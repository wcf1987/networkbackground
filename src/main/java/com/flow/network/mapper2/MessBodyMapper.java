package com.flow.network.mapper2;

import com.flow.network.domain2.MessBodyEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessBodyMapper {
    public static String TbaleName="t_messbody";
    //增加一个Person
    @Insert("insert into "+TbaleName+"( ID,Name,Type,Describes,CreateTime,AuthorID,EName)values(null,#{Name},#{Type},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID},#{EName})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    int insert(MessBodyEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type}, Describes =#{Describes} ,EName=#{EName} where ID=#{ID}")
    int updateByPrimaryKey(MessBodyEntity entity);
    //查询一个Person

    //查询所有的Person
    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID from  "+TbaleName+"  where ID = #{id}")
    MessBodyEntity selectByPrimaryKey(Integer id);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);
    @Select("select count(*) from  "+TbaleName+"  where EName = #{ename} and ID!=#{id}")
    Integer selectByEName(String ename,Integer id);
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<MessBodyEntity> getList(Integer uid);


    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID,EName from "+TbaleName+" where  Name like concat('%',#{name},'%') order by ${orderField} ${order}")
    List<MessBodyEntity> searchByName(String name,Integer uid,String order,String orderField);
}
