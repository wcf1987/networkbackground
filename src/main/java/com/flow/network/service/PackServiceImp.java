package com.flow.network.service;

import com.flow.network.domain.PackEntity;
import com.flow.network.mapper.PackMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackServiceImp
{

    @Autowired
    PackMapper mapper;
    public List<PackEntity> getAllList() {
        System.out.print("getlist");
        List<PackEntity> list=mapper.getList();
        return list;
    }
    public List<PackEntity> getList(Integer pageNum,Integer pageSize) {
        System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<PackEntity> list=mapper.getList();
        return list;
    }
    public List<PackEntity> getListByType(String type) {
        System.out.println("getpacklist"+type);
        List<PackEntity> list=mapper.getListByType(type);

        return list;
    }
    public String add(PackEntity entity) {
        //System.out.print("getlist");
        //AppDetailEntity.setId(-1);
        int pid=mapper.insert(entity);
        pid=entity.getId();
 

        return Tools.SUCCESS;
    }
    public String update(PackEntity entity) {
        //System.out.print("getlist");
        mapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public String deleteByID(Integer id) {
        System.out.println("deleteByID");
        mapper.delete(id);

        return  Tools.SUCCESS;
    }
    public String copyByID(PackEntity entity) {
        System.out.print("deleteByID");
        int oldid=entity.getId();
        int newid=mapper.copy(entity);
        newid=entity.getId();
        //detailMapper.copyByPID(oldid,newid);
        return Tools.SUCCESS;
    }
}
