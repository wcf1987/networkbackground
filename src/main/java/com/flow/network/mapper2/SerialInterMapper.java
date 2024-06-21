package com.flow.network.mapper2;

import com.flow.network.domain2.SerialInterEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SerialInterMapper {
    public static String TbaleName="t_serialinter";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,SerialNO,BAUD,DataBit,StopBit,FlowControl,Describes,CreateTime,AuthorID)values(null,#{Name},#{Type},#{SerialNO},#{BAUD},#{DataBit},#{StopBit},#{FlowControl},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(SerialInterEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  SerialNO =#{SerialNO} ,  BAUD =#{BAUD},DataBit=#{DataBit}, StopBit=#{StopBit},FlowControl=#{FlowControl}, Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(SerialInterEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    SerialInterEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<SerialInterEntity> getList(Integer uid);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);


    @Select("select ID,Name,Type,SerialNO,BAUD,DataBit,StopBit,FlowControl,Describes,CreateTime,AuthorID from "+TbaleName+" where Name like concat('%',#{name},'%') order by ${orderField} ${order}")
    List<SerialInterEntity> searchByName(String name,Integer uid,String order,String orderField);
}
