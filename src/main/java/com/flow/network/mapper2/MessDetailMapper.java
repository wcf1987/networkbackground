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
    @Insert("insert into "+TbaleName+"(ID,Name,TType,OrderID,OutID,OutType,PID,NestID,Flag) values(null,#{Name},#{TType},#{OrderID},#{OutID},#{OutType},#{PID},#{NestID},#{Flag})")
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
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type},DUINO=#{DUINO},ShortName=#{ShortName},DUIVersion=#{DUIVersion}, Describes =#{Describes},EName =#{EName},TypeCode =#{TypeCode},Length =#{Length},TableName =#{TableName},TableSaveName =#{TableSaveName}  where ID=#{ID}")
    int updateByPrimaryKey(FieldsDetailEntity entity);
    //查询一个Person
    @Select("select ID,EName,Type,ShortName ,Length from  "+TbaleName1+"  where ID = #{id}")
    MessDetailEntity selectCustomByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,EName,Type,ShortName ,Length from  "+TbaleName2+"  where ID = #{id}")
    MessDetailEntity selectFieldsByPrimaryKey(Integer id);

    @Select("select ID,NestID,OrderID from  "+TbaleName+"  where ID = #{id}")
    MessDetailEntity selecByPrimaryKey(Integer id);

    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<FieldsDetailEntity> getList(Integer uid);


    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=0 order by OrderID asc")
    List<MessDetailEntity> searchByName(String name, Integer uid,Integer pid,String ttype);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> getListByNestID(String name,Integer uid, Integer pid,String ttype,Integer nestid);
}
