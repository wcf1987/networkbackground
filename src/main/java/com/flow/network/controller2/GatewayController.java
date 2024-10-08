package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.GatewayDistributeEntity;
import com.flow.network.domain2.GatewayEntity;
import com.flow.network.mapper2.FlowDesignMapper;
import com.flow.network.service2.GatewayServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gateway")
public class GatewayController {
    @Autowired
    private GatewayServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody GatewayEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @RequestMapping("/deletedis")
    public ApiResponse deleteGatewayDistribute(@RequestBody GatewayDistributeEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteGatewayDistribute(detailEntity);
        return ApiResponse.success();

    }
    @PostMapping("/adddis")
    public ApiResponse addGatewayDistribute(@RequestBody GatewayDistributeEntity detailEntity){
        serviceImp.addGatewayDistribute(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/searchdis")
    public ApiResponse searchGatewayDistribute(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.searchGatewayDistribute(pageParmInfo.getName(),pageParmInfo.getGateid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
    @PostMapping("/searchdisall")
    public ApiResponse searchGatewayDistributeAll(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.searchGatewayDistributeAll());

    }
    @PostMapping("/dispatch")
    public ApiResponse dispatch(@RequestBody PageParmInfo pageParmInfo ){
        System.out.println("网关id："+pageParmInfo.getGatewayid()+"开始下发流程");
        String re=serviceImp.disPatchByGateID(pageParmInfo.getGatewayid());
        if(re.equals("")){
            return ApiResponse.fail(200,"流程下发出现错误");
        }else {
            return ApiResponse.success();
        }
    }
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody GatewayEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody GatewayEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }

}