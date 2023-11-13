package com.flow.network.service2;

import com.flow.network.domain2.RoleEntity;
import com.flow.network.mapper2.RoleMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImp
{

    @Autowired
    RoleMapper detailMapper;


    public String add(RoleEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(RoleEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<RoleEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<RoleEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<RoleEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<RoleEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public RoleEntity getByID(Integer id) {
        //System.out.print("getlist");
        RoleEntity r=detailMapper.getByID(id);

        return r;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
