package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.GatewayEntity;
import com.flow.network.mapper2.GatewayMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GatewayServiceImp
{

    @Autowired
    GatewayMapper detailMapper;
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
    public String add(GatewayEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加网关:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(GatewayEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新网关:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<GatewayEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<GatewayEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<GatewayEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<GatewayEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除网关:"+id);
        return 1;
    }
}
