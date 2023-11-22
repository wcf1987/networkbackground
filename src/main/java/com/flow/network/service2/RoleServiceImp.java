package com.flow.network.service2;

import com.flow.network.config.ServiceException;
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
    @Autowired
    private LogServiceImp logimp;

    public String add(RoleEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getRoleName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.insert(entity);
        logimp.addInfo("添加角色:"+entity.getRoleName());
        return Tools.SUCCESS;
    }
    public String update(RoleEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getRoleName(),entity.getId())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新角色:"+entity.getRoleName());
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
        logimp.addInfo("删除角色:"+id);
        return 1;
    }
}
