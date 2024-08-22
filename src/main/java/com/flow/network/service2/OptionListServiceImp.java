package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.BuiltNodeEntity;
import com.flow.network.domain2.OptionListEntity;
import com.flow.network.mapper2.BuiltNodeMapper;
import com.flow.network.mapper2.OptionListMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionListServiceImp
{

    @Autowired
    OptionListMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除内置节点:"+String.valueOf(num)+"条");
        return num;
    }
    public String add(OptionListEntity entity) {
        entity.setValue(entity.getLabel());
        if(detailMapper.selectByName(entity.getLabel(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByValue(entity.getValue(),0)>0){
            throw new ServiceException("value重复，请更改");
        }
        //System.out.print("getlist");
        entity.setValue(entity.getLabel());
        entity.setType("ParseMethod");
        detailMapper.insert(entity);
        logimp.addInfo("添加"+entity.getType()+":"+entity.getLabel());
        return Tools.SUCCESS;
    }
    public String update(OptionListEntity entity) {
        //System.out.print("getlist");
        entity.setValue(entity.getLabel());
        if(detailMapper.selectByName(entity.getLabel(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByValue(entity.getValue(),entity.getID())>0){
            throw new ServiceException("value重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新"+entity.getType()+":"+entity.getLabel());
        return Tools.SUCCESS;
    }
    public List<OptionListEntity> search(String name,  Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<OptionListEntity> list=detailMapper.searchByName(name,"ParseMethod");

        return list;
    }
    public List<OptionListEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<OptionListEntity> list=detailMapper.searchByName(name,"ParseMethod");

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除全局变量:"+id);
        return 1;
    }
}
