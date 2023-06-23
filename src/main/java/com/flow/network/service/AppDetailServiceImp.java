package com.flow.network.service;

import com.flow.network.domain.AppDetailEntity;
import com.flow.network.mapper.AppDetailMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppDetailServiceImp
{

    @Autowired
    AppDetailMapper detailMapper;
    public List<AppDetailEntity> getListByPID(Integer id) {
        System.out.print("getlist");
        List<AppDetailEntity> list=detailMapper.getList(id);
        return list;
    }
    public String add(AppDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(AppDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
