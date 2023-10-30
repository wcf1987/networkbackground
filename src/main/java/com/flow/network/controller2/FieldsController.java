package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain2.FieldsUploadEntity;
import com.flow.network.tools.*;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FieldsEntity;
import com.flow.network.domain2.MessBodyEntity;
import com.flow.network.service2.FieldsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
            List<FieldsUploadEntity> list = ExcelUtils.importExcel(result,1,1, FieldsUploadEntity.class);
            for(int i=0;i<list.size();i++){
                FieldsEntity fieldsEntity=new FieldsEntity(list.get(i));
                serviceImp.add(fieldsEntity);
            }

        }catch (Exception e){

            e.printStackTrace();
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