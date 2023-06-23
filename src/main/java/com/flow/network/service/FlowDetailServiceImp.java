package com.flow.network.service;

import com.flow.network.domain.FlowDetailEntity;
import com.flow.network.mapper.FlowDetailMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowDetailServiceImp
{

    @Autowired
    FlowDetailMapper detailMapper;
    public List<FlowDetailEntity> getListByPID(Integer id) {
        System.out.print("getlist");
        List<FlowDetailEntity> list=detailMapper.getList(id);
        return list;
    }

    public FlowDetailEntity getByID(Integer id) {
        //System.out.print("getlist");
        FlowDetailEntity one=detailMapper.selectByPrimaryKey(id);
        return one;
    }
    public String add(FlowDetailEntity entity) {
        //System.out.print("getlist");
        FlowDetailEntity temp=detailMapper.selectByPrimaryKey(entity.getFlowID());
        if(temp==null){
            System.out.println("新增模型");
            detailMapper.insert(entity);
        }else{
            System.out.println("更新模型");
            detailMapper.updateByPrimaryKey(entity);
        }

        return Tools.SUCCESS;
    }
    public String update(FlowDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
