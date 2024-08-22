package com.flow.network.mapper2;

import com.flow.network.domain2.BuiltNodeEntity;
import com.flow.network.domain2.OptionListEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OptionListMapper {
    public static String TbaleName="t_optionlist";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Label,Type,Value,Describes,CreateTime,AuthorID)values(null,#{Label},#{Type},#{Value},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(OptionListEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);


    //更改一个Person
    @Update("update "+TbaleName+" set Label =#{Label},  Type=#{Type}, Value=#{Value},Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(OptionListEntity entity);
    //查询一个Person
    @Select("select id,Label ,Value from  "+TbaleName+"  where id = #{id}")
    OptionListEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<OptionListEntity> getList(Integer uid);

    @Select("select count(*) from  "+TbaleName+"  where Label = #{label} and ID!=#{id}")
    Integer selectByName(String label,Integer id);
    @Select("select count(*) from  "+TbaleName+"  where Value = #{value} and ID!=#{id}")
    Integer selectByValue(String value,Integer id);

    @Select("select ID,Label,Type,Value,Describes,CreateTime,AuthorID from "+TbaleName+" where  Label like concat('%',#{name},'%') and Type=#{type}")
    List<OptionListEntity> searchByName(String name,String type);
}
