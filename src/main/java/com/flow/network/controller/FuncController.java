package com.flow.network.controller;

import com.flow.network.domain.FuncEntity;
import com.flow.network.service.FuncServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/func")
public class FuncController {
    @Autowired
    private FuncServiceImp serviceImp;
    @RequestMapping("/list")
    public List<FuncEntity> list() {

        return serviceImp.getAllList();

    }
    @RequestMapping("/delete")
    public List<FuncEntity> delete(@RequestBody FuncEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(entity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<FuncEntity> copy(@RequestBody FuncEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(entity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<FuncEntity> add(@RequestBody FuncEntity entity){
        serviceImp.add(entity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<FuncEntity> update(@RequestBody FuncEntity entity){
        serviceImp.update(entity);
        return serviceImp.getAllList();
    }
}