package com.flow.network.mapper;

import com.flow.network.domain.AppEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppMapper {
    public static String TbaleName="app";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,type,packID,unpackID)values(null,#{name},#{type},#{packID},#{unpackID})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(AppEntity entity );
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name},packID=#{packID},unpackID=#{unpackID} where id=#{id}")
    int updateByPrimaryKey(AppEntity entity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    AppEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type,packID,unpackID from "+TbaleName)
    List<AppEntity> getList();

    @Insert("insert into "+TbaleName+"(id,name,type,packID,unpackID) select null,CONCAT(name,'_复制'),type,packID,unpackID from "+TbaleName+" where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(AppEntity entity );
}
