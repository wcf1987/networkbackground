package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.MessDetailEntity;
import com.flow.network.domain2.MessHeaderEntity;
import com.flow.network.mapper2.MessDetailMapper;
import com.flow.network.mapper2.MessHeaderMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


@Service
public class MessHeaderServiceImp {

    @Autowired
    MessHeaderMapper detailMapper;
    @Autowired
    MessDetailMapper detailMapper2;
    @Autowired
    private MessDetailServiceImp serviceImp2;
    @Autowired
    private LogServiceImp logimp;

    public Integer deleteByIDS(List<String> ids) {
        Integer num = 0;
        for (String s : ids) {
            detailMapper2.deleteByPID(Integer.parseInt(s),"header");
            num = num + detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除消息头:" + String.valueOf(num) + "条");
        return num;
    }

    public Integer copyByIDS(List<String> ids) {
        Integer num = 0;
        for (String s : ids) {
            copyByID(Integer.parseInt(s));
        }

        logimp.addInfo("成功复制消息头:" + String.valueOf(ids.size()) + "条");
        return num;
    }

    public Integer copyByID(Integer id) {
        MessHeaderEntity header = detailMapper.selectByPrimaryKey(id);
        String newName="";
        for (int i=1; i < 1000; i++) {
            newName=header.getName()+"_"+String.valueOf(i);
            if (detailMapper.selectByName(newName, 0) == 0) {
                break;
            }

        }
        header.setName(newName);
        Integer temp=detailMapper.insert(header);
        copyDetail(id,header.getID());

        logimp.addInfo("添加消息头:" + header.getName());

        return 1;
    }
    public Integer copyDetail(Integer oldpid,Integer newpid){
        List<MessDetailEntity> detailtree=serviceImp2.search("",1,oldpid,"header",1,10000,0);
        Deque<MessDetailEntity> waitList = new ArrayDeque<MessDetailEntity>();
        for(MessDetailEntity detail:detailtree){
            waitList.push(detail);
        }
        for(;waitList.size()>0;){
            MessDetailEntity detail=waitList.pop();
            detail.setPID(newpid);
            if(detail.getOutType().equals("custom")){
                int temp=detailMapper2.insertCustom(detail);
                detail.setOutID(detail.getID());
                detailMapper2.insert(detail);
            }
            if(detail.getOutType().equals("fields")){
                detailMapper2.insert(detail);
            }
            if(detail.getOutType().equals("nest")){
                detailMapper2.insert(detail);
                for(MessDetailEntity child:detail.getChildren()){
                    child.setNestID(detail.getID());
                    waitList.push(child);
                }

            }
        }
        return 1;
    }

    public String add(MessHeaderEntity entity) {
        if (detailMapper.selectByName(entity.getName(), 0) > 0) {
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加消息头:" + entity.getName());
        return Tools.SUCCESS;
    }

    public String update(MessHeaderEntity entity) {
        //System.out.print("getlist");
        if (detailMapper.selectByName(entity.getName(), entity.getID()) > 0) {
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新消息头:" + entity.getName());
        return Tools.SUCCESS;
    }

    public List<MessHeaderEntity> search(String name, Integer uid, Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<MessHeaderEntity> list = detailMapper.searchByName(name, uid,"asc","ID");

        return list;
    }
    public List<MessHeaderEntity> search(PageParmInfo pageParmInfo ) {
        //System.out.print("getlist");
        PageHelper.startPage(pageParmInfo.getPageNum(),pageParmInfo.getPageSize());
        if(pageParmInfo.getOrder()==null || pageParmInfo.getOrder().equals("")){
            pageParmInfo.setOrder("ASC");
        }
        if(pageParmInfo.getOrderField()==null || pageParmInfo.getOrderField().equals("")){
            pageParmInfo.setOrderField("ID");
        }
        List<MessHeaderEntity> list = detailMapper.searchByName(pageParmInfo.getName(), pageParmInfo.getUid(),pageParmInfo.getOrder(),pageParmInfo.getOrderField());

        return list;
    }
    public List<MessHeaderEntity> searchAll(String name, Integer uid) {
        //System.out.print("getlist");
        List<MessHeaderEntity> list = detailMapper.searchByName(name, uid,"asc","id");

        return list;
    }
    public List<MessHeaderEntity> searchAll(PageParmInfo pageParmInfo ) {
        //System.out.print("getlist");
        if(pageParmInfo.getOrder()==null || pageParmInfo.getOrder().equals("")){
            pageParmInfo.setOrder("ASC");
        }
        if(pageParmInfo.getOrderField()==null || pageParmInfo.getOrderField().equals("")){
            pageParmInfo.setOrderField("ID");
        }
        List<MessHeaderEntity> list = detailMapper.searchByName(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getOrder(),pageParmInfo.getOrderField());

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        detailMapper2.deleteByPID(id,"header");
        logimp.addInfo("删除消息头:" + id);
        return 1;
    }
}
