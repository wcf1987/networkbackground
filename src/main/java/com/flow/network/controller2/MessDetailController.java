package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.MessDetailEntity;
import com.flow.network.service2.MessDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messdetail")
public class MessDetailController {
    @Autowired
    private MessDetailServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getTtype(),pageParmInfo.getNestid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody FieldsDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody MessDetailEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody MessDetailEntity detailEntity){
        serviceImp.update(detailEntity);

        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getTtype(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize(),pageParmInfo.getNestid()));

    }
    @PostMapping("/searchName")
    public ApiResponse searchName(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.searchName(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getTtype(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize(),pageParmInfo.getNestid()));

    }
}