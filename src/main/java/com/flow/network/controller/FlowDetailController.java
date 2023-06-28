package com.flow.network.controller;

import com.flow.network.domain.FlowDetailEntity;
import com.flow.network.service.FlowDetailServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flowDetail")
public class FlowDetailController {
    @Autowired
    private FlowDetailServiceImp flowDetailServiceImp;

    @RequestMapping("/getByID")
    public FlowDetailEntity getByID(@RequestBody FlowDetailEntity flowDetailEntity) {

        return flowDetailServiceImp.getByID(flowDetailEntity.getFlowID());

    }

    @RequestMapping("/delete")
    public String delete(@RequestBody FlowDetailEntity flowDetailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        flowDetailServiceImp.deleteByID(flowDetailEntity.getId());
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody FlowDetailEntity flowDetailEntity){
        System.out.println("保存流程图："+flowDetailEntity.getFlowID());
        flowDetailServiceImp.add(flowDetailEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody FlowDetailEntity flowDetailEntity){
        flowDetailServiceImp.update(flowDetailEntity);
        return Tools.SUCCESS;
    }
}