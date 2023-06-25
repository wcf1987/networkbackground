package com.flow.network.controller;

import com.flow.network.domain.PackageDetailEntity;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.service.PackageDetailServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/packageDetail")
public class PackageDetailController {
    @Autowired
    private PackageDetailServiceImp packageDetailServiceImp;


    @RequestMapping("/list")
    public List<PackageDetailEntity> list(@RequestBody PageParmInfo pageParmInfo) {

        return packageDetailServiceImp.getListByPID(pageParmInfo.getPid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize());

    }
    @RequestMapping("/alllistnum")
    public Integer allList(@RequestBody PageParmInfo pageParmInfo) {

        return packageDetailServiceImp.geAlltListByPID(pageParmInfo.getPid()).size();

    }

    @RequestMapping("/delete")
    public String delete(@RequestBody PackageDetailEntity packageDetailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        packageDetailServiceImp.deleteByID(packageDetailEntity.getId());
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody PackageDetailEntity packageDetailEntity){
        packageDetailServiceImp.add(packageDetailEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody PackageDetailEntity packageDetailEntity){
        packageDetailServiceImp.update(packageDetailEntity);
        return Tools.SUCCESS;
    }
}