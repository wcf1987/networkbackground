package com.flow.network.mapper2;

import com.flow.network.domain2.FlowDistributionEntity;
import com.flow.network.domain2.MessTranslateEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowDistributionMapper {
    public static String TbaleName="t_flowdistribution";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,FlowID,GatewayIDs,Describes,CreateTime,AuthorID)values(null,#{Name},#{FlowID},#{GatewayIDs},#{Describes},#{CreateTime},#{AuthorID})")
    int insert(FlowDistributionEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},   Describes =#{Describes}, FlowID =#{FlowID},GatewayIDs =#{GatewayIDs}  where ID=#{ID}")
    int updateByPrimaryKey(FlowDistributionEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    FlowDistributionEntity selectByPrimaryKey(Integer id);
    //查询所有的Person



    @Select("select ID,Name,FlowID,GatewayIDs,Describes,CreateTime,AuthorID from "+TbaleName+"  where   AuthorID=#{uid} and Name like concat('%',#{name},'%')")
    List<FlowDistributionEntity> searchByName(String name,Integer uid);
}