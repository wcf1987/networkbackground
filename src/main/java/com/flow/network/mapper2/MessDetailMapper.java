package com.flow.network.mapper2;

import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.MessDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessDetailMapper {
    public static String TbaleName="t_messdetail";
    public static String TbaleName1="t_messdetailcustom";
    public static String TbaleName2="t_fieldsdetail";

    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,TType,OrderID,OutID,OutType,PID,NestID,Flag,EName,Describes,MaxGroupNum) values(null,#{Name},#{TType},#{OrderID},#{OutID},#{OutType},#{PID},#{NestID},#{Flag},#{EName},#{Describes},#{MaxGroupNum})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    int insert(MessDetailEntity entity);

    @Insert("insert into "+TbaleName1+"(ID,Type,Describes,CreateTime,AuthorID,ShortName,EName,Length)values(null,#{Type},#{Describes},#{CreateTime},#{AuthorID},#{ShortName},#{EName},#{Length})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    int insertCustom(MessDetailEntity entity);


    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where PID = #{pid} and TType=#{ttype}")
    int deleteByPID(Integer pid,String ttype);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},OutID=#{OutID}, NestID=#{NestID}, Flag =#{Flag}, EName=#{EName} ,Describes=#{Describes},MaxGroupNum=#{MaxGroupNum} where ID=#{ID}")
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
    @Select("select t2.ID,t3.IDNO as DFINO,t3.Version as DFIVersion,t2.DUINO as DUINO,t2.DUIVersion as DUIVersion,t1.Flag,t2.`Name`,t2.EName,t2.ShortName,t2.Type,t2.Describes,t2.Length,t2.TypeCode,t2.TableName,t2.TableSaveName from  t_messdetail t1,t_fieldsdetail t2,t_fields t3 where t1.OutType='fields' and t1.OutID=t2.ID  AND t2.DFIID=t3.ID and t1.ID=#{id}")
    FieldsDetailEntity selectFieldsInfoByID(String id) throws Exception;
    @Select("select DISTINCT t2.ID,t3.IDNO as DFINO,t3.Version as DFIVersion,t2.DUINO as DUINO,t2.DUIVersion as DUIVersion,t2.`Name`,t2.EName,t2.ShortName,t2.Type,t2.Describes,t2.Length,t2.TypeCode,t2.TableName,t2.TableSaveName from  t_messdetail t1,t_fieldsdetail t2,t_fields t3 where t1.OutType='fields' and t1.OutID=t2.ID  AND t2.DFIID=t3.ID and t1.Name=#{name} and t1.PID=#{sourceid}")
    FieldsDetailEntity selectFieldsInfoByName(String name,String sourceid);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag ,t0.EName,t0.Describes,t0.MaxGroupNum from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> searchByName(String name, Integer uid,Integer pid,String ttype,Integer nestid);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag,t0.EName,t0.Describes,t0.MaxGroupNum from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> getListByNestID(String name,Integer uid, Integer pid,String ttype,Integer nestid);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag,t0.EName,t0.Describes,t0.MaxGroupNum from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> searchByPID(Integer pid,Integer nestid,String ttype);

}
