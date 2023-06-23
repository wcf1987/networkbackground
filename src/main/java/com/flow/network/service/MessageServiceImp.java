package com.flow.network.service;

import com.flow.network.domain.MessageEntity;
import com.flow.network.mapper.MessageDetailMapper;
import com.flow.network.mapper.MessageMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessageServiceImp
{

    @Autowired
    MessageMapper mapper;
    @Autowired
    MessageDetailMapper detailMapper;
    public List<MessageEntity> getAllList() {
        System.out.print("getlist");
        List<MessageEntity> list=mapper.getList();
        return list;
    }
    public String add(MessageEntity entity) {
        //System.out.print("getlist");
        //AppDetailEntity.setId(-1);
        int pid=mapper.insert(entity);
        pid=entity.getId();
 

        return Tools.SUCCESS;
    }
    public String update(MessageEntity entity) {
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
    public String copyByID(MessageEntity entity) {
        System.out.print("deleteByID");
        int oldid=entity.getId();
        int newid=mapper.copy(entity);
        newid=entity.getId();
        detailMapper.copyByPID(oldid,newid);
        return Tools.SUCCESS;
    }
}
