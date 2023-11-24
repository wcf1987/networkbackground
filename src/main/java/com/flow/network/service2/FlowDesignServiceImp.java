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

    @Autowired
    private LogServiceImp logimp;
    public String add(FlowDesignEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加流程设计:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String copy(FlowDesignEntity entity) {
        FlowDesignEntity f=detailMapper.getFlowDesignByID(entity.getID());
        //System.out.print("getlist");
        f.setName(f.getName()+"_copy");
        f.setAuthorID(entity.getAuthorID());
        detailMapper.insert(f);
        logimp.addInfo("复制流程设计:"+f.getName());
        return Tools.SUCCESS;
    }
    public String update(FlowDesignEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新流程设计:"+entity.getName());
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
        logimp.addInfo("删除流程设计:"+id);
        return 1;
    }
}
