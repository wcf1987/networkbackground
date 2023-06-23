package com.flow.network.mapper;

import com.flow.network.domain.MessageDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageDetailMapper {
    public static String TbaleName="messageDetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,ename,length,valuestr,optional,messageID,type,pid)values(null,#{name},#{ename},#{length},#{valuestr},#{optional},#{messageID},#{type},#{pid})")
    int insert(MessageDetailEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,valuestr,optional,messageID,type,pid) select null,name,ename,length,valuestr,optional,#{newid},type,pid from "+TbaleName+" where messageID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where messageid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name}, ename =#{ename}, valuestr =#{valuestr} ,length=#{length}, optional =#{optional}where id=#{id}")
    int updateByPrimaryKey(MessageDetailEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    MessageDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,ename,length,valuestr,optional,messageid,pid,type from "+TbaleName+" where messageid=#{id} and pid=0")
    List<MessageDetailEntity> getList(Integer id);

    @Select("select id,name,ename,length,valuestr,optional,messageid,pid,type  from "+TbaleName+" where messageid=#{mid} and pid=#{pid}")
    List<MessageDetailEntity> getListByPid(Integer mid,Integer pid);
}
