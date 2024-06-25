package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.FieldsStatisticsEntity;
import com.flow.network.mapper2.FieldsDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class FieldsDetailServiceImp
{

    @Autowired
    FieldsDetailMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;

    public String add(FieldsDetailEntity entity) {

        if(detailMapper.selectByName(entity.getName(),entity.getDFIID(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByDFIDUI(entity.getDFIID(),entity.getDUINO(),0)>0){
            throw new ServiceException("DUI标识重复，请更改");
        }

        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加DUI:"+entity.getDUINO());
        return Tools.SUCCESS;
    }
    public String update(FieldsDetailEntity entity) {
        if(detailMapper.selectByName(entity.getName(),entity.getDFIID(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        if(detailMapper.selectByDFIDUI(entity.getDFIID(),entity.getDUINO(),entity.getID())>0){
            throw new ServiceException("DUI标识重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新DUI:"+entity.getDUINO());
        return Tools.SUCCESS;
    }
    public List<FieldsDetailEntity> search(String name,Integer uid,Integer pid,Integer pageNum, Integer pageSize,String order) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<FieldsDetailEntity> list;
        if(pid==0){
          list = detailMapper.searchByNameNODFI(name, uid, pid, order);

        }else {
             list = detailMapper.searchByName(name, uid, pid, order);
        }
        return list;
    }
    public List<FieldsDetailEntity> searchAll(String name,Integer uid,Integer pid) {
        //System.out.print("getlist");
        List<FieldsDetailEntity> list=detailMapper.searchByName(name,uid,pid,"asc");

        return list;
    }
    public List<FieldsDetailEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<FieldsDetailEntity> list=detailMapper.searchByNameAll(name,uid);

        return list;
    }
    public FieldsDetailEntity getById(Integer id) {
        //System.out.print("getlist");
        FieldsDetailEntity entity=detailMapper.getById(id);

        return entity;
    }

    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除DUI:"+id);
        return 1;
    }

    public List<FieldsStatisticsEntity> statistics( Integer pageNum, Integer pageSize) {
        Integer count=detailMapper.searchAllDUI();
        //PageHelper.startPage(pageNum, pageSize);
        List<FieldsStatisticsEntity> list=detailMapper.statistics(count);

        Collections.sort(list);
        int start=(pageNum-1)*pageSize;
        int end=start+pageSize;
        if(end>=list.size()){
            end=list.size()-1;
        }
        List<FieldsStatisticsEntity> list2=list.subList(start,end);
        for(FieldsStatisticsEntity ft:list2){
            ft.setDUIPer((Tools.formatNumber(Double.valueOf(ft.getDUIPer())*100))+"%");
        }
        return list2;
    }
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:"+String.valueOf(num)+"条");
        return num;
    }
    public List<FieldsStatisticsEntity> statisticsSize() {
        List<FieldsStatisticsEntity> list=detailMapper.statistics(1);
        return list;
    }
}
