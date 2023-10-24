package com.flow.network.service2;

import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.MessDetailEntity;
import com.flow.network.mapper2.MessDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


@Service
public class MessDetailServiceImp
{

    @Autowired
    MessDetailMapper detailMapper;


    public String add(MessDetailEntity entity) {
        //System.out.print("getlist");
        int temp=detailMapper.insertCustom(entity);
        //System.out.println(temp);
        entity.setOutID(entity.getID());
        entity.setOutType("custom");

        MessDetailEntity e2=detailMapper.selecByPrimaryKey(entity.getSortID());
        entity.setNestID(e2.getNestID());
        entity.setOrderID(e2.getOrderID()+0.0001);
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(FieldsDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<MessDetailEntity> search(String name, Integer uid,Integer pid,String ttype, Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);

        List<MessDetailEntity> list=detailMapper.searchByName(name,uid,pid,ttype);
        Deque<MessDetailEntity> waitList = new ArrayDeque<MessDetailEntity>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOutType().equals("nest")){
                waitList.push(list.get(i));
            }
        }
        for(;waitList.size()>0;){
            MessDetailEntity t=waitList.pop();
            List<MessDetailEntity> listt=detailMapper.getListByNestID(name,uid,pid,ttype,t.getID());
            if(listt.size()>0){
                t.setChildren(listt);
            }
            for(int i=0;i<listt.size();i++){
                if(listt.get(i).getOutType().equals("nest")){
                    waitList.push(listt.get(i));
                }
            }
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).getOutType().equals("nest")){
                waitList.push(list.get(i));
            }
            else{
                CompleteFields(list.get(i));
            }
        }
        for(;waitList.size()>0;){
            MessDetailEntity t=waitList.pop();
            List<MessDetailEntity> listt=null;
            if(t.getChildren().size()>0){
                listt=t.getChildren();
            }else {
                continue;
            }
            for(int i=0;i<listt.size();i++){
                if(listt.get(i).getOutType().equals("nest")){
                    waitList.push(listt.get(i));
                }else{
                    CompleteFields(listt.get(i));
                }
            }
        }



        return list;
    }
    //    补全字段
    public  void CompleteFields(MessDetailEntity t){
        MessDetailEntity temp;
        if(t.getOutType().equals("custom")){
            temp=detailMapper.selectCustomByPrimaryKey(t.getOutID());
            t.setEName(temp.getEName());
            t.setShortName(temp.getShortName());
            t.setLength(temp.getLength());
            t.setType(temp.getType());
        }
        if(t.getOutType().equals("fields")){
            temp=detailMapper.selectFieldsByPrimaryKey(t.getOutID());
            t.setEName(temp.getEName());
            t.setShortName(temp.getShortName());
            t.setLength(temp.getLength());
            t.setType(temp.getType());
        }
    }
    public List<MessDetailEntity> searchAll(String name, Integer uid,Integer pid,String ttype ) {
        //System.out.print("getlist");
        List<MessDetailEntity> list=detailMapper.searchByName(name,uid,pid,ttype);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
