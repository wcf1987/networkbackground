package com.flow.network.mapper2;

import com.flow.network.domain2.GatewayEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GatewayMapper {
    public static String TbaleName="t_gateway";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,IP,Describes,CreateTime,AuthorID)values(null,#{Name},#{IP},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(GatewayEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  IP=#{IP}, Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(GatewayEntity entity);
    //查询一个Person
    @Select("select ID,Name,IP,Describes,CreateTime,AuthorID from  "+TbaleName+"  where ID = #{id}")
    GatewayEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,IP,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<GatewayEntity> getList(Integer uid);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);
    @Select("select ID,Name,IP,Describes,CreateTime,AuthorID from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<GatewayEntity> searchByName(String name,Integer uid);
}
