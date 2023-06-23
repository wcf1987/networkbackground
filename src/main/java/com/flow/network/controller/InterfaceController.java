package com.flow.network.controller;

import com.flow.network.domain.InterfaceEntity;
import com.flow.network.service.InterfaceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interface")
public class InterfaceController {
    @Autowired
    private InterfaceServiceImp interfaceService;
    @RequestMapping("/list")
    public List<InterfaceEntity> list() {

        return interfaceService.getAllList();

    }
    @RequestMapping("/delete")
    public List<InterfaceEntity> delete(@RequestBody InterfaceEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        interfaceService.deleteByID(interfaceEntity.getId());
        return interfaceService.getAllList();

    }
    @RequestMapping("/copy")
    public List<InterfaceEntity> copy(@RequestBody InterfaceEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        interfaceService.copyByID(interfaceEntity);
        return interfaceService.getAllList();

    }
    @PostMapping("add")
    public List<InterfaceEntity> add(@RequestBody InterfaceEntity interfaceEntity){
        interfaceService.add(interfaceEntity);
        return interfaceService.getAllList();
    }
    @PostMapping("update")
    public List<InterfaceEntity> update(@RequestBody InterfaceEntity interfaceEntity){
        interfaceService.update(interfaceEntity);
        return interfaceService.getAllList();
    }
}