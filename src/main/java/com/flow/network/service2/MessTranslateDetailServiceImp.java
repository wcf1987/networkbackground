package com.flow.network.service2;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.flow.network.domain2.*;
import com.flow.network.mapper2.MessDetailMapper;
import com.flow.network.mapper2.MessTranslateDetailMapper;
import com.flow.network.mapper2.MessTraslateMapper;
import com.flow.network.tools.GraphTools;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class MessTranslateDetailServiceImp {

    @Autowired
    MessTranslateDetailMapper detailMapper;
    @Autowired
    MessTraslateMapper transDetailMapper;
    @Autowired
    private LogServiceImp logimp;

    public Integer deleteByIDS(List<String> ids) {
        Integer num = 0;
        for (String s : ids) {
            num = num + detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:" + String.valueOf(num) + "条");
        return num;
    }

    public String update(MessTraslateDetailEntity entity) {
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;

        //System.out.print("getlist");

    }

    public String updateOrderID(MessDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateOrderIDByPrimaryKey(entity);
        return Tools.SUCCESS;
    }

    public List<MessTraslateDetailEntity> dfs(MessTranslateEntity detailEntity) {
        GraphTools graphTools = new GraphTools();
        Integer maxid = transDetailMapper.getMaxMessBodyID();
        graphTools.initGraph(maxid + 1);

        List<MessTranslateEntity> pareList = transDetailMapper.searchByName("", 0);

        for (MessTranslateEntity me : pareList) {
            graphTools.setGraph(me.getSourceID(), me.getTargetID());
        }
        graphTools.setGraphValue(detailEntity.getSourceID(), detailEntity.getTargetID(), 0);


        graphTools.dfs(detailEntity.getSourceID(), detailEntity.getTargetID());
        System.out.println(graphTools.ans.get(0));

        ArrayList<Integer> dfsArray = graphTools.ans.get(0);
        List<MessTraslateDetailEntity> tempA;
        List<MessTraslateDetailEntity> tempB = null;
        List<MessTraslateDetailEntity> tempC = null;
        for (int i = 1; i < dfsArray.size(); i++) {
            Integer transID = 0;
            for (MessTranslateEntity me : pareList) {
                if (me.getSourceID() == dfsArray.get(i - 1) && me.getTargetID() == dfsArray.get(i)) {
                    transID = me.getID();
                }
            }
            if (transID != 0) {
                tempA = tempB;
                tempB = detailMapper.searchByTransID(transID,dfsArray.get(i));
                CompleteEName(tempB);
                if (tempA != null) {

                    updateTransSource(tempA, tempB);
                }
            }
        }
        List<MessTraslateDetailEntity> re = search("", 0, detailEntity.getTargetID(), "body", 0, 1, 1000);
        updateTransSourceReturn(re, tempB);
        updateTransNodesDB(tempB,detailEntity.getTransid());
        return re;
    }
    public void CompleteEName(List<MessTraslateDetailEntity> temp){
            for(MessTraslateDetailEntity z:temp){

                    z.setEName(getEName(temp,z));

            }
    }
    public String getEName(List<MessTraslateDetailEntity> temp,MessTraslateDetailEntity z){
        if(z.getNestID()==0){
            return z.getEName();
        }
        for(MessTraslateDetailEntity z1:temp) {
            if (z.getNestID().equals(z1.getFieldsID())) {
                return getEName(temp, z1) + "." + z.getEName();
            }
        }
        return "";
    }
    public void updateTransNodesDB(List<MessTraslateDetailEntity> tempB,Integer transid){
        for(MessTraslateDetailEntity t2 : tempB){
            if(t2.getTransrule()!=null) {
                t2.setTransID(transid);
                detailMapper.updateTransDetailByID(t2);
            }
            }
    }
    public void updateTransSource(List<MessTraslateDetailEntity> tempA, List<MessTraslateDetailEntity> tempB) {
        for (MessTraslateDetailEntity t2 : tempB) {
            if (t2.getOptional() != null && (t2.getOptional().equals("自定义转换计算") || t2.getOptional().equals("直接转换") || t2.getOptional().equals("内置变量赋值") || t2.getOptional().equals("直接赋值")) && t2.getSourceData() != null && (!t2.getSourceData().equals("[]")) && tempA != null) {
                String slist = t2.getSourceData();
                String rules=t2.getTransrule();
                JSONArray jsonArray = JSONUtil.parseArray(slist);
                JSONArray jsonArrayFinal = new JSONArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    for (MessTraslateDetailEntity t1 : tempA) {
                        if (t1.getName().equals(jsonArray.get(i).toString())) {
                            JSONArray jsonArray2 = JSONUtil.parseArray(t1.getSourceData());
                            jsonArrayFinal.addAll(jsonArray2);
                            rules=rules.replaceAll(t1.getEName(),t1.getTransrule());
                        }
                    }
                }
                System.out.println(jsonArrayFinal);
                t2.setSourceData(jsonArrayFinal.toString());
                t2.setTransrule(rules);
            }
        }

    }

    public void updateNode(MessTraslateDetailEntity node, List<MessTraslateDetailEntity> tempB) {
        for (MessTraslateDetailEntity t1 : tempB) {
            if (node.getID().equals(t1.getFieldsID()) && t1.getTransrule() != null) {
                node.setSourceData(t1.getSourceData());
                node.setTransrule(t1.getTransrule());
                node.setOptional("自定义转换计算");
            }
        }
    }

    public void updateTransSourceReturn(List<MessTraslateDetailEntity> results, List<MessTraslateDetailEntity> tempB) {
        if (results == null || results.size() == 0) {
            return;
        }
        Queue<MessTraslateDetailEntity> queue = new LinkedList<>();
        // 将List中的元素添加到队列中
        for (MessTraslateDetailEntity number : results) {

            queue.offer(number);
        }
        while (!queue.isEmpty()) {
            MessTraslateDetailEntity node = queue.poll();
            if (node.getChildren()==null) {
                updateNode(node, tempB);
            }
            if (node.getChildren() != null) {
                for (MessTraslateDetailEntity number : node.getChildren()) {

                    queue.offer(number);
                }
            }
        }
        return;

    }

    public List<MessTraslateDetailEntity> search(String name, Integer uid, Integer pid, String ttype, Integer transid, Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);

        List<MessTraslateDetailEntity> list = detailMapper.searchByName(name, uid, pid, ttype);
        Deque<MessTraslateDetailEntity> waitList = new ArrayDeque<MessTraslateDetailEntity>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOutType().equals("nest")) {
                waitList.push(list.get(i));
            }
        }
        for (; waitList.size() > 0; ) {
            MessTraslateDetailEntity t = waitList.pop();
            List<MessTraslateDetailEntity> listt = detailMapper.getListByNestID(name, uid, pid, ttype, t.getID());
            if (listt.size() > 0) {
                t.setChildren(listt);
            }
            for (int i = 0; i < listt.size(); i++) {
                if (listt.get(i).getOutType().equals("nest")) {
                    waitList.push(listt.get(i));
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getOutType().equals("nest")) {
                CompleteFields(list.get(i), transid);
                waitList.push(list.get(i));
            } else {
                CompleteFields(list.get(i), transid);
            }
        }
        for (; waitList.size() > 0; ) {
            MessTraslateDetailEntity t = waitList.pop();
            List<MessTraslateDetailEntity> listt = null;
            if (t.getChildren() == null) {
                t.setChildren(new ArrayList<MessTraslateDetailEntity>());
                continue;
            }
            if (t.getChildren().size() > 0) {
                listt = t.getChildren();
            } else {
                continue;
            }
            for (int i = 0; i < listt.size(); i++) {
                if (listt.get(i).getOutType().equals("nest")) {
                    CompleteFields(listt.get(i), transid);
                    waitList.push(listt.get(i));
                } else {
                    CompleteFields(listt.get(i), transid);
                }
            }
        }


        return list;
    }

    //    补全字段
    public void CompleteFields(MessTraslateDetailEntity t, Integer transid) {
        MessTraslateDetailEntity temp;
        if (t.getOutType().equals("custom")) {
            temp = detailMapper.selectCustomByPrimaryKey(t.getOutID());
            t.setEName(temp.getEName());
            t.setShortName(temp.getShortName());
            t.setTName(t.getName());
            //t.setFlag(temp.getFlag());
            t.setType(temp.getType());
            t.setLength(temp.getLength());

        }
        if (t.getOutType().equals("fields")) {
            temp = detailMapper.selectFieldsByPrimaryKey(t.getOutID());
            t.setEName(temp.getEName());
            t.setShortName(temp.getShortName());
            t.setTName(t.getName());
            t.setType(temp.getType());
            t.setLength(temp.getLength());
        }
        if (t.getOutType().equals("nest")) {
            temp = detailMapper.selectNestByPrimaryKey(t.getID());
            //System.out.println(temp.getEName());
            t.setEName(temp.getEName());
            return;
        }
        temp = detailMapper.selectByFieldsID(t.getID(), transid);
        if (temp != null) {

            t.setTransID(transid);
            t.setTName(temp.getTName());
            t.setOptional(temp.getOptional());
            t.setTransrule(temp.getTransrule());
            t.setDescribes(temp.getDescribes());
            t.setCreateTime(temp.getCreateTime());
            t.setFuncrule(temp.getFuncrule());
            t.setSourceData(temp.getSourceData());
            t.setTransDetailID(temp.getID());

        } else {
            if (transid == -1 || transid == 0) {

            } else {
                detailMapper.insertByFieldsID(t.getID(), transid);
                temp = detailMapper.selectByFieldsID(t.getID(), transid);
                t.setTransID(transid);
                t.setTransDetailID(temp.getID());
            }
        }

    }

    public List<MessTraslateDetailEntity> searchAll(String name, Integer uid, Integer pid, String ttype) {
        //System.out.print("getlist");
        List<MessTraslateDetailEntity> list = detailMapper.searchByName(name, uid, pid, ttype);

        return list;
    }

    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }

    @Autowired
    MessDetailMapper messDetailMapper;

    public DUITransDetailAll searchAllDUITrans() {
        DUITransDetailAll duiAll = new DUITransDetailAll();
        List<DUITransDetailEntity> list = detailMapper.searchAllDUITrans();
        //System.out.println(list);
        List<DUITransDetailEntity> listone = new ArrayList<DUITransDetailEntity>();
        List<FieldsDetailEntity> pointone = new ArrayList<FieldsDetailEntity>();
        try {
            for (DUITransDetailEntity tdui : list) {
                //System.out.println(tdui);
                FieldsDetailEntity targetDUI = messDetailMapper.selectFieldsInfoByID(tdui.getTargetFieldID());

                JSONArray jsonArray = JSONUtil.parseArray(tdui.getSourceData());
                // 输出转换后的JSONArray对象
                System.out.println(jsonArray);
                // 遍历JSONArray对象
                int k = 0;
                for (int i = 0; i < jsonArray.size(); i++) {
                    String sname = jsonArray.get(i).toString();
                    //JSONArray jsonArray1 = JSONUtil.parseArray(json);

                    FieldsDetailEntity sourceDUI = messDetailMapper.selectFieldsInfoByName(sname, tdui.getSourceMessID());
                    System.out.println(sourceDUI);
                    if (sourceDUI != null) {
                        DUITransDetailEntity duione = new DUITransDetailEntity();
                        if (sourceDUI.getID() == targetDUI.getID() || sourceDUI.getID().equals(targetDUI.getID())) {

                        } else {
                            k = k + 1;
                            duione.setSourceFieldID(String.valueOf(sourceDUI.getID()));
                            duione.setTargetFieldID(String.valueOf(targetDUI.getID()));
                            duione.setTransID(tdui.getTransID());
                            duione.setOptional(tdui.getOptional());
                            duione.setTransrule(tdui.getTransrule());
                            duione.setTName(tdui.getTName());
                            pointone.add(sourceDUI);
                            listone.add(duione);
                        }
                    }
                }
                ;
                if (k > 0) {
                    pointone.add(targetDUI);
                    //FieldsDetailEntity sourceDUI=messDetailMapper.selectFieldsInfoByName(tdui.getSourceData(),tdui.getSourceMessID());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 去重
        List<DUITransDetailEntity> conectnew = listone.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(DUITransDetailEntity::getSouToTar))), ArrayList::new));
        List<FieldsDetailEntity> pointnew = pointone.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(FieldsDetailEntity::getID))), ArrayList::new));
        duiAll.setPoint(pointnew);
        duiAll.setConnect(conectnew);
        return duiAll;
    }
}
