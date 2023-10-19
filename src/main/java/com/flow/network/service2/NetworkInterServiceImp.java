package com.flow.network.service2;

import com.flow.network.domain2.NetworkInterEntity;
import com.flow.network.mapper2.NetworkInterMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NetworkInterServiceImp
{

    @Autowired
    NetworkInterMapper detailMapper;

    public List<NetworkInterEntity> getListByUID(Integer id, Integer pageNum, Integer pageSize) {
        System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<NetworkInterEntity> list=detailMapper.getList(id);
        return list;
    }
    public List<NetworkInterEntity> geAlltListByUID(Integer id) {
        System.out.print("getlist");
        List<NetworkInterEntity> list=detailMapper.getList(id);
        return list;
    }

    public String add(NetworkInterEntity entity) {
        //System.out.print("getlist");
        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String update(NetworkInterEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public List<NetworkInterEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<NetworkInterEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<NetworkInterEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<NetworkInterEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
