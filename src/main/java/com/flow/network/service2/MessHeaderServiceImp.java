package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.MessHeaderEntity;
import com.flow.network.mapper2.MessHeaderMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessHeaderServiceImp
{

    @Autowired
    MessHeaderMapper detailMapper;


    public String add(MessHeaderEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(MessHeaderEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<MessHeaderEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<MessHeaderEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<MessHeaderEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<MessHeaderEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
