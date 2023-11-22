package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.config.ResponseCode;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.domain2.UserEntity;
import com.flow.network.service2.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@SessionAttributes("username")
public class UserController {
    @Autowired
    private UserServiceImp serviceImp;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/searchSize")
    public ApiResponse searchSize(@RequestBody PageParmInfo pageParmInfo) {

        return ApiResponse.success(serviceImp.searchAll(pageParmInfo.getName(),pageParmInfo.getUid()).size());

    }

    @RequestMapping("/delete")
    public ApiResponse delete(@RequestBody UserEntity detailEntity) {
        //List<InterfaceEntity> u = new ArrayList<>();
        serviceImp.deleteByID(detailEntity.getId());
        return ApiResponse.success();

    }
    @PostMapping("/add")
    public ApiResponse add(@RequestBody UserEntity detailEntity){
        serviceImp.add(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/update")
    public ApiResponse update(@RequestBody UserEntity detailEntity){
        serviceImp.update(detailEntity);
        return ApiResponse.success();
    }
    @PostMapping("/search")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ){
        return ApiResponse.success(serviceImp.search(pageParmInfo.getName(),pageParmInfo.getUid(),pageParmInfo.getPageNum(),pageParmInfo.getPageSize()));

    }
    @PostMapping("/signIn")
    public ApiResponse signIn(@RequestBody UserEntity detailEntity,HttpSession session){
        try{
        UserEntity u=serviceImp.getByPass(detailEntity.getUserName(),detailEntity.getPassword());
        if(u==null){
            return ApiResponse.fail(301,"用户密码错误");
        }else {

            //第二步：将想要保存到数据存入session中
            session.setAttribute("userName",detailEntity.getUserName());
            System.out.println("log session id:"+session.getId());
            return ApiResponse.success(u);
        }}catch (Exception e){
            e.printStackTrace();
            return ApiResponse.fail(ResponseCode.ERROR.getCode(), e.getMessage());
        }
    }
    @PostMapping("/signOut")
    public ApiResponse signOut(@RequestBody UserEntity detailEntity ){
        String userName = (String) request.getSession().getAttribute("userName");
        System.out.println(userName);
        return ApiResponse.success(serviceImp.sighOut(detailEntity.getUserName()));

    }
}