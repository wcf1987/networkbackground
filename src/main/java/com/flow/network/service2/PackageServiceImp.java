package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.PackageEntity;
import com.flow.network.mapper2.PackageDetailMapper;
import com.flow.network.mapper2.PackageMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageServiceImp
{

    @Autowired
    PackageMapper detailMapper;
    @Autowired
    PackageDetailMapper detailMapper2;
    @Autowired
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            detailMapper2.deleteByPID(Integer.parseInt(s));
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
        return num;
    }
    public String add(PackageEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByEName(entity.getEName(),0)>0){
            throw new ServiceException("引用名重复，请更改");
        }
        detailMapper.insert(entity);
        logimp.addInfo("添加封装头:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(PackageEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByEName(entity.getEName(),0)>0){
            throw new ServiceException("引用名重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新封装头:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<PackageEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<PackageEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<PackageEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<PackageEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        detailMapper2.deleteByPID(id);
        logimp.addInfo("删除封装头:"+id);
        return 1;
    }
}
