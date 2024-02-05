package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.MessHeaderEntity;
import com.flow.network.mapper2.MessHeaderMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessHeaderServiceImp {

    @Autowired
    MessHeaderMapper detailMapper;

    @Autowired
    private LogServiceImp logimp;

    public Integer deleteByIDS(List<String> ids) {
        Integer num = 0;
        for (String s : ids) {
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
        detailMapper.insert(header);
        logimp.addInfo("添加消息头:" + header.getName());

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
        List<MessHeaderEntity> list = detailMapper.searchByName(name, uid);

        return list;
    }

    public List<MessHeaderEntity> searchAll(String name, Integer uid) {
        //System.out.print("getlist");
        List<MessHeaderEntity> list = detailMapper.searchByName(name, uid);

        return list;
    }

    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除消息头:" + id);
        return 1;
    }
}
