package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FunctionplugEntity;
import com.flow.network.domain2.SysplugEntity;
import com.flow.network.mapper2.FunctionplugMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FunctionplugServiceImp
{

    @Autowired
    FunctionplugMapper detailMapper;

    @Autowired
    private LogServiceImp logimp;
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
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除函数插件:"+id);
        return 1;
    }
}
