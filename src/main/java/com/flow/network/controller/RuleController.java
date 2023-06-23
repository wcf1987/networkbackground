package com.flow.network.controller;

import com.flow.network.domain.RuleEntity;
import com.flow.network.service.RuleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleServiceImp serviceImp;
    @RequestMapping("/list")
    public List<RuleEntity> list() {

        return serviceImp.getAllList();

    }
    @RequestMapping("/delete")
    public List<RuleEntity> delete(@RequestBody RuleEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(entity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<RuleEntity> copy(@RequestBody RuleEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(entity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<RuleEntity> add(@RequestBody RuleEntity entity){
        serviceImp.add(entity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<RuleEntity> update(@RequestBody RuleEntity entity){
        serviceImp.update(entity);
        return serviceImp.getAllList();
    }
}