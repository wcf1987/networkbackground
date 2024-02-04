package com.flow.network.mapper2;

import com.flow.network.domain2.PackageDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageDetailMapper {
    public static String TbaleName="t_packagedetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,EName,Type,Length,ArrayOr,DefaultValue,packID,Describes,CreateTime,AuthorID)values(null,#{Name},#{EName},#{Type},#{Length},#{ArrayOr},#{DefaultValue},#{packID},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(PackageDetailEntity entity);
    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and packID=#{pid} and ID!=#{id}")
    Integer selectByName(String name,Integer pid,Integer id);

    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name}, EName =#{EName},  Type=#{Type},Length=#{Length},ArrayOr=#{ArrayOr},DefaultValue=#{DefaultValue}, Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(PackageDetailEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    PackageDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person


    @Select("select ID,Name,EName,Type,Length,ArrayOr,DefaultValue,packID,Describes,CreateTime,AuthorID from "+TbaleName+" where Name like concat('%',#{name},'%') and packID=#{pid}")
    List<PackageDetailEntity> searchByName(String name,Integer uid,Integer pid);
}
