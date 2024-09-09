package com.flow.network.mapper2;

import com.flow.network.domain2.TransClassfyEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransClassfyMapper {
    public static String TbaleName="t_transclassfy";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Code,Describes,CreateTime,AuthorID)values(null,#{Name},#{Type},#{Code},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(TransClassfyEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type}, Code=#{Code},Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(TransClassfyEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    TransClassfyEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<TransClassfyEntity> getList(Integer uid);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id} and Type=#{type}")
    Integer selectByName(String name,String type,Integer id);

    @Select("select count(*) from  "+TbaleName+"  where Code = #{code} and ID!=#{id}")
    Integer selectByCode(String code,Integer id);

    @Select("select ID,Name,Type,Code,Describes,CreateTime,AuthorID from "+TbaleName+" where  Name like concat('%',#{name},'%') and Type=#{type}")
    List<TransClassfyEntity> searchByName(String name,String type,Integer uid);
}
