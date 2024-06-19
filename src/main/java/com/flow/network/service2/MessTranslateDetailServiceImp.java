package com.flow.network.service2;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.flow.network.domain2.*;
import com.flow.network.mapper2.MessDetailMapper;
import com.flow.network.mapper2.MessTranslateDetailMapper;
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
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
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

        }
        if (t.getOutType().equals("fields")) {
            temp = detailMapper.selectFieldsByPrimaryKey(t.getOutID());
            t.setEName(temp.getEName());
            t.setShortName(temp.getShortName());
            t.setTName(t.getName());

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
            if(transid==-1){

            }else {
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
        DUITransDetailAll duiAll=new DUITransDetailAll();
        List<DUITransDetailEntity> list =detailMapper.searchAllDUITrans();
        //System.out.println(list);
        List<DUITransDetailEntity> listone =new ArrayList<DUITransDetailEntity>();
        List<FieldsDetailEntity> pointone =new ArrayList<FieldsDetailEntity>();
        try {
            for (DUITransDetailEntity tdui : list) {
                //System.out.println(tdui);
                FieldsDetailEntity targetDUI = messDetailMapper.selectFieldsInfoByID(tdui.getTargetFieldID());

                JSONArray jsonArray = JSONUtil.parseArray(tdui.getSourceData());
                // 输出转换后的JSONArray对象
                System.out.println(jsonArray);
                // 遍历JSONArray对象
                int k=0;
                for(int i=0;i<jsonArray.size();i++)
                {   String sname=jsonArray.get(i).toString();
                    //JSONArray jsonArray1 = JSONUtil.parseArray(json);

                    FieldsDetailEntity sourceDUI=messDetailMapper.selectFieldsInfoByName(sname,tdui.getSourceMessID());
                    System.out.println(sourceDUI);
                    if(sourceDUI!=null){
                        DUITransDetailEntity duione=new DUITransDetailEntity();
                        if(sourceDUI.getID()==targetDUI.getID()){

                        }else {
                            k=k+1;
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
                };
                if(k>0) {
                    pointone.add(targetDUI);
                    //FieldsDetailEntity sourceDUI=messDetailMapper.selectFieldsInfoByName(tdui.getSourceData(),tdui.getSourceMessID());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        // 去重
        List<DUITransDetailEntity> conectnew = listone.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(DUITransDetailEntity ::getSouToTar ))), ArrayList::new));
        List<FieldsDetailEntity> pointnew =pointone.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(FieldsDetailEntity ::getID ))), ArrayList::new));
        duiAll.setPoint(pointnew);
        duiAll.setConnect(conectnew);
        return duiAll;
    }
}
