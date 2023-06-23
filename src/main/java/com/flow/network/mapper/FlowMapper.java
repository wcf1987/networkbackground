package com.flow.network.mapper;

import com.flow.network.domain.FlowEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowMapper {
    public static String TbaleName="flow";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,type)values(null,#{name},#{type})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(FlowEntity entity );
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name} where id=#{id}")
    int updateByPrimaryKey(FlowEntity entity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    FlowEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type from "+TbaleName)
    List<FlowEntity> getList();

    @Insert("insert into "+TbaleName+"(id,name,type) select null,CONCAT(name,'_复制'),type from "+TbaleName+" where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(FlowEntity entity );
}
