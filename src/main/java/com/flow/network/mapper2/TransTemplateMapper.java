package com.flow.network.mapper2;

import com.flow.network.domain2.TransTemplateEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransTemplateMapper {
    public static String TbaleName="t_transtemplate";
    public static String TbaleName1="t_transclassfy";

    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,LastModified,ClassfyID)values(null,#{Name},#{Type},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID},#{FlowJson},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{ClassfyID})")
    int insert(TransTemplateEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where ClassfyID = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type}, Describes =#{Describes},LastModified=DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S')  where ID=#{ID}")
    int updateByPrimaryKey(TransTemplateEntity entity);

    @Update("update "+TbaleName+" set FlowJson =#{FlowJson}, FlowOutStr =#{FlowOutStr}, LastModified=DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S')  where ID=#{ID}")
    int updateJsonByPrimaryKey(TransTemplateEntity entity);
    //查询一个Person


    @Select("select ID,Name,Type,Describes,ClassfyID,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified from "+TbaleName+" where ID=#{id}")
    TransTemplateEntity getFlowDesignByID(Integer id);
    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and ID!=#{id}")
    Integer selectByName(String name,Integer id);
    @Select("select ID,Name,Type,Describes,CreateTime,AuthorID,FlowJson,FlowOutStr,LastModified from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<TransTemplateEntity> searchByName(String name,Integer uid);

    @Select("select ID,Name,Type,ClassfyID,Describes,CreateTime,AuthorID,FlowJson,LastModified from "+TbaleName+" where  Name like concat('%',#{name},'%') and ClassfyID=#{pid} order by LastModified desc")
    List<TransTemplateEntity> searchByPID(String name,Integer pid);
}
