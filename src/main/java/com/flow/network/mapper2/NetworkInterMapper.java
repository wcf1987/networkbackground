package com.flow.network.mapper2;

import com.flow.network.domain2.NetworkInterEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NetworkInterMapper {
    public static String TbaleName="t_networkinter";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID,Pic)values(null,#{Name},#{Type},#{IP},#{Port},#{Protocol},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S') ,#{AuthorID} ,#{Pic})")
    int insert(NetworkInterEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  IP =#{IP} ,  Port =#{Port},Protocol=#{Protocol}, Describes =#{Describes} ,Pic=#{Pic} where ID=#{ID}")
    int updateByPrimaryKey(NetworkInterEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    NetworkInterEntity selectByPrimaryKey(Integer id);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" ")
    List<NetworkInterEntity> getList(Integer uid);


    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID,Pic from "+TbaleName+" where  Name like concat('%',#{name},'%') order by ${orderField} ${order}")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    List<NetworkInterEntity> searchByName(String name,Integer uid,String order,String orderField);
}
