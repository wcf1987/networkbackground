package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.MessTranslateEntity;
import com.flow.network.mapper2.MessTranslateDetailMapper;
import com.flow.network.mapper2.MessTraslateMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MessTranslateServiceImp
{

    @Autowired
    MessTraslateMapper detailMapper;
    @Autowired
    MessTranslateDetailMapper detailMapper2;
    @Autowired
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            detailMapper2.deleteByPID(Integer.parseInt(s));
            num=num+detailMapper.delete(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除消息转换:"+String.valueOf(num)+"条");
        return num;
    }
    public String add(MessTranslateEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加消息转换:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(MessTranslateEntity entity) {
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新消息转换:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<MessTranslateEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<MessTranslateEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public List<MessTranslateEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<MessTranslateEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        detailMapper2.deleteByPID(id);
        logimp.addInfo("删除消息转换:"+id);
        return 1;
    }
}
