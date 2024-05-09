package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.TransTemplateEntity;
import com.flow.network.service2.TransTemplateServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transtemplate")
public class TransTemplateController {
    @Autowired
    private TransTemplateServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody TransTemplateEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody TransTemplateEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/copy")
    public ApiResponse copy(@RequestBody TransTemplateEntity detailEntity){
        serviceImp.copy(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody TransTemplateEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/applyflow")
    public ApiResponse applyFlow(@RequestBody TransTemplateEntity detailEntity){
        serviceImp.applyFlow(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/updatejson")
    public ApiResponse updateJson(@RequestBody TransTemplateEntity detailEntity){

        return ApiResponse.success(serviceImp.updateJson(detailEntity));

    }

    @PostMapping("/getbyid")
    public ApiResponse getFlowByID(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.getFlowByID(pageParmInfo.getId()));

    }

    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
}