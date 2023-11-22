package com.flow.network.service2;

import com.flow.network.domain2.LogEntity;
import com.flow.network.mapper2.LogMapper;
import com.flow.network.tools.Tools;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class LogServiceImp
{

    @Autowired
    LogMapper detailMapper;

    @Autowired
    HttpServletRequest request;
    @Autowired
    private HttpSession httpSession;
    public String add(LogEntity entity) {
        //System.out.print("getlist");

        detailMapper.insert(entity);
        return Tools.SUCCESS;
    }
    public String addInfo(String msg) {
        System.out.print("log session id"+httpSession.getId());

        String userName = (String)httpSession.getAttribute("userName");
        LogEntity logtemp=new LogEntity("info",userName,msg);
        detailMapper.insert(logtemp);
        return Tools.SUCCESS;
    }
    public List<LogEntity> search(String name,Integer pageNum, Integer pageSize) {
        //System.out.print("getlist");
        PageHelper.startPage(pageNum, pageSize);
        List<LogEntity> list=detailMapper.searchByName(name);

        return list;
    }
    public List<LogEntity> searchAll(String name) {
        //System.out.print("getlist");
        //PageHelper.startPage(pageNum, pageSize);
        List<LogEntity> list=detailMapper.searchByName(name);

        return list;
    }
}
