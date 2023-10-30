package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.FieldsDetailUploadEntity;
import com.flow.network.domain2.FieldsEntity;
import com.flow.network.domain2.FieldsUploadEntity;
import com.flow.network.service2.FieldsDetailServiceImp;
import com.flow.network.tools.ExcelUtils;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/fieldsdetail")
public class FieldsDetailController {
    @Autowired
    private FieldsDetailServiceImp serviceImp;


    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody FieldsDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody FieldsDetailEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/uploadfile")
    public ApiResponse uploadfile(@RequestParam("file") MultipartFile[] file, @RequestParam Integer pid){

        String path = "D:\\uploadfiles";
        String result = "";
        // 调用fileService保存文件
        try {
            result = Tools.storeFile(file[0], path);
            List<FieldsDetailUploadEntity> list = ExcelUtils.importExcel(result,1,1, FieldsDetailUploadEntity.class);
            for(int i=0;i<list.size();i++){
                if(list.get(i).getDUINO()==null){
                    continue;
                }
                FieldsDetailEntity fieldsDetailEntity=new FieldsDetailEntity(list.get(i));
                fieldsDetailEntity.setDFIID(pid);
                serviceImp.add(fieldsDetailEntity);
            }

        }catch (Exception e){

            e.printStackTrace();
        }
        //serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody FieldsDetailEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
}