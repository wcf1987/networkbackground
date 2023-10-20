package com.flow.network.mapper2;

import com.flow.network.domain2.PackageDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageDetailMapper {
    public static String TbaleName="t_packagedetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Length,ArrayOr,DefaultValue,packID,Describes,CreateTime,AuthorID)values(null,#{Name},#{Type},#{Length},#{ArrayOr},#{DefaultValue},#{packID},#{Describes},#{CreateTime},#{AuthorID})")
    int insert(PackageDetailEntity entity);


    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type},Length=#{Length},ArrayOr=#{ArrayOr},DefaultValue=#{DefaultValue}, Describes =#{Describes}, CreateTime =#{CreateTime}  where ID=#{ID}")
    int updateByPrimaryKey(PackageDetailEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    PackageDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person


    @Select("select ID,Name,Type,Length,ArrayOr,DefaultValue,packID,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid} and Name like concat('%',#{name},'%') and packID=#{pid}")
    List<PackageDetailEntity> searchByName(String name,Integer uid,Integer pid);
}
