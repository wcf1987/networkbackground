package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.BuiltNodeEntity;
import com.flow.network.domain2.GlobalVarEntity;
import com.flow.network.mapper2.BuiltNodeMapper;
import com.flow.network.mapper2.GlobalVarMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BuiltNodeServiceImp
{

    @Autowired
    BuiltNodeMapper detailMapper;
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
    public String add(BuiltNodeEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("节点名称重复，请更改");
        }
        if(detailMapper.selectByCode(entity.getCode(),0)>0){
            throw new ServiceException("变量名重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加内置节点:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(BuiltNodeEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("内置节点名称重复，请更改");
        }
        if(detailMapper.selectByCode(entity.getCode(),entity.getID())>0){
            throw new ServiceException("变量名重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新内置节点:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<BuiltNodeEntity> search(String name, Integer uid, Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<BuiltNodeEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<BuiltNodeEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<BuiltNodeEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除全局变量:"+id);
        return 1;
    }
}
