package com.flow.network.service;

import com.flow.network.domain.FuncEntity;
import com.flow.network.mapper.FuncMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FuncServiceImp
{

    @Autowired
    FuncMapper mapper;
    public List<FuncEntity> getAllList() {
        System.out.print("getlist");
        List<FuncEntity> list=new ArrayList<FuncEntity>();
        list.add(new FuncEntity(100,"数学函数","基础函数","",null));
        list.add(new FuncEntity(200,"逻辑函数","基础函数","",null));
        list.add(new FuncEntity(300,"字符串函数","基础函数","",null));
        list.add(new FuncEntity(400,"时间函数","基础函数","",null));
        list.add(new FuncEntity(500,"集合函数","基础函数","",null));
        list.add(new FuncEntity(600,"自定义函数","基础函数","",null));

        for(int i=0;i<list.size();i++){
            List<FuncEntity> temp=mapper.getListByType(list.get(i).getName());
            list.get(i).setChildren(temp);
        }

        //List<FuncEntity> temp=mapper.getList();

        return list;
    }
    public String add(FuncEntity entity) {
        //System.out.print("getlist");
        //AppDetailEntity.setId(-1);
        int pid=mapper.insert(entity);
        pid=entity.getId();
 

        return Tools.SUCCESS;
    }
    public String update(FuncEntity entity) {
        //System.out.print("getlist");
        mapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public String deleteByID(Integer id) {
        System.out.println("deleteByID");
        mapper.delete(id);

        return  Tools.SUCCESS;
    }
    public String copyByID(FuncEntity entity) {
        System.out.print("deleteByID");
        int oldid=entity.getId();
        int newid=mapper.copy(entity);
        newid=entity.getId();
        //detailMapper.copyByPID(oldid,newid);
        return Tools.SUCCESS;
    }
}
