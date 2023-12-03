package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.NetworkInterEntity;
import com.flow.network.service2.NetworkInterServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/networkinter")
public class NetworkInterfaceController {
    @Autowired
    private NetworkInterServiceImp serviceImp;

    @RequestMapping("/list")
    public ApiResponse list(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.getListByUID(pageParmInfo.getUid(),pageParmInfo.getPageNum(), pageParmInfo.getPageSize()));

    }
    @RequestMapping("/alllistnum")
    public ApiResponse allList(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.geAlltListByUID(pageParmInfo.getUid()).size());

    }
    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody NetworkInterEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody @Valid NetworkInterEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody NetworkInterEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
}