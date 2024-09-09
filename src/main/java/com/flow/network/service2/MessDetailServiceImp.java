package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.MessDetailEntity;
import com.flow.network.mapper2.MessDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


@Service
public class MessDetailServiceImp
{

    @Autowired
    MessDetailMapper detailMapper;
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
    public String addCustom(MessDetailEntity entity){
        int temp=detailMapper.insertCustom(entity);
        //System.out.println(temp);
        entity.setOutID(entity.getID());
        if(entity.getSortID()==null){
            entity.setNestID(entity.getNestID());
            entity.setOrderID(9999999.0);
        }else {
            MessDetailEntity e2 = detailMapper.selecByPrimaryKey(entity.getSortID());
            if(detailMapper.countByNestID(e2.getID())==0 && e2.getOutType().equals("nest")){
                entity.setNestID(e2.getID());
                entity.setOrderID(1.0);
            }else{
                entity.setNestID(e2.getNestID());
                entity.setOrderID(e2.getOrderID() - 0.0001);

            }
                    }
        detailMapper.insert(entity);
        updateOrder(entity.getPID(),entity.getNestID(),entity.getTType());
        return Tools.SUCCESS;
    }
    public String addFields(MessDetailEntity entity){

        //entity.setOutID(entity.getID());
        if(entity.getSortID()==null){
            entity.setNestID(entity.getNestID());
            entity.setOrderID(9999999.0);
        }else {
            MessDetailEntity e2 = detailMapper.selecByPrimaryKey(entity.getSortID());
            if(detailMapper.countByNestID(e2.getID())==0 && e2.getOutType().equals("nest")){
                entity.setNestID(e2.getID());
                entity.setOrderID(1.0);
            }else{
                entity.setNestID(e2.getNestID());
                entity.setOrderID(e2.getOrderID() - 0.0001);

            }
        }
        detailMapper.insert(entity);
        updateOrder(entity.getPID(),entity.getNestID(),entity.getTType());
        return Tools.SUCCESS;
    }
    public String addNest(MessDetailEntity entity){

        //entity.setOutID(entity.getID());
        if(entity.getSortID()==null){
            entity.setNestID(entity.getNestID());
            entity.setOrderID(9999999.0);
        }else {
            MessDetailEntity e2 = detailMapper.selecByPrimaryKey(entity.getSortID());
            if(detailMapper.countByNestID(e2.getID())==0 && e2.getOutType().equals("nest")){
                entity.setNestID(e2.getID());
                entity.setOrderID(1.0);
            }else{
                entity.setNestID(e2.getNestID());
                entity.setOrderID(e2.getOrderID() - 0.0001);

            }
        }
        detailMapper.insert(entity);
        updateOrder(entity.getPID(),entity.getNestID(),entity.getTType());
        return Tools.SUCCESS;
    }
    public String add(MessDetailEntity entity) {

        //System.out.print("getlist");
        if(entity.getOutType().equals("custom")){
            if(detailMapper.selectByName(entity.getName(),entity.getPID(),0)>0){
                throw new ServiceException("名称重复，请更改");
            }
            if(detailMapper.selectByAlias(entity.getAlias(),entity.getPID(),0)>0){
                throw new ServiceException("别名重复，请更改");
            }
            addCustom(entity);
        }
        if(entity.getOutType().equals("fields")){
            if(detailMapper.selectByAlias(entity.getAlias(),entity.getPID(),0)>0){
                throw new ServiceException("别名重复，请更改");
            }
            addFields(entity);
        }
        if(entity.getOutType().equals("nest")){
            if(detailMapper.selectByName(entity.getName(),entity.getPID(),0)>0){
                throw new ServiceException("名称重复，请更改");
            }
            addNest(entity);
        }
        return Tools.SUCCESS;

    }
    public String updateOrder(Integer pid,Integer nestid,String ttype){
        List<MessDetailEntity> list=detailMapper.searchByPID(pid,nestid,ttype);
        for(int i=0;i<list.size();i++){
            MessDetailEntity temp= list.get(i);
            temp.setOrderID(Double.valueOf(i));
            updateOrderID(temp);
        }
        return Tools.SUCCESS;
    }
    public String updateCustom(MessDetailEntity entity){
        if(entity.getSortID()!=null){
            MessDetailEntity e2 = detailMapper.selecByPrimaryKey(entity.getSortID());

            if(detailMapper.countByNestID(e2.getID())==0 && e2.getOutType().equals("nest")){
                entity.setNestID(e2.getID());
                entity.setOrderID(1.0);
            }else{
                entity.setNestID(e2.getNestID());
                entity.setOrderID(e2.getOrderID() - 0.0001);

            }


            detailMapper.updateByPrimaryKey(entity);
            detailMapper.updateCustomByPrimaryKey(entity);
            detailMapper.updateOrderIDByPrimaryKey(entity);
            updateOrder(entity.getPID(),entity.getNestID(),entity.getTType());
        }else{
            detailMapper.updateByPrimaryKey(entity);
            detailMapper.updateCustomByPrimaryKey(entity);
        }

        return Tools.SUCCESS;
    }
    public String updateFields(MessDetailEntity entity){
        if(entity.getSortID()!=null){
            MessDetailEntity e2 = detailMapper.selecByPrimaryKey(entity.getSortID());
            if(detailMapper.countByNestID(e2.getID())==0 && e2.getOutType().equals("nest")){
                entity.setNestID(e2.getID());
                entity.setOrderID(1.0);
            }else{
                entity.setNestID(e2.getNestID());
                entity.setOrderID(e2.getOrderID() - 0.0001);

            }
            detailMapper.updateByPrimaryKey(entity);
            detailMapper.updateOrderIDByPrimaryKey(entity);
            updateOrder(entity.getPID(),entity.getNestID(),entity.getTType());
        }else{
            detailMapper.updateByPrimaryKey(entity);

        }

        return Tools.SUCCESS;
    }
    public String updateNest(MessDetailEntity entity){
        if(entity.getSortID()!=null){
            MessDetailEntity e2 = detailMapper.selecByPrimaryKey(entity.getSortID());
            if(detailMapper.countByNestID(e2.getID())==0 && e2.getOutType().equals("nest")){
                entity.setNestID(e2.getID());
                entity.setOrderID(1.0);
            }else{
                entity.setNestID(e2.getNestID());
                entity.setOrderID(e2.getOrderID() - 0.0001);

            }
            System.out.println(entity);
            detailMapper.updateByPrimaryKey(entity);
            detailMapper.updateOrderIDByPrimaryKey(entity);
            updateOrder(entity.getPID(),entity.getNestID(),entity.getTType());
        }else{
            detailMapper.updateByPrimaryKey(entity);

        }

        return Tools.SUCCESS;
    }
    public String update(MessDetailEntity entity) {

        if(entity.getOutType().equals("custom")){
            if(detailMapper.selectByName(entity.getName(),entity.getPID(),entity.getID())>0){
                throw new ServiceException("名称重复，请更改");
            }
            if(detailMapper.selectByAlias(entity.getAlias(),entity.getPID(),entity.getID())>0){
                throw new ServiceException("别名重复，请更改");
            }
            updateCustom(entity);
        }
        if(entity.getOutType().equals("fields")){
            if(detailMapper.selectByAlias(entity.getAlias(),entity.getPID(),entity.getID())>0){
                throw new ServiceException("别名重复，请更改");
            }
            updateFields(entity);
        }
        if(entity.getOutType().equals("nest")){
            if(detailMapper.selectByName(entity.getName(),entity.getPID(),entity.getID())>0){
                throw new ServiceException("名称重复，请更改");
            }
            if(detailMapper.selectByAlias(entity.getAlias(),entity.getPID(),entity.getID())>0){
                throw new ServiceException("别名重复，请更改");
            }
            updateNest(entity);
        }
        return Tools.SUCCESS;

        //System.out.print("getlist");

    }
    public String updateOrderID(MessDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateOrderIDByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<MessDetailEntity> search(String name, Integer uid,Integer pid,String ttype, Integer pageNum, Integer pageSize,Integer nestid) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);

        List<MessDetailEntity> list=detailMapper.searchByName(name,uid,pid,ttype,nestid);
        Deque<MessDetailEntity> waitList = new ArrayDeque<MessDetailEntity>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOutType().equals("nest")){
                //list.get(i).setEName(list.get(i).getName());
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
                   // listt.get(i).setEName(listt.get(i).getName());
                    waitList.push(listt.get(i));
                }
            }
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).getOutType().equals("nest")){
              //  list.get(i).setEName(list.get(i).getName());
                waitList.push(list.get(i));
            }
            else{
                CompleteFields(list.get(i));
            }
        }
        for(;waitList.size()>0;){
            MessDetailEntity t=waitList.pop();
            List<MessDetailEntity> listt=null;
            if(t.getChildren()==null){
                t.setChildren(new ArrayList<MessDetailEntity>());
                continue;
            }
            if(t.getChildren().size()>0){
                listt=t.getChildren();
            }else {
                continue;
            }
            for(int i=0;i<listt.size();i++){
                if(listt.get(i).getOutType().equals("nest")){
                //    listt.get(i).setEName(listt.get(i).getName());
                    waitList.push(listt.get(i));
                }else{
                    CompleteFields(listt.get(i));
                }
            }
        }


        return list;
    }
    public List<MessDetailEntity> searchName(String name, Integer uid,Integer pid,String ttype, Integer pageNum, Integer pageSize,Integer nestid) {
        //System.out.print("getlist");
        //PageHelper.startPage(pageNum, pageSize);
        String NameBlank="";
        List<MessDetailEntity> list=detailMapper.searchByName(NameBlank,uid,pid,ttype,nestid);
        Deque<MessDetailEntity> waitList = new ArrayDeque<MessDetailEntity>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getOutType().equals("nest")){
                //list.get(i).setEName(list.get(i).getName());
                waitList.push(list.get(i));
            }
        }
        for(;waitList.size()>0;){
            MessDetailEntity t=waitList.pop();
            List<MessDetailEntity> listt=detailMapper.getListByNestID(NameBlank,uid,pid,ttype,t.getID());
            if(listt.size()>0){
                t.setChildren(listt);
            }
            for(int i=0;i<listt.size();i++){
                if(listt.get(i).getOutType().equals("nest")){
                    // listt.get(i).setEName(listt.get(i).getName());
                    waitList.push(listt.get(i));
                }
            }
        }

        for(int i=0;i<list.size();i++){
            if(list.get(i).getOutType().equals("nest")){
                //  list.get(i).setEName(list.get(i).getName());
                waitList.push(list.get(i));
            }
            else{
                CompleteFields(list.get(i));
            }
        }
        for(;waitList.size()>0;){
            MessDetailEntity t=waitList.pop();
            List<MessDetailEntity> listt=null;
            if(t.getChildren()==null){
                t.setChildren(new ArrayList<MessDetailEntity>());
                continue;
            }
            if(t.getChildren().size()>0){
                listt=t.getChildren();
            }else {
                continue;
            }
            for(int i=0;i<listt.size();i++){
                if(listt.get(i).getOutType().equals("nest")){
                    //    listt.get(i).setEName(listt.get(i).getName());
                    waitList.push(listt.get(i));
                }else{
                    CompleteFields(listt.get(i));
                }
            }
        }

        List<MessDetailEntity> listC=new ArrayList<MessDetailEntity>();
        for(MessDetailEntity temp : list){
            if(CheckName(temp,name)){
                listC.add(temp);
            }
        }
        int start,end;
        start=pageSize*(pageNum-1);
        end=pageSize*pageNum;
        System.out.println(list);
        if(listC.size()==0){
            return listC;
        }else{
            if(listC.size()<end){
                end=listC.size();
            }
            return listC.subList(start,end);
        }

    }
    public boolean CheckName(MessDetailEntity me,String namestr){
        if(me.getOutType().equals("nest")){
            if(me.getName().indexOf(namestr)!=-1){
                return true;
            }
            for(MessDetailEntity children:me.getChildren()){
                if(CheckName(children,namestr)){
                    return true;
                }
            }
            return false;
        }else{
            if(me.getName().indexOf(namestr)!=-1){
                return true;
            }else {
                return false;

            }
        }

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
            //t.setEName(temp.getEName());
            t.setShortName(temp.getShortName());
            t.setLength(temp.getLength());
            t.setType(temp.getType());
            t.setDFIID(temp.getDFIID());
        }
    }
    public List<MessDetailEntity> searchAll(String name, Integer uid,Integer pid,String ttype,Integer nestid ) {
        //System.out.print("getlist");
        List<MessDetailEntity> list=detailMapper.searchByName(name,uid,pid,ttype,nestid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
