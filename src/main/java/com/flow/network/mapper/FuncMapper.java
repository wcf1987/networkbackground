package com.flow.network.mapper;

import com.flow.network.domain.FuncEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FuncMapper {
    public static String TbaleName="func";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,type,descri)values(null,#{name},#{type},#{descri})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(FuncEntity entity );
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name}, descri =#{descri}, type =#{type} where id=#{id}")
    int updateByPrimaryKey(FuncEntity entity);
    //查询一个Person
    @Select("select id,name ,age from "+TbaleName+" where id = #{id}")
    FuncEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type,descri from "+TbaleName)
    List<FuncEntity> getList();

    @Select("select id,name,type,descri from "+TbaleName+" where type=#{type}")
    List<FuncEntity> getListByType(String type);

    @Insert("insert into "+TbaleName+"(id,name,type) select null,CONCAT(name,'_复制'),type from "+TbaleName+" where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(FuncEntity entity );
}
