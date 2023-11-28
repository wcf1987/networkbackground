package com.flow.network.service2;

import com.flow.network.domain2.GatewayEntity;
import com.flow.network.mapper2.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeServiceImp
{

    @Autowired
    HomeMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;


    public List<GatewayEntity> search() {
        //System.out.print("getlist");

        List<GatewayEntity> list=detailMapper.searchByName();

        return list;
    }

}
