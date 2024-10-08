package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FlowDistributionEntity;
import com.flow.network.service2.FlowDistributionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flowdistribution")
public class FlowDistributionController {
    @Autowired
    private FlowDistributionServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody FlowDistributionEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody FlowDistributionEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody FlowDistributionEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @PostMapping("/dispatch")
    public ApiResponse dispatch(@RequestBody PageParmInfo pageParmInfo ){
        System.out.println("网关id："+pageParmInfo.getGatewayid()+"开始下发流程");
        String re="";
        //String re=serviceImp.disPatchByGateID(pageParmInfo.getGatewayid());
        if(re.equals("")){
            return ApiResponse.fail();
        }else {
            return ApiResponse.success();
        }
    }
}