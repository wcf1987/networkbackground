package com.flow.network.controller;

import com.flow.network.domain.AppEntity;
import com.flow.network.domain.PackageEntity;
import com.flow.network.domain.PageParmInfo;
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
    private PackageServiceImp serviceImp;



    @RequestMapping("/list")
    public List<PackageEntity> list(@RequestBody PageParmInfo pageParmInfo) {

        return serviceImp.getList(pageParmInfo.getPageNum(), pageParmInfo.getPageSize());

    }

    @RequestMapping("/alllistnum")
    public Integer alllistNum() {

        return serviceImp.getAllList().size();

    }
    @RequestMapping("/delete")
    public List<PackageEntity> delete(@RequestBody PackageEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(interfaceEntity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<PackageEntity> copy(@RequestBody PackageEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(interfaceEntity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<PackageEntity> add(@RequestBody PackageEntity interfaceEntity){
        serviceImp.add(interfaceEntity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<PackageEntity> update(@RequestBody PackageEntity interfaceEntity){
        serviceImp.update(interfaceEntity);
        return serviceImp.getAllList();
    }
}