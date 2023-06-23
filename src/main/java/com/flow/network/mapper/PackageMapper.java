package com.flow.network.mapper;

import com.flow.network.domain.PackageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageMapper {
    //增加一个Person
    @Insert("insert into package(id,name,type,packID,unpackID)values(null,#{name},#{type},#{packID},#{unpackID})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(PackageEntity packageEntity );
    //删除一个Person
    @Delete("delete from package where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update package set name =#{name},packID=#{packID},unpackID=#{unpackID}  where id=#{id}")
    int updateByPrimaryKey(PackageEntity packageEntity );
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    PackageEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type,packID,unpackID from package")
    List<PackageEntity> getList();

    @Insert("insert into package(id,name,type,packID,unpackID) select null,CONCAT(name,'_复制'),type,packID,unpackID from package where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(PackageEntity packageEntity );
}
