package com.flow.network.service2;

import com.flow.network.config.ServiceException;
import com.flow.network.domain2.FlowDesignEntity;
import com.flow.network.domain2.TransTemplateEntity;
import com.flow.network.mapper2.FlowDesignMapper;
import com.flow.network.mapper2.TransTemplateMapper;
import com.flow.network.tools.DateTools;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransTemplateServiceImp
{

    @Autowired
    TransTemplateMapper detailMapper;
    @Autowired
    FlowDesignMapper detailMapper2;
    @Autowired
    private LogServiceImp logimp;
    public String add(TransTemplateEntity entity) {
        if(detailMapper.selectByName(entity.getName(),0)>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.print("getlist");
        detailMapper.insert(entity);
        logimp.addInfo("添加流程设计:"+entity.getName());
        return Tools.SUCCESS;
    }
    public String copy(TransTemplateEntity entity) {
        TransTemplateEntity f=detailMapper.getFlowDesignByID(entity.getID());
        //System.out.print("getlist");
        f.setName(f.getName()+"_copy");
        f.setAuthorID(entity.getAuthorID());
        detailMapper.insert(f);
        logimp.addInfo("复制流程设计:"+f.getName());
        return Tools.SUCCESS;
    }
    public String applyFlow(TransTemplateEntity entity) {
        TransTemplateEntity f=detailMapper.getFlowDesignByID(entity.getID());
        //System.out.print("getlist");
        FlowDesignEntity fd=new FlowDesignEntity();
        fd.setFlowJson(f.getFlowJson());
        fd.setName(entity.getName());
        //fd.setName(f.getName()+"_"+DateTools.getNowStr()+"实例");
        fd.setType(f.getType());
        fd.setDescribes(f.getName()+"的一个实例，创建于"+ DateTools.getNowStr());
        fd.setAuthorID(entity.getAuthorID());
        detailMapper2.insert(fd);
        logimp.addInfo("应用模板:"+f.getName());
        return Tools.SUCCESS;
    }
    public String update(TransTemplateEntity entity) {
        //System.out.print("getlist");
        if(detailMapper.selectByName(entity.getName(),entity.getID())>0){
            throw new ServiceException("名称重复，请更改");
        }
        //System.out.println("test"+entity.getFlowOutStr());
        detailMapper.updateByPrimaryKey(entity);
        logimp.addInfo("更新流程设计:"+entity.getName());
        return Tools.SUCCESS;
    }
    public TransTemplateEntity updateJson(TransTemplateEntity entity) {
        //System.out.print("getlist");
        detailMapper.updateJsonByPrimaryKey(entity);
        TransTemplateEntity t=getFlowByID(entity.getID());
        return t;
    }
    public List<TransTemplateEntity> search(String name,Integer uid,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<TransTemplateEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public TransTemplateEntity getFlowByID(Integer id) {
        //System.out.print("getlist");

        TransTemplateEntity t=detailMapper.getFlowDesignByID(id);

        return t;
    }
    public List<TransTemplateEntity> searchAll(String name,Integer uid) {
        //System.out.print("getlist");
        List<TransTemplateEntity> list=detailMapper.searchByName(name,uid);

        return list;
    }
    public Integer deleteByID(Integer id) {
        //System.out.print("deleteByID");
        detailMapper.delete(id);
        logimp.addInfo("删除流程设计:"+id);
        return 1;
    }
}
