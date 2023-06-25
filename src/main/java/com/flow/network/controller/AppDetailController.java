package com.flow.network.controller;

import com.flow.network.domain.AppDetailEntity;
import com.flow.network.domain.InterfaceDetailEntity;
import com.flow.network.domain.PageParmInfo;
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
    public List<AppDetailEntity> list(@RequestBody PageParmInfo pageParmInfo) {

        return detailServiceImp.getListByPID(pageParmInfo.getPid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize());

    }
    @RequestMapping("/alllistnum")
    public Integer allList(@RequestBody PageParmInfo pageParmInfo) {

        return detailServiceImp.geAlltListByPID(pageParmInfo.getPid()).size();

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