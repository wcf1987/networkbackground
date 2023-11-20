package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.mapper2.FieldsDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FieldsDetailServiceImp
{

    @Autowired
    FieldsDetailMapper detailMapper;


    public String add(FieldsDetailEntity entity) {
        if(detailMapper.selectByName(entity.getName())!=null){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByDFIDUI(entity.getDFIID(),entity.getDUINO())!=null){
            throw new ServiceException("DUI标识重复，请更改");
        }

        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(FieldsDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<FieldsDetailEntity> search(String name,Integer uid,Integer pid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<FieldsDetailEntity> list=detailMapper.searchByName(name,uid,pid);

        return list;
    }
    public List<FieldsDetailEntity> searchAll(String name,Integer uid,Integer pid) {
        //System.out.print("getlist");
        List<FieldsDetailEntity> list=detailMapper.searchByName(name,uid,pid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
