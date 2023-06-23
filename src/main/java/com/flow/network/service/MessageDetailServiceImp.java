package com.flow.network.service;

import com.flow.network.domain.MessageDetailEntity;
import com.flow.network.mapper.MessageDetailMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;


@Service
public class MessageDetailServiceImp
{

    @Autowired
    MessageDetailMapper detailMapper;
    public List<MessageDetailEntity> getListByPID(Integer mid,Integer pid) {
        System.out.print("getlist");
        List<MessageDetailEntity> list=detailMapper.getListByPid(mid,pid);
        return list;
    }
    public List<MessageDetailEntity> getListByMID(Integer id) {
        System.out.print("getlist");
        List<MessageDetailEntity> list=detailMapper.getList(id);
        return list;
    }
    public List<MessageDetailEntity> getAllListByMID(Integer id) {
        System.out.print("getlist");
        List<MessageDetailEntity> list=detailMapper.getList(id);
        //List<MessageDetailEntity> waitList=new ArrayList<MessageDetailEntity>();
        Deque<MessageDetailEntity> waitList = new ArrayDeque<MessageDetailEntity>();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getType().equals("嵌套消息体")){
                waitList.push(list.get(i));
            }
        }
        for(;waitList.size()>0;){
            MessageDetailEntity t=waitList.pop();
            List<MessageDetailEntity> listt=detailMapper.getListByPid(t.getMessageID(),t.getId());
                if(listt.size()>0){
                    t.setChildren(listt);
                }
            for(int i=0;i<listt.size();i++){
                if(listt.get(i).getType().equals("嵌套消息体")){
                    waitList.push(listt.get(i));
                }
            }
        }
        return list;
    }
    public String add(MessageDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(MessageDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
