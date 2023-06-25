package com.flow.network.service;

import com.flow.network.domain.PackageEntity;
import com.flow.network.mapper.PackMapper;
import com.flow.network.mapper.PackageDetailMapper;
import com.flow.network.mapper.PackageMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PackageServiceImp
{

    @Autowired
    PackageMapper packageMapper;
    @Autowired
    PackageDetailMapper packageDetailMapper;
    @Autowired
    PackMapper packMapper;
    public List<PackageEntity> getAllList() {
        System.out.print("getlist");
        List<PackageEntity> list=packageMapper.getList();
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

    public List<PackageEntity> getList(Integer pageNum,Integer pageSize) {
        System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<PackageEntity> list=packageMapper.getList();
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

    public String add(PackageEntity interfaceEntity) {
        //System.out.print("getlist");
        //interfaceEntity.setId(-1);
        int pid=packageMapper.insert(interfaceEntity);
        pid=interfaceEntity.getId();
        /*if(interfaceEntity.getType().equals("串口")){
            packageDetailMapper.insert(new InterfaceDetailEntity(1,"类型","type","0","串口",pid));
            packageDetailMapper.insert(new InterfaceDetailEntity(2,"出口","outside","1","",pid));
            packageDetailMapper.insert(new InterfaceDetailEntity(3,"比特率","bitrate","8","",pid));
            packageDetailMapper.insert(new InterfaceDetailEntity(4,"流控","flowControl","4","",pid));

        }else{
            packageDetailMapper.insert(new InterfaceDetailEntity(1,"类型","type","0","网口",pid));
            packageDetailMapper.insert(new InterfaceDetailEntity(2,"IP地址","IPAddress","32","",pid));
            packageDetailMapper.insert(new InterfaceDetailEntity(3,"端口地址","port","8","",pid));
            packageDetailMapper.insert(new InterfaceDetailEntity(4,"协议","protocol","4","",pid));

        }*/

        return Tools.SUCCESS;
    }
    public String update(PackageEntity interfaceEntity) {
        //System.out.print("getlist");
        packageMapper.updateByPrimaryKey(interfaceEntity);
        return Tools.SUCCESS;
    }
    public Integer deleteByID(Integer id) {
        System.out.print("deleteByID");
        packageMapper.delete(id);
        packageDetailMapper.deleteByPID(id);
        return 1;
    }
    public Integer copyByID(PackageEntity interfaceEntity) {
        System.out.print("deleteByID");
        int oldid=interfaceEntity.getId();
        int newid=packageMapper.copy(interfaceEntity);
        newid=interfaceEntity.getId();
        packageDetailMapper.copyByPID(oldid,newid);
        return 1;
    }
}
