package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.GlobalVarEntity;
import com.flow.network.mapper2.GlobalVarMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GlobalVarServiceImp
{

    @Autowired
    GlobalVarMapper detailMapper;


    public String add(GlobalVarEntity entity) {
        if(detailMapper.selectByName(entity.getName())!=null){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(GlobalVarEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<GlobalVarEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<GlobalVarEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<GlobalVarEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<GlobalVarEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
