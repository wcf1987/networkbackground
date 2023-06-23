package com.flow.network.controller;

import com.flow.network.domain.MessageDetailEntity;
import com.flow.network.service.MessageDetailServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messageDetail")
public class MessageDetailController {
    @Autowired
    private MessageDetailServiceImp detailServiceImp;
    @RequestMapping("/list")
    public List<MessageDetailEntity> list(@RequestBody MessageDetailEntity detailEntity) {

        return detailServiceImp.getListByMID(detailEntity.getMessageID());

    }
    @RequestMapping("/allList")
    public List<MessageDetailEntity> alllist(@RequestBody MessageDetailEntity detailEntity) {

        return detailServiceImp.getAllListByMID(detailEntity.getMessageID());

    }
    @RequestMapping("/getpidlist")
    public List<MessageDetailEntity> getPidList(@RequestBody MessageDetailEntity detailEntity) {

        return detailServiceImp.getListByPID(detailEntity.getMessageID(),detailEntity.getPid());

    }
    @RequestMapping("/delete")
    public String delete(@RequestBody MessageDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        detailServiceImp.deleteByID(detailEntity.getId());
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody MessageDetailEntity detailEntity){
        detailServiceImp.add(detailEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody MessageDetailEntity detailEntity){
        detailServiceImp.update(detailEntity);
        return Tools.SUCCESS;
    }
}