package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FieldsEntity;
import com.flow.network.mapper2.FieldsMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FieldsServiceImp
{

    @Autowired
    FieldsMapper detailMapper;

    @Autowired
    private LogServiceImp logimp;
    public Integer add(FieldsEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByIDNO(entity.getIDNO(),0)>0){
            throw new ServiceException("标识号重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加DFI:"+entity.getIDNO());
        return entity.getID();
    }
    public Integer add2(FieldsEntity entity) {


        //System.out.print("getlist");
        detailMapper.insert(entity);
        return entity.getID();
    }
    public String update(FieldsEntity entity) {
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByIDNO(entity.getIDNO(),entity.getID())>0){
            throw new ServiceException("标识号重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新DFI:"+entity.getIDNO());
        return Tools.SUCCESS;
    }
    public List<FieldsEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize,String order) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<FieldsEntity> list=detailMapper.searchByName(name,uid,order);

        return list;
    }
    public List<FieldsEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<FieldsEntity> list=detailMapper.searchByName(name,uid,"asc");

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
        return num;
    }
    public FieldsEntity getByID(Integer id) {
        //System.out.print("deleteByID");
        FieldsEntity fe=detailMapper.selectByPrimaryKey(id);
        logimp.addInfo("删除DFI:"+id);
        return fe;
    }

}
