package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.TransClassfyEntity;
import com.flow.network.domain2.TransTemplateEntity;
import com.flow.network.mapper2.TransClassfyMapper;
import com.flow.network.mapper2.TransTemplateMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransClassfyServiceImp
{

    @Autowired
    TransClassfyMapper detailMapper;
    @Autowired
    TransTemplateMapper detailMapper2;
    @Autowired
    private LogServiceImp logimp;
    public Integer deleteByIDS(List<String> ids) {
        Integer num=0;
        for(String s :ids){
            num=num+detailMapper.delete(Integer.parseInt(s));
            detailMapper2.deleteByPID(Integer.parseInt(s));
        }

        logimp.addInfo("成功删除模板分类:"+String.valueOf(num)+"条");
        return num;
    }
    public String add(TransClassfyEntity entity) {
        if(detailMapper.selectByName(entity.getName(),entity.getType(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }

        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加模板分类:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String update(TransClassfyEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getType(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }

        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新分类:"+entity.getName());
        return Tools.SUCCESS;
    }
    public List<TransClassfyEntity> search(String name,String type,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<TransClassfyEntity> list=detailMapper.searchByName(name,type,uid);

        return list;
    }
    public List<TransClassfyEntity> searchWithChildren(String name,String type,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");

        List<TransClassfyEntity> list=detailMapper.searchByName(name,type,uid);
        for(TransClassfyEntity e : list){
            List<TransTemplateEntity> list2=detailMapper2.searchByPID("",e.getID());
            e.setChildren(list2);
        }
        return list;
    }
    public List<TransClassfyEntity> searchAll(String name,String type,Integer uid) {
        //System.out.print("getlist");
        List<TransClassfyEntity> list=detailMapper.searchByName(name,type,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        detailMapper2.deleteByPID(id);
        logimp.addInfo("删除模板分类:"+id);
        return 1;
    }
}
