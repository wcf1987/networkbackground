package com.flow.network.controller;

import com.flow.network.domain.AppEntity;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.service.AppServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/app")
public class AppController {
    @Autowired
    private AppServiceImp serviceImp;
    @RequestMapping("/list")
    public List<AppEntity> list(@RequestBody PageParmInfo pageParmInfo) {

        return serviceImp.getList(pageParmInfo.getPageNum(), pageParmInfo.getPageSize());

    }

    @RequestMapping("/alllistnum")
    public Integer alllistNum() {

        return serviceImp.getAllList().size();

    }



    @RequestMapping("/delete")
    public List<AppEntity> delete(@RequestBody AppEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(entity.getId());
        return serviceImp.getAllList();

    }
    @RequestMapping("/copy")
    public List<AppEntity> copy(@RequestBody AppEntity entity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.copyByID(entity);
        return serviceImp.getAllList();

    }
    @PostMapping("add")
    public List<AppEntity> add(@RequestBody AppEntity entity){
        serviceImp.add(entity);
        return serviceImp.getAllList();
    }
    @PostMapping("update")
    public List<AppEntity> update(@RequestBody AppEntity entity){
        serviceImp.update(entity);
        return serviceImp.getAllList();
    }
}