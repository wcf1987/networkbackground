package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.PackageDetailEntity;
import com.flow.network.service2.PackageDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/packagedetail")
public class PackageDetailController {
    @Autowired
    private PackageDetailServiceImp serviceImp;
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }

    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody PackageDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody PackageDetailEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody PackageDetailEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
}