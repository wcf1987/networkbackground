package com.flow.network.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateTools {
    public static String getNowStr(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentTime = dateFormat.format(date);
        return currentTime;
    }
}



