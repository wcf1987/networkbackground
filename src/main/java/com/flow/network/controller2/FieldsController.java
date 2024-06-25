package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.config.ServiceException;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.*;
import com.flow.network.service2.FieldsDetailServiceImp;
import com.flow.network.service2.FieldsServiceImp;
import com.flow.network.tools.ExcelUtils;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fields")
public class FieldsController {
    @Autowired
    private FieldsServiceImp serviceImp;
    @Autowired
    private FieldsDetailServiceImp serviceImp2;

    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody MessBodyEntity detailEntity) {
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
    public ApiResponse add(@RequestBody FieldsEntity detailEntity) {
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/uploadfile")
    public ApiResponse uploadfile(@RequestParam("file") MultipartFile[] file) {

        String path = "D:\\uploadfiles";


        File fileupload = new File("upload" + File.separator);
        try{
            if (!fileupload.exists()) {
                fileupload.mkdirs();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        path=fileupload.getAbsolutePath();
        System.out.println(path);
        String result = "";
        List<UploadErrorEntity> errorlist;
        int sucessNum=0;
        // 调用fileService保存文件
        List<FieldsEntity> listdb = serviceImp.searchAll("", 0);
        try {
            result = Tools.storeFile(file[0], path);
            List<FieldsUploadEntity> list = ExcelUtils.importExcel(result, 1, 1, FieldsUploadEntity.class);
             errorlist = CheckDFI(listdb, list);
            if (errorlist.size() > 0) {
            //    return ApiResponse.fail(ResponseCode.UPLOAD_ERROR.getCode(), ResponseCode.UPLOAD_ERROR.getMessage(), errorlist);
            }

            for (int i = 0; i < list.size(); i++) {
                int flag=0;
                for(int j=0;j<errorlist.size();j++){
                    if (i==(Integer.valueOf(errorlist.get(j).getIndex())-1)){
                        flag=1;
                    }
                }
                if(flag==0) {
                    FieldsEntity fieldsEntity = new FieldsEntity(list.get(i));
                    serviceImp.add(fieldsEntity);
                    sucessNum++;
                }

            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        //serviceImp.add(detailEntity);
        String msg="上传成功"+sucessNum+"条数据,失败"+errorlist.size()+"条数据";
        return ApiResponse.success(msg,errorlist);
    }

    private List<UploadErrorEntity> CheckDFI(List<FieldsEntity> listdb, List<FieldsUploadEntity> list) {
        List<UploadErrorEntity> errorlist = new ArrayList<UploadErrorEntity>();
        int flag = 0;
        for (int i = 0; i < list.size(); i++) {


            if (list.get(i).getIdNO() == null || list.get(i).getIdNO().equals("")) {
                errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI标示号为空"));

            }
            if (list.get(i).getName() == null || list.get(i).getName().equals("")) {
                errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI名称为空"));

            }

        }
        if (errorlist.size() > 0) {
            return errorlist;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                FieldsUploadEntity f1 = list.get(i);
                FieldsUploadEntity f2 = list.get(j);
                if (f1.getIdNO().equals(f2.getIdNO())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI标示号和第" + String.valueOf(j + 1) + "行重复"));

                }
                if (f1.getName().equals(f2.getName())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI名称和第" + String.valueOf(j + 1) + "行重复"));

                }

            }
        }
        if (errorlist.size() > 0) {
            return errorlist;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listdb.size(); j++) {

                if (list.get(i).getIdNO() == null || list.get(i).getIdNO().equals("")) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI标示号为空"));

                } else {
                    if (list.get(i).getIdNO().equals(listdb.get(j).getIDNO())) {
                        errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI标示号重复"));
                    }
                }
                if (list.get(i).getName() == null || list.get(i).getName().equals("")) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI名称为空"));

                } else {
                    if (list.get(i).getName().equals(listdb.get(j).getName())) {
                        errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DFI名称重复"));
                    }
                }

            }
        }
        return errorlist;
    }

    @PostMapping("/uploadfileall")
    public ApiResponse uploadfileAll(@RequestParam("file") MultipartFile[] file) throws IOException {
        String path = "D:\\uploadfiles";


        File fileupload = new File("upload" + File.separator);
        try{
            if (!fileupload.exists()) {
                fileupload.mkdirs();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        path=fileupload.getAbsolutePath();


        String result = "";
        List<FieldsEntity> listdfidb = serviceImp.searchAll("", 0);
        List<FieldsDetailEntity> listduidb = serviceImp2.searchAll("", 0);
        // 调用fileService保存文件

            result = Tools.storeFile(file[0], path);
            List<FieldsDetailUploadAllEntity> list = ExcelUtils.importExcel(result, 1, 1, FieldsDetailUploadAllEntity.class);
            List<UploadErrorEntity> errorlist = CheckDUI(listduidb, list);
            if (errorlist.size() > 0) {
               // return ApiResponse.fail(ResponseCode.UPLOAD_ERROR.getCode(), ResponseCode.UPLOAD_ERROR.getMessage(), errorlist);
            }

        List<FieldsDetailEntity> listsucess=new ArrayList<FieldsDetailEntity>();
        for (int i = 0; i < list.size(); i++) {
            int flag=0;
            for(int j=0;j<errorlist.size();j++){
                if (i==(Integer.valueOf(errorlist.get(j).getIndex())-1)){
                    flag=1;
                }
            }
            if(flag==0) {
                //FieldsEntity fieldsEntity = new FieldsEntity(list.get(i));
                listsucess.add(new FieldsDetailEntity(list.get(i)));

            }

        }

            for (int i = 0; i < listsucess.size(); i++) {

                FieldsDetailEntity fieldsDetailEntity = listsucess.get(i);
                //fieldsDetailEntity.setDFIID(pid);
                Integer pid = checkDFINO(listdfidb, fieldsDetailEntity.getDFINO());
                fieldsDetailEntity.setDFIID(pid);
                serviceImp2.add(fieldsDetailEntity);
            }


        //serviceImp.add(detailEntity);
        String msg="上传成功"+listsucess.size()+"条数据,失败"+errorlist.size()+"条数据";
        return ApiResponse.success(msg,errorlist);
    }

    private Integer checkDFINO(List<FieldsEntity> listdfidb, String dfino) {
        for (int i = 0; i < listdfidb.size(); i++) {
            if (listdfidb.get(i).getIDNO().equals(dfino)) {
                return listdfidb.get(i).getID();
            }
        }
        FieldsEntity f1=new FieldsEntity(dfino);
        Integer id=serviceImp.add2(f1);
        listdfidb.add(f1);
        return id;
    }

    private List<UploadErrorEntity> CheckDUI(List<FieldsDetailEntity> listdb, List<FieldsDetailUploadAllEntity> list) {
        List<UploadErrorEntity> errorlist = new ArrayList<UploadErrorEntity>();
        for (int i = 0; i < list.size(); i++) {
            FieldsDetailUploadAllEntity f = list.get(i);

            if (f.getDUINO() == null || f.getDUINO().equals("")) {
                errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号为空"));

            }
            if (f.getName() == null || f.getName().equals("")) {
                errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称为空"));

            }

        }
        if (errorlist.size() > 0) {
            return errorlist;
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                FieldsDetailUploadAllEntity f1 = list.get(i);
                FieldsDetailUploadAllEntity f2 = list.get(j);
                if (f1.getDUINO().equals(f2.getDUINO()) && f1.getDFINO().equals(f2.getDFINO())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号和第" + String.valueOf(j + 1) + "行重复"));

                }
                if (f1.getName().equals(f2.getName()) && f1.getDFINO().equals(f2.getDFINO())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称和第" + String.valueOf(j + 1) + "行重复"));

                }

            }
        }
        if (errorlist.size() > 0) {
            return errorlist;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listdb.size(); j++) {

                FieldsDetailUploadAllEntity f1 = list.get(i);
                FieldsDetailEntity f2 = listdb.get(j);
                if (f1.getDUINO() == null || f1.getDUINO().equals("")) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号为空"));

                } else {
                    if (f1.getDUINO().equals(f2.getDUINO()) && f1.getDFINO().equals(f2.getDFINO())) {
                        errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号重复"));
                    }
                }
                if (f1.getName() == null || f1.getName().equals("")) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称为空"));

                } else {
                    if (f1.getName().equals(f2.getName()) && f1.getDFINO().equals(f2.getDFINO())) {
                        errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称重复"));
                    }
                }

            }
        }
        return errorlist;
    }

    @PostMapping("/update")
    public ApiResponse update(@RequestBody FieldsEntity detailEntity) {
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo) {
        return ApiResponse.success(serviceImp.search(pageParmInfo));

    }

    @RequestMapping("/getByID")
    public ApiResponse getByID(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.getByID(pageParmInfo.getPid()));

    }
}