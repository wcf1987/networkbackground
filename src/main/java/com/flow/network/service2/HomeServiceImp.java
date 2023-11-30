package com.flow.network.service2;

import com.flow.network.domain2.*;
import com.flow.network.mapper2.HomeMapper;
import com.flow.network.tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class HomeServiceImp {

    @Autowired
    HomeMapper detailMapper;
    @Autowired
    private LogServiceImp logimp;


    public HomeEntity search() {
        //System.out.print("getlist");

        //List<GatewayEntity> list=detailMapper.searchByName();
        HomeEntity home = new HomeEntity();
        HomeResourceEntity re = new HomeResourceEntity();

        try {
            re.setMemory(String.valueOf((int) Math.round(Tools.getMemoryInfo())));
            re.setDisk(String.valueOf((int) Math.round(Tools.getDiskUsed())));
            re.setCpu(String.valueOf((int) Math.round(Tools.recordCpuInfo())));
            double v = 30 * Math.random();
            re.setNetwork(String.valueOf((int) v));


            re.setAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
        home.setResource(re);

        HomeOneEntity h1 = new HomeOneEntity();
        //detailMapper.getlownum();
        h1.setFlownums(detailMapper.getFlowNum());
        Integer temp = detailMapper.getFlowNow();
        System.out.println(temp * 100 / Integer.valueOf(h1.getFlownums()));
        h1.setFlowadd(Tools.formatNumber(temp * 100 / Integer.valueOf(h1.getFlownums())));

        h1.setDiswnums(detailMapper.getDisNum());
        temp = detailMapper.getDisNow();
        h1.setDisadd(Tools.formatNumber(temp * 100 / Integer.valueOf(h1.getDiswnums())));

        h1.setUsernums(detailMapper.getUserNum());
        temp = detailMapper.getUserNow();
        h1.setUseradd(Tools.formatNumber(temp * 100 / Integer.valueOf(h1.getUsernums())));


        h1.setFieldsnums(detailMapper.getFieldsNum());
        temp = detailMapper.getFieldsNow();
        h1.setFieldsadd(Tools.formatNumber(temp * 100 / Integer.valueOf(h1.getFieldsnums())));

        home.setOne(h1);

        List<CountNum> list = detailMapper.getFieldsClass();
        HomePieEntity pie = new HomePieEntity();
        pie.setName(getListSub(list, "name"));
        pie.setValue(getListSub(list, "num1"));
        home.setPie(pie);

        HomeBarEntity bar = new HomeBarEntity();


        List<String> datelist=Tools.getPastSevenDaysInChinese();

        //String[] strArray0 = {"t1", "t2", "t3", "t4", "t5"};


        bar.setDate1(datelist);

        List<String> datelist2=Tools.getPastSevenDays();
        List<String> s1=new ArrayList<String>();
        List<String> s2=new ArrayList<String>();
        List<String> s3=new ArrayList<String>();

        for(int i=0;i<datelist2.size();i++){
            String ds=datelist2.get(i);
            s1.add(detailMapper.getUserAdd(ds));
            s2.add(detailMapper.getUserLogin(ds));
            s3.add(detailMapper.getFieldsUpdate(ds,"更新DUI"));

        }

        bar.setValue1(s1);
        //String[] strArray21 = {"13", "12", "23", "11", "17"};
        bar.setValue2(s2);
        //String[] strArray3 = {"13", "12", "23", "11", "17"};
        bar.setValue3(s3);
        home.setBar(bar);
        return home;
    }

    private List<String> getListSub(List<CountNum> list, String name) {
        List<String> temp = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            if (name.equals("name")) {

                temp.add(list.get(i).getName());
            }
            if (name.equals("num1")) {

                temp.add(list.get(i).getNum1());
            }
            if (name.equals("num2")) {

                temp.add(list.get(i).getNum2());
            }
            if (name.equals("num3")) {

                temp.add(list.get(i).getNum3());
            }
        }

        return temp;
    }

}