package com.flow.network.mapper;

import com.flow.network.domain.RuleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RuleMapper {
    public static String TbaleName="rule";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,type,sourceid,targetid)values(null,#{name},#{type},#{sourceid},#{targetid})")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int insert(RuleEntity entity );
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name},sourceid=#{sourceid},targetid=#{targetid} where id=#{id}")
    int updateByPrimaryKey(RuleEntity entity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    RuleEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select t0.id,t0.name,t0.type,t0.sourceid,t0.targetid,t1.name as sourcedata,t2.name as targetdata from "+TbaleName+" t0 ,message t1,message t2 where t1.id=sourceid and t2.id=targetid")
    List<RuleEntity> getList();

    @Insert("insert into "+TbaleName+"(id,name,type,sourceid,targetid) select null,CONCAT(name,'_复制'),type,sourceid,targetid from "+TbaleName+" where id=#{id}")
    @SelectKey(keyProperty = "id",keyColumn = "id",resultType = int.class,before = false,statement = "select last_insert_id()")
    int copy(RuleEntity entity );
}
