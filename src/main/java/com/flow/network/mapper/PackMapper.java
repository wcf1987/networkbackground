package com.flow.network.mapper;

import com.flow.network.domain.PackEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackMapper {
    public static String TbaleName="pack";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,type,calladdr)values(null,#{name},#{type},#{calladdr})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(PackEntity entity );
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name},calladdr =#{name},type =#{type} where id=#{id}")
    int updateByPrimaryKey(PackEntity entity);
    //查询一个Person
    @Select("select name  from "+TbaleName+" where id = #{id}")
    PackEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type,calladdr from "+TbaleName)
    List<PackEntity> getList();

    @Select("select id,name,type,calladdr from "+TbaleName+" where type=#{type}")
    List<PackEntity> getListByType(String type);

    @Insert("insert into "+TbaleName+"(id,name,type) select null,CONCAT(name,'_复制'),type from "+TbaleName+" where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(PackEntity entity );
}
