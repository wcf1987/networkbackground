package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.MessBodyEntity;
import com.flow.network.mapper2.MessBodyMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessBodyServiceImp
{

    @Autowired
    MessBodyMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
        return num;
    }
    public String add(MessBodyEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加消息体:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(MessBodyEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新消息体:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<MessBodyEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<MessBodyEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<MessBodyEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<MessBodyEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除消息体:"+id);
        return 1;
    }
}
