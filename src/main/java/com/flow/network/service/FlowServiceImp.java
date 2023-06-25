package com.flow.network.service;

import com.flow.network.domain.FlowDetailEntity;
import com.flow.network.domain.FlowEntity;
import com.flow.network.mapper.FlowDetailMapper;
import com.flow.network.mapper.FlowMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowServiceImp
{

    @Autowired
    FlowMapper mapper;
    @Autowired
    private FlowDetailMapper flowDetailMapper;
    @Autowired
   // AppDetailMapper detailMapper;
    public List<FlowEntity> getAllList() {
        System.out.println("get-flow-list");
        List<FlowEntity> list=mapper.getList();
        return list;
    }
    public List<FlowEntity> getList(Integer pageNum,Integer pageSize) {
        System.out.println("get-flow-list");
        PageHelper.startPage(pageNum, pageSize);
        List<FlowEntity> list=mapper.getList();
        return list;
    }
    public String createScriptByID(Integer id) {
        //System.out.println("deleteByID");
        //mapper.delete(id);
        //detailMapper.deleteByPID(id);
        FlowDetailEntity flowDetailEntity=flowDetailMapper.selectByPrimaryKey(id);
        return  flowDetailEntity.getBpmnjsonstr();
    }
    public String add(FlowEntity entity) {
        //System.out.print("getlist");
        //AppDetailEntity.setId(-1);
        int pid=mapper.insert(entity);
        pid=entity.getId();
 

        return Tools.SUCCESS;
    }
    public String update(FlowEntity entity) {
        //System.out.print("getlist");
        mapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public String deleteByID(Integer id) {
        System.out.println("deleteByID");
        mapper.delete(id);
        //detailMapper.deleteByPID(id);
        return  Tools.SUCCESS;
    }
    public String copyByID(FlowEntity entity) {
        System.out.print("deleteByID");
        int oldid=entity.getId();
        int newid=mapper.copy(entity);
        newid=entity.getId();
        //detailMapper.copyByPID(oldid,newid);
        return Tools.SUCCESS;
    }
}
