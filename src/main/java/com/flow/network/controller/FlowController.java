package com.flow.network.controller;

import com.flow.network.domain.FlowEntity;
import com.flow.network.service.FlowServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flow")
public class FlowController {
    @Autowired
    private FlowServiceImp serviceImp;
    @RequestMapping("/list")
    public List<FlowEntity> list() {
        System.out.println("flow list");
        return serviceImp.getAllList();

    }
    @RequestMapping("/delete")
    public List<FlowEntity> delete(@RequestBody FlowEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(entity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<FlowEntity> copy(@RequestBody FlowEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(entity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<FlowEntity> add(@RequestBody FlowEntity entity){
        serviceImp.add(entity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<FlowEntity> update(@RequestBody FlowEntity entity){
        serviceImp.update(entity);
        return serviceImp.getAllList();
    }
}