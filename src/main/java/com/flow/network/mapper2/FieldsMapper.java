package com.flow.network.mapper2;

import com.flow.network.domain2.FieldsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FieldsMapper {
    public static String TbaleName="t_fields";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,IDNO,Version,ShortName,ApplicableMess,Describes,CreateTime,AuthorID)values(null,#{Name},#{IDNO},#{Version},#{ShortName},#{ApplicableMess},#{Describes},#{CreateTime},#{AuthorID})")
    int insert(FieldsEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  IDNO=#{IDNO},Version=#{Version},ShortName=#{ShortName},ApplicableMess=#{ApplicableMess}, Describes =#{Describes}, CreateTime =#{CreateTime}  where ID=#{ID}")
    int updateByPrimaryKey(FieldsEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    FieldsEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<FieldsEntity> getList(Integer uid);


    @Select("select ID,Name,IDNO,Version,ShortName,ApplicableMess,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid} and Name like concat('%',#{name},'%')")
    List<FieldsEntity> searchByName(String name,Integer uid);
}
