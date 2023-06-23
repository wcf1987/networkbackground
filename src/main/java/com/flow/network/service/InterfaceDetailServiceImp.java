package com.flow.network.service;

import com.flow.network.domain.InterfaceDetailEntity;
import com.flow.network.mapper.InterfaceDetailMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InterfaceDetailServiceImp
{

    @Autowired
    InterfaceDetailMapper interfaceDetailMapper;
    public List<InterfaceDetailEntity> getListByPID(Integer id) {
        System.out.print("getlist");
        List<InterfaceDetailEntity> list=interfaceDetailMapper.getList(id);
        return list;
    }
    public String add(InterfaceDetailEntity interfaceDetailEntity) {
        //System.out.print("getlist");
        interfaceDetailMapper.insert(interfaceDetailEntity);
        return Tools.SUCCESS;
    }
    public String update(InterfaceDetailEntity interfaceDetailEntity) {
        //System.out.print("getlist");
        interfaceDetailMapper.updateByPrimaryKey(interfaceDetailEntity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        interfaceDetailMapper.delete(id);
        return 1;
    }
}
