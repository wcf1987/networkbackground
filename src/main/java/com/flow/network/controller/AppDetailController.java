package com.flow.network.controller;

import com.flow.network.domain.AppDetailEntity;
import com.flow.network.service.AppDetailServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appDetail")
public class AppDetailController {
    @Autowired
    private AppDetailServiceImp detailServiceImp;
    @RequestMapping("/list")
    public List<AppDetailEntity> list(@RequestBody AppDetailEntity detailEntity) {

        return detailServiceImp.getListByPID(detailEntity.getAppID());

    }
    @RequestMapping("/delete")
    public String delete(@RequestBody AppDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        detailServiceImp.deleteByID(detailEntity.getId());
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody AppDetailEntity detailEntity){
        detailServiceImp.add(detailEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody AppDetailEntity detailEntity){
        detailServiceImp.update(detailEntity);
        return Tools.SUCCESS;
    }
}