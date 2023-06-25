package com.flow.network.controller;

import com.flow.network.domain.InterfaceEntity;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.service.InterfaceServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/interface")
public class InterfaceController {
    @Autowired
    private InterfaceServiceImp interfaceService;
    @RequestMapping("/list")
    public List<InterfaceEntity> list(@RequestBody PageParmInfo pageParmInfo) {

        return interfaceService.getList(pageParmInfo.getPageNum(), pageParmInfo.getPageSize());

    }
    @RequestMapping("/alllistnum")
    public Integer alllistNum() {

        return interfaceService.getAllList().size();

    }
    @RequestMapping("/delete")
    public String delete(@RequestBody InterfaceEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        interfaceService.deleteByID(interfaceEntity.getId());
        return Tools.SUCCESS;

    }
    @RequestMapping("/copy")
    public String copy(@RequestBody InterfaceEntity interfaceEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        interfaceService.copyByID(interfaceEntity);
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody InterfaceEntity interfaceEntity){
        interfaceService.add(interfaceEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody InterfaceEntity interfaceEntity){
        interfaceService.update(interfaceEntity);
        return Tools.SUCCESS;
    }
}