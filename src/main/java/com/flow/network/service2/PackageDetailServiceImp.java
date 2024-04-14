package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.PackageDetailEntity;
import com.flow.network.mapper2.PackageDetailMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageDetailServiceImp {

    @Autowired
    PackageDetailMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;

    public Integer deleteByIDS(List<String> ids) {
        Integer num = 0;
        for (String s : ids) {
            num = num + detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除网口:" + String.valueOf(num) + "条");
        return num;
    }

    public String add(PackageDetailEntity entity) {
        //System.out.print("getlist");
        if (detailMapper.selectByName(entity.getName(), entity.getPackID(), 0) > 0) {
            throw new ServiceException("名称重复，请更改");
        }

        if (entity.getSortID() == null) {
            entity.setOrderID(9999999.0);
        } else {
            PackageDetailEntity e2 = detailMapper.selectByPrimaryKey(entity.getSortID());
            entity.setOrderID(e2.getOrderID() - 0.0001);
        }
        detailMapper.insert(entity);
        updateOrder(entity.getPackID());
        return Tools.SUCCESS;
    }

    public String updateOrder(Integer pid) {
        List<PackageDetailEntity> list = detailMapper.searchByPid(pid);
        for (int i = 0; i < list.size(); i++) {
            PackageDetailEntity temp = list.get(i);
            temp.setOrderID(Double.valueOf(i));
            updateOrderID(temp);
        }
        return Tools.SUCCESS;
    }

    public String updateOrderID(PackageDetailEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateOrderIDByPrimaryKey(entity);
        return Tools.SUCCESS;
    }

    public String update(PackageDetailEntity entity) {
        //System.out.print("getlist");
        if (detailMapper.selectByName(entity.getName(), entity.getPackID(), entity.getID()) > 0) {
            throw new ServiceException("名称重复，请更改");
        }

        if (entity.getSortID() == null) {
            detailMapper.updateByPrimaryKey(entity);

        } else {


            PackageDetailEntity e2 = detailMapper.selectByPrimaryKey(entity.getSortID());
            entity.setOrderID(e2.getOrderID() - 0.0001);

            detailMapper.updateByPrimaryKey(entity);

            detailMapper.updateOrderIDByPrimaryKey(entity);
            updateOrder(entity.getPackID());

        }

        return Tools.SUCCESS;

    }

    public List<PackageDetailEntity> search(String name, Integer uid, Integer pid, Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<PackageDetailEntity> list = detailMapper.searchByName(name, uid, pid);

        return list;
    }

    public List<PackageDetailEntity> searchAll(String name, Integer uid, Integer pid) {
        //System.out.print("getlist");
        List<PackageDetailEntity> list = detailMapper.searchByName(name, uid, pid);

        return list;
    }

    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        return 1;
    }
}
