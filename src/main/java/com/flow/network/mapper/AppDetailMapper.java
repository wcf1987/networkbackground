package com.flow.network.mapper;

import com.flow.network.domain.AppDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppDetailMapper {
    public static String TbaleName="appDetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,ename,length,valuestr,optional,appID)values(null,#{name},#{ename},#{length},#{valuestr},#{optional},#{appID})")
    int insert(AppDetailEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,valuestr,optional,appID) select null,name,ename,length,valuestr,optional,#{newid} from "+TbaleName+" where appID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name}, ename =#{ename}, valuestr =#{valuestr} ,length=#{length}, optional =#{optional}where id=#{id}")
    int updateByPrimaryKey(AppDetailEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    AppDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,ename,length,valuestr,optional,appid from "+TbaleName+" where appid=#{id}")
    List<AppDetailEntity> getList(Integer id);
}
