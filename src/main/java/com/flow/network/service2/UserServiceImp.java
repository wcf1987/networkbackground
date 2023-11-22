package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.LogEntity;
import com.flow.network.domain2.UserEntity;
import com.flow.network.mapper2.LogMapper;
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
    @Autowired
    LogMapper logMapper;
    @Autowired
    private LogServiceImp logimp;
    public String add(UserEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getUserName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.insert(entity);
        logimp.addInfo("添加用户:"+entity.getUserName());
        return Tools.SUCCESS;
    }
    public UserEntity getByPass(String name,String pass) {
        //System.out.print("getlist");
        UserEntity userEntity=detailMapper.check(name,pass);
        if(userEntity!=null){

            logMapper.insert(new LogEntity("info",name,"登陆成功"));
        }else{
            logMapper.insert(new LogEntity("info",name,"登陆失败"));
        }
        return userEntity;
    }
    public UserEntity sighOut(String name) {
        //System.out.print("getlist");
        //detailMapper.check(name,pass);

            logMapper.insert(new LogEntity("info",name,"注销成功"));

        return null;
    }
    public String update(UserEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新用户:"+entity.getUserName());
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
        logimp.addInfo("删除用户ID:"+id);
        return 1;
    }
}
