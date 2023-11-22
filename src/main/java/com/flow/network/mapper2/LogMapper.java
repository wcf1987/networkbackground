package com.flow.network.mapper2;

import com.flow.network.domain2.LogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    public static String TbaleName="t_logs";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Username,Type,Logs,CreateTime)values(null,#{Username},#{Type},#{Logs},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'))")
    int insert(LogEntity entity);

    @Select("select Username from  t_user  where  ID=#{id}")
    String selectByID(Integer id);

    @Select("select ID,Username,Type,Logs,CreateTime from "+TbaleName+" where  Username like concat('%',#{name},'%') order by CreateTime desc ")
    List<LogEntity> searchByName(String name);
}
