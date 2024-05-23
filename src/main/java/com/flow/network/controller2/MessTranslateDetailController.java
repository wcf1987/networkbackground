package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.MessDetailEntity;
import com.flow.network.domain2.MessTraslateDetailEntity;
import com.flow.network.service2.MessTranslateDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/messtranslatedetail")
public class MessTranslateDetailController {
    @Autowired
    private MessTranslateDetailServiceImp serviceImp;

    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getTtype()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody FieldsDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();

        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody MessDetailEntity detailEntity){

        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody MessTraslateDetailEntity detailEntity){
        serviceImp.update(detailEntity);

        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getTtype(),pageParmInfo.getTransid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
    @PostMapping("/searchAllDUITrans")
    public ApiResponse searchAllDUITrans(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.searchAllDUITrans());

    }
}