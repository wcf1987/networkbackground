package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.UserEntity;
import com.flow.network.mapper2.RoleMapper;
import com.flow.network.mapper2.UserMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp
{

    @Autowired
    UserMapper detailMapper;
    @Autowired
    RoleMapper detailMapper2;

    public String add(UserEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getUserName())!=null){
            throw new ServiceException("用户名重复，请更改");
        }
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public UserEntity getByPass(String name,String pass) {
        //System.out.print("getlist");
        UserEntity userEntity=detailMapper.check(name,pass);
        return userEntity;
    }
    public UserEntity sighOut(String name) {
        //System.out.print("getlist");
        //detailMapper.check(name,pass);
        return null;
    }
    public String update(UserEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<UserEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<UserEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<UserEntity> list=detailMapper.searchByName(name,uid);
/*
        for(int i=0;i<list.size();i++){
            UserEntity user=list.get(i);
            RoleEntity role=detailMapper2.selectByPrimaryKey(user.getRoleSign());
            if(role!=null){
                user.setRoleStr(role.getRoleName());

            }
        }
      */
        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
