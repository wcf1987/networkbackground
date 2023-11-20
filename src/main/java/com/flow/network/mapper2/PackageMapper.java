package com.flow.network.mapper2;

import com.flow.network.domain2.PackageEntity;
import com.flow.network.domain2.SerialInterEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageMapper {
    public static String TbaleName="t_package";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Describes,CreateTime,AuthorID)values(null,#{Name},#{Type},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(PackageEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type}, Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(PackageEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    PackageEntity selectByPrimaryKey(Integer id);

    @Select("select ID,Name from  "+TbaleName+"  where Name = #{name}")
    SerialInterEntity selectByName(String name);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<PackageEntity> getList(Integer uid);


    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<PackageEntity> searchByName(String name,Integer uid);
}
