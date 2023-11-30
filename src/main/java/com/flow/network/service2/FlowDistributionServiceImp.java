package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FlowDesignEntity;
import com.flow.network.domain2.FlowDistributionEntity;
import com.flow.network.domain2.GatewayEntity;
import com.flow.network.mapper2.FlowDesignMapper;
import com.flow.network.mapper2.FlowDistributionMapper;
import com.flow.network.mapper2.GatewayMapper;
import com.flow.network.tools.Tools;
import com.flow.network.tools.ZMQRequester;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FlowDistributionServiceImp {

    @Autowired
    FlowDistributionMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;
    @Autowired
    FlowDesignMapper detailMapper2;
    @Autowired
    GatewayMapper detailMapper3;

    public String add(FlowDistributionEntity entity) {
        if (detailMapper.selectByName(entity.getName(), 0) > 0) {
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加流程分发:" + entity.getName());
        return Tools.SUCCESS;
    }

    public String update(FlowDistributionEntity entity) {
        //System.out.print("getlist");
        if (detailMapper.selectByName(entity.getName(), entity.getID()) > 0) {
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新流程分发:" + entity.getName());
        return Tools.SUCCESS;
    }

    public List<FlowDistributionEntity> search(String name, Integer uid, Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<FlowDistributionEntity> list = detailMapper.searchByName(name, uid);
        List<GatewayEntity> list2 = detailMapper3.searchByName("", uid);
        for (int i = 0; i < list.size(); i++) {
            FlowDistributionEntity temp = list.get(i);
            Integer fid = temp.getFlowID();
            if (fid != null) {
                FlowDesignEntity fd = detailMapper2.getFlowDesignByID(fid);
                temp.setFlowName(fd.getName());
                temp.setFlowOutStr(fd.getFlowOutStr());
            }
            String s = temp.getGatewayIDs();
            if (!s.equals("")) {
                String s1 = s.substring(1, s.length() - 1);
                System.out.println(s1);
                String[] s2 = s1.split(",");
                String s3 = "";
                for (int k = 0; k < s2.length; k++) {
                    for (int j = 0; j < list2.size(); j++) {
                        if (String.valueOf(list2.get(j).getID()).equals(s2[k])) {
                            s3 = s3 + list2.get(j).getName() + ",";
                        }
                    }

                }
                temp.setGatewayNames(s3);
            }
        }
        return list;
    }

    public List<FlowDistributionEntity> searchAll(String name, Integer uid) {
        //System.out.print("getlist");
        List<FlowDistributionEntity> list = detailMapper.searchByName(name, uid);

        return list;
    }

    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除流程分发:" + id);
        return 1;
    }

    public String dispatch(Integer id) {
        String re = "";
        List<FlowDistributionEntity> list = detailMapper.searchById(id);
        List<GatewayEntity> gatelist=new ArrayList<GatewayEntity>();
        List<GatewayEntity> list2 = detailMapper3.searchByName("", 0);
        int i = 0;
        FlowDistributionEntity temp = list.get(i);
        Integer fid = temp.getFlowID();
        if (fid != null) {
            FlowDesignEntity fd = detailMapper2.getFlowDesignByID(fid);
            temp.setFlowName(fd.getName());
            temp.setFlowOutStr(fd.getFlowOutStr());
        }else{
            throw new ServiceException("流程不存在");
        }
        String s = temp.getGatewayIDs();
        if (!s.equals("")) {
            String s1 = s.substring(1, s.length() - 1);
            System.out.println(s1);
            String[] s2 = s1.split(",");
            String s3 = "";

            for (int k = 0; k < s2.length; k++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (String.valueOf(list2.get(j).getID()).equals(s2[k])) {
                        //s3 = s3 + list2.get(j).getName() + ",";
                        gatelist.add(list2.get(j));
                    }
                }

            }
            ZMQRequester zmqreq=new ZMQRequester();

            for(int k=0;k<gatelist.size();k++){
                String sa1="tcp://";
                String sa2=":5555";
                String saddr=sa1+gatelist.get(k).getIP()+sa2;
                System.out.println("发送信息:"+saddr);
                //re=zmqreq.sendRequest(temp.getFlowOutStr(),saddr);
                re="1";
                logimp.addInfo(temp.getFlowName()+" 流程下发至:" + saddr+" ,返回"+re);
            }
            //temp.setGatewayNames(s3);
        }

        return re;
    }
}
