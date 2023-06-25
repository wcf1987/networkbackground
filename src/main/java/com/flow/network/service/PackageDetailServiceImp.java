package com.flow.network.service;

import com.flow.network.domain.PackageDetailEntity;
import com.flow.network.mapper.PackageDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageDetailServiceImp
{

    @Autowired
    PackageDetailMapper packageDetailMapper;


    public List<PackageDetailEntity> getListByPID(Integer id, Integer pageNum, Integer pageSize) {
        System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<PackageDetailEntity> list=packageDetailMapper.getList(id);
        return list;
    }
    public List<PackageDetailEntity> geAlltListByPID(Integer id) {
        System.out.print("getlist");
        List<PackageDetailEntity> list=packageDetailMapper.getList(id);
        return list;
    }

    public String add(PackageDetailEntity packageDetailEntity) {
        System.out.println("插入packageDetail");
        packageDetailMapper.insert(packageDetailEntity);
        return Tools.SUCCESS;
    }
    public String update(PackageDetailEntity packageDetailEntity) {
        //System.out.print("getlist");
        packageDetailMapper.updateByPrimaryKey(packageDetailEntity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        packageDetailMapper.delete(id);
        return 1;
    }
}
