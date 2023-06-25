package com.flow.network.service;

import com.flow.network.domain.RuleDetailEntity;
import com.flow.network.mapper.RuleDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RuleDetailServiceImp
{

    @Autowired
    RuleDetailMapper detailMapper;

    public List<RuleDetailEntity> getListByPID(Integer id, Integer pageNum, Integer pageSize) {
        System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<RuleDetailEntity> list=detailMapper.getList(id);
        return list;
    }
    public List<RuleDetailEntity> geAlltListByPID(Integer id) {
        System.out.print("getlist");
        List<RuleDetailEntity> list=detailMapper.getList(id);
        return list;
    }
    public String add(RuleDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(RuleDetailEntity entity) {
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
