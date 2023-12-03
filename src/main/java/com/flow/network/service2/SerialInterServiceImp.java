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
    private LogServiceImp logimp;
    @Autowired
    SerialInterMapper detailMapper;


    public String add(SerialInterEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.insert(entity);
        //logimp.addInfo("添加串口:"+entity.getName());
        logimp.addInfo("添加串口:"+entity.getName());
        return Tools.SUCCESS;
    }
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
        return num;
    }
    public String update(SerialInterEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新串口:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<SerialInterEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<SerialInterEntity> list=detailMapper.searchByName(name,uid);
        //logimp.addInfo("查询:");
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
        logimp.addInfo("删除串口ID:"+id);
        return 1;
    }
}
