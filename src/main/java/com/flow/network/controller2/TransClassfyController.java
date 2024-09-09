package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.TransClassfyEntity;
import com.flow.network.service2.TransClassfyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transclassfy")
public class TransClassfyController {
    @Autowired
    private TransClassfyServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getType(),pageParmInfo.getUid()).size());

    }
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody TransClassfyEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody TransClassfyEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody TransClassfyEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getType(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }

    @PostMapping("/searchWithChildren")
    public ApiResponse searchWithChildren(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.searchWithChildren(pageParmInfo.getName(),pageParmInfo.getType(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
}