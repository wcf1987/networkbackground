package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.SpecialNodeEntity;
import com.flow.network.mapper2.SpecialNodeMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpecialNodeServiceImp
{

    @Autowired
    SpecialNodeMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;

    public String add(SpecialNodeEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByCode(entity.getCode(),0)>0){
            throw new ServiceException("变量名重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加特殊节点:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(SpecialNodeEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByCode(entity.getCode(),entity.getID())>0){
            throw new ServiceException("变量名重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新特殊节点:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<SpecialNodeEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<SpecialNodeEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<SpecialNodeEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<SpecialNodeEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除特殊节点:"+id);
        return 1;
    }
}
