package com.flow.network.mapper;

import com.flow.network.domain.InterfaceEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InterfaceMapper {
    //增加一个Person
    @Insert("insert into interface(id,name,type)values(null,#{name},#{type})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(InterfaceEntity interfaceEntity);
    //删除一个Person
    @Delete("delete from interface where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update interface set name =#{name} where id=#{id}")
    int updateByPrimaryKey(InterfaceEntity interfaceEntity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    InterfaceEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type from interface")
    List<InterfaceEntity> getList();

    @Insert("insert into interface(id,name,type) select null,CONCAT(name,'_复制'),type from interface where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(InterfaceEntity interfaceEntity);
}
