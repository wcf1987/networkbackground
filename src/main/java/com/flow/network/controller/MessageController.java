package com.flow.network.controller;

import com.flow.network.domain.MessageEntity;
import com.flow.network.service.MessageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageServiceImp serviceImp;
    @RequestMapping("/list")
    public List<MessageEntity> list() {

        return serviceImp.getAllList();

    }
    @RequestMapping("/delete")
    public List<MessageEntity> delete(@RequestBody MessageEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(entity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<MessageEntity> copy(@RequestBody MessageEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(entity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<MessageEntity> add(@RequestBody MessageEntity entity){
        serviceImp.add(entity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<MessageEntity> update(@RequestBody MessageEntity entity){
        serviceImp.update(entity);
        return serviceImp.getAllList();
    }
}