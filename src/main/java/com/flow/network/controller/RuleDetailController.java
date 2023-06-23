package com.flow.network.controller;

import com.flow.network.domain.RuleDetailEntity;
import com.flow.network.service.RuleDetailServiceImp;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ruleDetail")
public class RuleDetailController {
    @Autowired
    private RuleDetailServiceImp detailServiceImp;
    @RequestMapping("/list")
    public List<RuleDetailEntity> list(@RequestBody RuleDetailEntity detailEntity) {

        return detailServiceImp.getListByPID(detailEntity.getRuleID());

    }
    @RequestMapping("/delete")
    public String delete(@RequestBody RuleDetailEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        detailServiceImp.deleteByID(detailEntity.getId());
        return Tools.SUCCESS;

    }
    @PostMapping("add")
    public String add(@RequestBody RuleDetailEntity detailEntity){
        detailServiceImp.add(detailEntity);
        return Tools.SUCCESS;
    }
    @PostMapping("update")
    public String update(@RequestBody RuleDetailEntity detailEntity){
        detailServiceImp.update(detailEntity);
        return Tools.SUCCESS;
    }
}