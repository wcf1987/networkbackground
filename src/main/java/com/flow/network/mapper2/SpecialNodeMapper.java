package com.flow.network.mapper2;

import com.flow.network.domain2.SpecialNodeEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SpecialNodeMapper {
    public static String TbaleName="t_specialnode";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Code,Describes,CreateTime,AuthorID)values(null,#{Name},#{Type},#{Code},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(SpecialNodeEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type}, Code=#{Code},Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(SpecialNodeEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    SpecialNodeEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<SpecialNodeEntity> getList(Integer uid);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);

    @Select("select count(*) from  "+TbaleName+"  where Code = #{code} and ID!=#{id}")
    Integer selectByCode(String code,Integer id);

    @Select("select ID,Name,Type,Code,Describes,CreateTime,AuthorID from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<SpecialNodeEntity> searchByName(String name,Integer uid);
}
