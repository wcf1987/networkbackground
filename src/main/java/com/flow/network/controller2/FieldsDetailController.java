package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.config.ResponseCode;
import com.flow.network.config.ServiceException;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.*;
import com.flow.network.service2.FieldsDetailServiceImp;
import com.flow.network.tools.ExcelUtils;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
        List<FieldsDetailEntity> listdb=serviceImp.searchAll("",0,pid);
        try {
            result = Tools.storeFile(file[0], path);
            List<FieldsDetailUploadEntity> list = ExcelUtils.importExcel(result,1,1, FieldsDetailUploadEntity.class);
            List<UploadErrorEntity> errorlist=CheckDUI(listdb,list);
            if(errorlist.size()>0){
                return ApiResponse.fail(ResponseCode.UPLOAD_ERROR.getCode(),ResponseCode.UPLOAD_ERROR.getMessage(),errorlist );
            }
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
            throw new ServiceException(e.getMessage());
        }
        //serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    private  List<UploadErrorEntity> CheckDUI(List<FieldsDetailEntity> listdb, List<FieldsDetailUploadEntity> list) {
        List<UploadErrorEntity> errorlist=new ArrayList<UploadErrorEntity>();
        for (int i = 0; i < list.size(); i++) {
            FieldsDetailUploadEntity f=list.get(i);

            if (f.getDUINO() == null || f.getDUINO().equals("")) {
                errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号为空"));

            }
            if (f.getName() == null || f.getName().equals("")) {
                errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称为空"));

            }

        }
        if(errorlist.size()>0){
            return errorlist;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                FieldsDetailUploadEntity f1=list.get(i);
                FieldsDetailUploadEntity f2=list.get(j);
                if (f1.getDUINO().equals(f2.getDUINO())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号和第"+String.valueOf(j+1)+"行重复"));

                }
                if (f1.getName().equals(f2.getName())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称和第"+String.valueOf(j+1)+"行重复"));

                }

            }
        }
        if(errorlist.size()>0){
            return errorlist;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listdb.size(); j++) {

                if (list.get(i).getDUINO() == null || list.get(i).getDUINO().equals("")) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号为空"));

                } else {
                    if (list.get(i).getDUINO().equals(listdb.get(j).getDUINO())) {
                        errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号重复"));
                    }
                }
                if (list.get(i).getName() == null || list.get(i).getName().equals("")) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称为空"));

                } else {
                    if (list.get(i).getName().equals(listdb.get(j).getName())) {
                        errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称重复"));
                    }
                }

            }
        }
        return errorlist;
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody FieldsDetailEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize(),pageParmInfo.getOrder()));

    }
}