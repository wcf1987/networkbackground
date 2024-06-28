package com.flow.network.mapper2;

import com.flow.network.domain2.CountNum;
import com.flow.network.domain2.GatewayEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeMapper {
    public static String TbaleName="t_gateway";


    @Select("select ID,Name,IP,Describes,CreateTime,AuthorID from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<GatewayEntity> searchByName();

    @Select("select count(*) from t_flow ")
    String getFlowNum();
    @Select("select count(*) from t_user ")
    String getUserNum();

    @Select("select count(*) from t_fieldsdetail ")
    String getFieldsNum();

    @Select("select count(*) from t_gatewaydistribute ")
    String getDisNum();
    @Select("select count(*) from t_flow where CreateTime>DATE_FORMAT(now(),'%Y-%m-%d') ")
    Integer getFlowNow();

    @Select("select count(*) from t_flowdistribution where CreateTime>DATE_FORMAT(now(),'%Y-%m-%d') ")
    Integer getDisNow();

    @Select("select count(*) from t_user where CreateTime>DATE_FORMAT(now(),'%Y-%m-%d') ")
    Integer getUserNow();

    @Select("select count(*) from t_fieldsdetail where CreateTime>DATE_FORMAT(now(),'%Y-%m-%d') ")
    Integer getFieldsNow();

    @Select("select t0.Name as name,count(t1.ID) as num1 from t_fields t0 ,t_fieldsdetail t1 where t0.ID =t1.DFIID group by t0.Name ")
    List<CountNum> getFieldsClass();

    @Select("select count(*) from t_user where SUBSTR(CreateTime,1,10)=#{ds} ")
    String getUserAdd(String ds);

    @Select("select count(*) from t_logs where SUBSTR(CreateTime,1,10)=#{ds} and Logs=\"登陆成功\"")
    String getUserLogin(String ds);

    @Select("select count(*) from t_logs where SUBSTR(CreateTime,1,10)=#{ds} and Logs like concat(#{ls},'%') ")
    String getFieldsUpdate(String ds,String ls);
}
