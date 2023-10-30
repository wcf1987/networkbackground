package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FieldsEntity;
import com.flow.network.domain2.MessBodyEntity;
import com.flow.network.service2.FieldsServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fields")
public class FieldsController {
    @Autowired
    private FieldsServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody MessBodyEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody FieldsEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/uploadfile")
    public ApiResponse uploadfile(@RequestParam("file") MultipartFile[] file){

        String path = "D:\\uploadfiles";
        String result = "";
        // 调用fileService保存文件
        try {
            result = Tools.storeFile(file[0], path);
        }catch (Exception e){

        }
        //serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody FieldsEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
    @RequestMapping("/getByID")
    public ApiResponse getByID(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.getByID(pageParmInfo.getPid()));

    }
}