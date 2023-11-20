package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.SerialInterEntity;
import com.flow.network.mapper2.SerialInterMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SerialInterServiceImp
{

    @Autowired
    SerialInterMapper detailMapper;


    public String add(SerialInterEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName())!=null){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(SerialInterEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<SerialInterEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<SerialInterEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<SerialInterEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<SerialInterEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
