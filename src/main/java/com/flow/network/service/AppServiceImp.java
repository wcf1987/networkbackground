package com.flow.network.service;

import com.flow.network.domain.AppEntity;
import com.flow.network.mapper.AppDetailMapper;
import com.flow.network.mapper.AppMapper;
import com.flow.network.mapper.PackMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AppServiceImp
{

    @Autowired
    AppMapper mapper;
    @Autowired
    AppDetailMapper detailMapper;

    @Autowired
    PackMapper packMapper;
    public List<AppEntity> getAllList() {
        System.out.print("getlist");
        List<AppEntity> list=mapper.getList();
        for (int i=0;i<list.size();i++){
            if(list.get(i).getPackID()!=null){

                list.get(i).setPackName(packMapper.selectByPrimaryKey(list.get(i).getPackID()).getName());
            }
            if(list.get(i).getUnpackID()!=null) {
                list.get(i).setUnpackName(packMapper.selectByPrimaryKey(list.get(i).getUnpackID()).getName());
            }
        }
        return list;
    }
    public String add(AppEntity entity) {
        //System.out.print("getlist");
        //AppDetailEntity.setId(-1);
        int pid=mapper.insert(entity);
        pid=entity.getId();
 

        return Tools.SUCCESS;
    }
    public String update(AppEntity entity) {
        //System.out.print("getlist");
        mapper.updateByPrimaryKey(entity);
        return Tools.SUCCESS;
    }
    public String deleteByID(Integer id) {
        System.out.println("deleteByID");
        mapper.delete(id);
        detailMapper.deleteByPID(id);
        return  Tools.SUCCESS;
    }
    public String copyByID(AppEntity entity) {
        System.out.print("deleteByID");
        int oldid=entity.getId();
        int newid=mapper.copy(entity);
        newid=entity.getId();
        detailMapper.copyByPID(oldid,newid);
        return Tools.SUCCESS;
    }
}
