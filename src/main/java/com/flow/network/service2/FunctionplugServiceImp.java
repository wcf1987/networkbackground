package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.FunctionplugEntity;
import com.flow.network.domain2.PlugFileEntity;
import com.flow.network.mapper2.FunctionplugMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class FunctionplugServiceImp
{

    @Autowired
    FunctionplugMapper detailMapper;
    @Value("${plug.file.dir}")
    private String PlugFileDir;
    @Autowired
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
        return num;
    }
    public String add(FunctionplugEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加函数插件:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(FunctionplugEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新函数插件:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<FunctionplugEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<FunctionplugEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<FunctionplugEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<FunctionplugEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<PlugFileEntity> getPlugFiles(PageParmInfo pageParmInfo){
        List<PlugFileEntity> list=new ArrayList<PlugFileEntity>();
        File dir=new File(PlugFileDir);
        File[] files=dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                  //  searchFiles(file); // 递归调用，继续查询子目录
                } else {
                    PlugFileEntity temp=new PlugFileEntity();
                    temp.setFileName(file.getName());
                    //temp.setType(file.get);
                    temp.setFilePath(file.getAbsolutePath());
                    list.add(temp);
                    //System.out.println(file.getAbsolutePath()); // 输出文件的绝对路径
                }
            }
        }
        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除函数插件:"+id);
        return 1;
    }
}
