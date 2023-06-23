package com.flow.network.service;

import com.flow.network.domain.InterfaceDetailEntity;
import com.flow.network.domain.InterfaceEntity;
import com.flow.network.mapper.InterfaceDetailMapper;
import com.flow.network.mapper.InterfaceMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class InterfaceServiceImp
{

    @Autowired
    InterfaceMapper interfaceMapper;
    @Autowired
    InterfaceDetailMapper interfaceDetailMapper;
    public List<InterfaceEntity> getAllList() {
        System.out.print("getlist");
        List<InterfaceEntity> list=interfaceMapper.getList();
        return list;
    }
    public String add(InterfaceEntity interfaceEntity) {
        //System.out.print("getlist");
        //interfaceEntity.setId(-1);
        int pid=interfaceMapper.insert(interfaceEntity);
        pid=interfaceEntity.getId();
        if(interfaceEntity.getType().equals("串口")){
            interfaceDetailMapper.insert(new InterfaceDetailEntity(1,"类型","type","0","串口",pid));
            interfaceDetailMapper.insert(new InterfaceDetailEntity(2,"出口","outside","1","",pid));
            interfaceDetailMapper.insert(new InterfaceDetailEntity(3,"比特率","bitrate","8","",pid));
            interfaceDetailMapper.insert(new InterfaceDetailEntity(4,"流控","flowControl","4","",pid));

        }else{
            interfaceDetailMapper.insert(new InterfaceDetailEntity(1,"类型","type","0","网口",pid));
            interfaceDetailMapper.insert(new InterfaceDetailEntity(2,"IP地址","IPAddress","32","",pid));
            interfaceDetailMapper.insert(new InterfaceDetailEntity(3,"端口地址","port","8","",pid));
            interfaceDetailMapper.insert(new InterfaceDetailEntity(4,"协议","protocol","4","",pid));

        }

        return Tools.SUCCESS;
    }
    public String update(InterfaceEntity interfaceEntity) {
        //System.out.print("getlist");
        interfaceMapper.updateByPrimaryKey(interfaceEntity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        interfaceMapper.delete(id);
        interfaceDetailMapper.deleteByPID(id);
        return 1;
    }
    public Integer copyByID(InterfaceEntity interfaceEntity) {
        System.out.print("deleteByID");
        int oldid=interfaceEntity.getId();
        int newid=interfaceMapper.copy(interfaceEntity);
        newid=interfaceEntity.getId();
        interfaceDetailMapper.copyByPID(oldid,newid);
        return 1;
    }
}
