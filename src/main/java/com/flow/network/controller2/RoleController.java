package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.RoleEntity;
import com.flow.network.service2.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody RoleEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getId());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody RoleEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody RoleEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
    @PostMapping("/getbyid")
    public ApiResponse getbyid(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.getByID(pageParmInfo.getId()));

    }
}