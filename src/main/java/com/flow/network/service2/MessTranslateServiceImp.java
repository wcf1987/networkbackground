package com.flow.network.service2;

import com.flow.network.domain2.MessTranslateEntity;
import com.flow.network.mapper2.MessTraslateMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessTranslateServiceImp
{

    @Autowired
    MessTraslateMapper detailMapper;


    public String add(MessTranslateEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(MessTranslateEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<MessTranslateEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<MessTranslateEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<MessTranslateEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<MessTranslateEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
