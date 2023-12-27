package com.flow.network.mapper2;

import com.flow.network.domain2.MessDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessDetailMapper {
    public static String TbaleName="t_messdetail";
    public static String TbaleName1="t_messdetailcustom";
    public static String TbaleName2="t_fieldsdetail";

    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,TType,OrderID,OutID,OutType,PID,NestID,Flag,EName) values(null,#{Name},#{TType},#{OrderID},#{OutID},#{OutType},#{PID},#{NestID},#{Flag},#{EName})")
    int insert(MessDetailEntity entity);

    @Insert("insert into "+TbaleName1+"(ID,Type,Describes,CreateTime,AuthorID,ShortName,EName,Length)values(null,#{Type},#{Describes},#{CreateTime},#{AuthorID},#{ShortName},#{EName},#{Length})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    int insertCustom(MessDetailEntity entity);


    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},OutID=#{OutID}, NestID=#{NestID}, Flag =#{Flag}, EName=#{EName}  where ID=#{ID}")
    int updateByPrimaryKey(MessDetailEntity entity);
    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and PID=#{pid} and ID!=#{id}")
    Integer selectByName(String name,Integer pid,Integer id);

    @Update("update "+TbaleName1+" set EName =#{EName}, ShortName=#{ShortName},Length =#{Length},Type =#{Type}  where ID=#{OutID}")
    int updateCustomByPrimaryKey(MessDetailEntity entity);

    @Update("update "+TbaleName+" set OrderID =#{OrderID}  where ID=#{ID}")
    int updateOrderIDByPrimaryKey(MessDetailEntity entity);

    //查询一个Person
    @Select("select ID,EName,Type,ShortName ,Length from  "+TbaleName1+"  where ID = #{id}")
    MessDetailEntity selectCustomByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,EName,Type,ShortName ,Length,DFIID from  "+TbaleName2+"  where ID = #{id}")
    MessDetailEntity selectFieldsByPrimaryKey(Integer id);

    @Select("select ID,NestID,OrderID,OutType from  "+TbaleName+"  where ID = #{id}")
    MessDetailEntity selecByPrimaryKey(Integer id);

    @Select("select count(ID) from  "+TbaleName+"  where NestID = #{id}")
    Integer countByNestID(Integer id);


    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag ,t0.EName from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> searchByName(String name, Integer uid,Integer pid,String ttype,Integer nestid);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag,t0.EName from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> getListByNestID(String name,Integer uid, Integer pid,String ttype,Integer nestid);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag,t0.EName from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> searchByPID(Integer pid,Integer nestid,String ttype);

}
