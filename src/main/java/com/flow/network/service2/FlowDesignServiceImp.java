package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FlowDesignEntity;
import com.flow.network.mapper2.FlowDesignMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FlowDesignServiceImp
{

    @Autowired
    FlowDesignMapper detailMapper;


    public String add(FlowDesignEntity entity) {
        if(detailMapper.selectByName(entity.getName())!=null){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(FlowDesignEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public FlowDesignEntity updateJson(FlowDesignEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateJsonByPrimaryKey(entity);
        FlowDesignEntity t=getFlowByID(entity.getID());
        return t;
    }
    public List<FlowDesignEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<FlowDesignEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public FlowDesignEntity getFlowByID(Integer id) {
        //System.out.print("getlist");

        FlowDesignEntity t=detailMapper.getFlowDesignByID(id);

        return t;
    }
    public List<FlowDesignEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<FlowDesignEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
