package com.flow.network.mapper2;

import com.flow.network.domain2.FlowDesignEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowDesignMapper {
    public static String TbaleName="t_flow";
    public static String TbaleName1="t_transclassfy";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified,ClassfyID)values(null,#{Name},#{Type},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID},#{FlowJson},#{FlowOutStr},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{ClassfyID})")
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

    @Update("update "+TbaleName+" set FlowJson =#{FlowJson}, FlowOutStr=#{FlowOutStr}, LastModified=DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S') ,CheckGraph=#{CheckGraph},SourceIP=#{SourceIP},TargetIP=#{TargetIP} where ID=#{ID}")
    int updateJsonByPrimaryKey(FlowDesignEntity entity);
    //查询一个Person


    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified from "+TbaleName+" where ID=#{id}")
    FlowDesignEntity getFlowDesignByID(Integer id);
    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);
    @Select("select t0.ID,t0.Name,t0.Type,t0.Describes,t0.CreateTime,t0.AuthorID,FlowJson,FlowOutStr,LastModified,CheckGraph,SourceIP,TargetIP,t0.classfyid,t1.Name as ClassfyName from "+TbaleName+" t0, "+TbaleName1+" t1 where  t0.Name like concat('%',#{name},'%') and t0.ClassfyID=t1.ID")
    List<FlowDesignEntity> searchByName(String name,Integer uid);
}
