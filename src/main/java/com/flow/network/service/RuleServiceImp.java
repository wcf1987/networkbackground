package com.flow.network.service;

import com.flow.network.domain.RuleEntity;
import com.flow.network.mapper.RuleDetailMapper;
import com.flow.network.mapper.RuleMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RuleServiceImp
{

    @Autowired
    RuleMapper mapper;
    @Autowired
    RuleDetailMapper detailMapper;
    public List<RuleEntity> getList(Integer pageNum,Integer pageSize) {
        System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<RuleEntity> list=mapper.getList();
        return list;
    }
    public List<RuleEntity> getAllList() {
        System.out.print("getlist");
        List<RuleEntity> list=mapper.getList();
        return list;
    }
    public String add(RuleEntity entity) {
        //System.out.print("getlist");
        //AppDetailEntity.setId(-1);
        int pid=mapper.insert(entity);
        pid=entity.getId();
 

        return Tools.SUCCESS;
    }
    public String update(RuleEntity entity) {
        //System.out.print("getlist");
        mapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public String deleteByID(Integer id) {
        System.out.println("deleteByID");
        mapper.delete(id);
        detailMapper.deleteByPID(id);
        return  Tools.SUCCESS;
    }
    public String copyByID(RuleEntity entity) {
        System.out.print("deleteByID");
        int oldid=entity.getId();
        int newid=mapper.copy(entity);
        newid=entity.getId();
        detailMapper.copyByPID(oldid,newid);
        return Tools.SUCCESS;
    }
}
