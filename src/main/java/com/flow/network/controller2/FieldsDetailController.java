package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.config.ServiceException;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.FieldsDetailUploadEntity;
import com.flow.network.domain2.FieldsEntity;
import com.flow.network.domain2.UploadErrorEntity;
import com.flow.network.mapper2.FieldsMapper;
import com.flow.network.service2.FieldsDetailServiceImp;
import com.flow.network.tools.ExcelUtils;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fieldsdetail")
public class FieldsDetailController {
    @Autowired
    private FieldsDetailServiceImp serviceImp;

    @Autowired
    FieldsMapper detailMapper2;
    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(), pageParmInfo.getUid(), pageParmInfo.getPid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody FieldsDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getID());
        return ApiResponse.success();

    }

    @PostMapping("/add")
    public ApiResponse add(@RequestBody FieldsDetailEntity detailEntity) {
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/uploadfile")
    public ApiResponse uploadfile(@RequestParam("file") MultipartFile[] file, @RequestParam Integer pid) {

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
        // 调用fileService保存文件
        List<FieldsDetailEntity> listdb = serviceImp.searchAll("", 0, pid);
        List<FieldsDetailEntity> listsucess;
        List<UploadErrorEntity> errorlist;
        try {
            result = Tools.storeFile(file[0], path);
            List<FieldsDetailUploadEntity> list = ExcelUtils.importExcel(result, 1, 1, FieldsDetailUploadEntity.class);
             errorlist = CheckDUI(listdb, list);
            if (errorlist.size() > 0) {
              //  return ApiResponse.fail(ResponseCode.UPLOAD_ERROR.getCode(), ResponseCode.UPLOAD_ERROR.getMessage(), errorlist);
            }

             listsucess=new ArrayList<FieldsDetailEntity>();
            for (int i = 0; i < list.size(); i++) {
                int flag=0;
                for(int j=0;j<errorlist.size();j++){
                    if (i==(Integer.valueOf(errorlist.get(j).getIndex())-1)){
                        flag=1;
                    }
                }
                if(flag==0 && list.get(i).getDUINO()!=null) {
                    //FieldsEntity fieldsEntity = new FieldsEntity(list.get(i));
                    listsucess.add(new FieldsDetailEntity(list.get(i)));

                }

            }


            for (int i = 0; i < listsucess.size(); i++) {
                if (list.get(i).getDUINO() == null) {
                    continue;
                }
                FieldsDetailEntity fieldsDetailEntity = listsucess.get(i);
                fieldsDetailEntity.setDFIID(pid);
                serviceImp.add(fieldsDetailEntity);
            }

        } catch (Exception e) {

            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        String msg="上传成功"+listsucess.size()+"条数据,失败"+errorlist.size()+"条数据";
        return ApiResponse.success(msg,errorlist);
    }

    private List<UploadErrorEntity> CheckDUI(List<FieldsDetailEntity> listdb, List<FieldsDetailUploadEntity> list) {
        List<UploadErrorEntity> errorlist = new ArrayList<UploadErrorEntity>();
        for (int i = 0; i < list.size(); i++) {
            FieldsDetailUploadEntity f = list.get(i);

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
                FieldsDetailUploadEntity f1 = list.get(i);
                FieldsDetailUploadEntity f2 = list.get(j);
                if (f1.getDUINO().equals(f2.getDUINO())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI标示号和第" + String.valueOf(j + 1) + "行重复"));

                }
                if (f1.getName().equals(f2.getName())) {
                    errorlist.add(new UploadErrorEntity(String.valueOf(i + 1), "DUI名称和第" + String.valueOf(j + 1) + "行重复"));

                }

            }
        }
        if (errorlist.size() > 0) {
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
    public ApiResponse update(@RequestBody FieldsDetailEntity detailEntity) {
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }

    @PostMapping("/statistics")
    public ApiResponse statistics(@RequestBody PageParmInfo pageParmInfo) {
        return ApiResponse.success(serviceImp.statistics(pageParmInfo.getPageNum(), pageParmInfo.getPageSize()));

    }

    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo) {
        return ApiResponse.success(serviceImp.search( pageParmInfo.getName(), pageParmInfo.getUid(), pageParmInfo.getPid(), pageParmInfo.getPageNum(), pageParmInfo.getPageSize(), pageParmInfo.getOrder()));

    }

    @PostMapping("/getById")
    public ApiResponse getById(@RequestBody PageParmInfo pageParmInfo) {
        FieldsDetailEntity entity=serviceImp.getById( pageParmInfo.getId());
        FieldsEntity entity1=detailMapper2.selectByPrimaryKey(entity.getDFIID());
        entity.setDFINO(entity1.getIDNO());
        entity.setDFIVersion(entity1.getVersion());
        return ApiResponse.success(entity);

    }
    @RequestMapping("/delids")
    public ApiResponse delids(@RequestBody List<String> ids) {
        //List<InterfaceEntity> u = new ArrayList<>();
        return ApiResponse.success(serviceImp.deleteByIDS(ids));


    }
    @PostMapping("/statisticsSize")
    public ApiResponse statisticsSize(@RequestBody PageParmInfo pageParmInfo) {
        return ApiResponse.success(serviceImp.statisticsSize().size());

    }
}