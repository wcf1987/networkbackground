package com.flow.network.controller;

import com.flow.network.domain.PackEntity;
import com.flow.network.service.PackServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pack")
public class PackController {
    @Autowired
    private PackServiceImp serviceImp;
    @RequestMapping("/list")
    public List<PackEntity> list() {

        return serviceImp.getAllList();

    }
    @RequestMapping("/listByType")
    public List<PackEntity> listByType(@RequestBody PackEntity entity) {

        return serviceImp.getListByType(entity.getType());

    }

    @RequestMapping("/delete")
    public List<PackEntity> delete(@RequestBody PackEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(entity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<PackEntity> copy(@RequestBody PackEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(entity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<PackEntity> add(@RequestBody PackEntity entity){
        serviceImp.add(entity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<PackEntity> update(@RequestBody PackEntity entity){
        serviceImp.update(entity);
        return serviceImp.getAllList();
    }
}