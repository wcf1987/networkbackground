package com.flow.network.controller;

import com.flow.network.domain.PackageEntity;
import com.flow.network.service.PackageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/package")
public class PackageController {
    @Autowired
    private PackageServiceImp packageServiceImp;

    @RequestMapping("/list")
    public List<PackageEntity> list() {

        return packageServiceImp.getAllList();

    }
    @RequestMapping("/delete")
    public List<PackageEntity> delete(@RequestBody PackageEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        packageServiceImp.deleteByID(interfaceEntity.getId());
        return packageServiceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<PackageEntity> copy(@RequestBody PackageEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        packageServiceImp.copyByID(interfaceEntity);
        return packageServiceImp.getAllList();

    }
    @PostMapping("add")
    public List<PackageEntity> add(@RequestBody PackageEntity interfaceEntity){
        packageServiceImp.add(interfaceEntity);
        return packageServiceImp.getAllList();
    }
    @PostMapping("update")
    public List<PackageEntity> update(@RequestBody PackageEntity interfaceEntity){
        packageServiceImp.update(interfaceEntity);
        return packageServiceImp.getAllList();
    }
}