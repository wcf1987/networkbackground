package com.flow.network.mapper2;

import com.flow.network.domain2.RoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper {
    public static String TbaleName="t_role";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,roleName,roleSign,menustr,status,describes,createTime)values(null,#{roleName},#{roleSign},#{menustr},#{status},#{describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'))")
    int insert(RoleEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set roleName =#{roleName},  roleSign=#{roleSign}, menustr =#{menustr},status=#{status},describes=#{describes} where id=#{id}")
    int updateByPrimaryKey(RoleEntity entity);
    //查询一个Person
    @Select("select id,roleName,roleSign,menustr,status,describes,createTime from  "+TbaleName+"  where id = #{id}")
    RoleEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<RoleEntity> getList(Integer uid);


    @Select("select id,roleName,roleSign,menustr,status,describes,createTime from "+TbaleName+" where roleName like concat('%',#{roleName},'%')")
    List<RoleEntity> searchByName(String roleName,Integer uid);
}
