package com.flow.network.controller;

import com.flow.network.domain.InterfaceDetailEntity;
import com.flow.network.service.InterfaceDetailServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interfaceDetail")
public class InterfaceDetailController {
    @Autowired
    private InterfaceDetailServiceImp interfaceDetailServiceImp;
    @RequestMapping("/list")
    public List<InterfaceDetailEntity> list(@RequestBody InterfaceDetailEntity interfaceDetailEntity) {

        return interfaceDetailServiceImp.getListByPID(interfaceDetailEntity.getInterfaceID());

    }
    @RequestMapping("/delete")
    public String delete(@RequestBody InterfaceDetailEntity interfaceDetailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        interfaceDetailServiceImp.deleteByID(interfaceDetailEntity.getId());
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody InterfaceDetailEntity interfaceDetailEntity){
        interfaceDetailServiceImp.add(interfaceDetailEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody InterfaceDetailEntity interfaceDetailEntity){
        interfaceDetailServiceImp.update(interfaceDetailEntity);
        return Tools.SUCCESS;
    }
}