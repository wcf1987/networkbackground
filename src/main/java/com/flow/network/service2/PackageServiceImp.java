package com.flow.network.service2;

import com.flow.network.domain2.PackageEntity;
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


    public String add(PackageEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(PackageEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
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
        return 1;
    }
}
