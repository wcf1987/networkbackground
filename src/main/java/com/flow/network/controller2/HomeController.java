package com.flow.network.controller2;

import com.flow.network.config.ApiResponse;
import com.flow.network.domain.PageParmInfo;
import com.flow.network.service2.HomeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeServiceImp serviceImp;



    @PostMapping("/getdata")
    public ApiResponse search(@RequestBody PageParmInfo pageParmInfo ) {
        return ApiResponse.success(serviceImp.search());

    }
}