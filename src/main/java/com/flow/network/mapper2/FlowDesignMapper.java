package com.flow.network.mapper2;

import com.flow.network.domain2.FlowDesignEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowDesignMapper {
    public static String TbaleName="t_flow";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified)values(null,#{Name},#{Type},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID},#{FlowJson},#{FlowOutStr},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'))")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    int insert(FlowDesignEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")

    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type}, Describes =#{Describes},LastModified=DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S')  where ID=#{ID}")
    int updateByPrimaryKey(FlowDesignEntity entity);

    @Update("update "+TbaleName+" set FlowJson =#{FlowJson}, FlowOutStr=#{FlowOutStr}, LastModified=DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S') ,CheckGraph=#{CheckGraph} where ID=#{ID}")
    int updateJsonByPrimaryKey(FlowDesignEntity entity);
    //查询一个Person


    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified from "+TbaleName+" where ID=#{id}")
    FlowDesignEntity getFlowDesignByID(Integer id);
    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);
    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified,CheckGraph from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<FlowDesignEntity> searchByName(String name,Integer uid);
}
