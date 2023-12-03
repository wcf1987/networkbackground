package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.NetworkInterEntity;
import com.flow.network.mapper2.NetworkInterMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class NetworkInterServiceImp
{
    @Autowired
    private LogServiceImp logimp;
    @Autowired
    NetworkInterMapper detailMapper;
    @Autowired
    HttpServletRequest request;
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

        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.insert(entity);
        logimp.addInfo("添加网口:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(NetworkInterEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新网口:"+entity.getName());
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
        logimp.addInfo("删除网口:"+id);
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
}
