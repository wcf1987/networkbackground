package com.flow.network.mapper;

import com.flow.network.domain.MessageEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageMapper {
    public static String TbaleName="message";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,type)values(null,#{name},#{type})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(MessageEntity entity );
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name} where id=#{id}")
    int updateByPrimaryKey(MessageEntity entity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    MessageEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,type from "+TbaleName)
    List<MessageEntity> getList();

    @Insert("insert into "+TbaleName+"(id,name,type) select null,CONCAT(name,'_复制'),type from "+TbaleName+" where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(MessageEntity entity );
}
