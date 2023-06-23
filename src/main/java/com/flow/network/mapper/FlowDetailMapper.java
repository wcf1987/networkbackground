package com.flow.network.mapper;

import com.flow.network.domain.FlowDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FlowDetailMapper {
    public static String TbaleName="flowDetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,bpmnstr,flowID)values(null,#{bpmnstr},#{flowID})")
    int insert(FlowDetailEntity entity);

    @Insert("insert into "+TbaleName+" (id,bpmnstr,flowID) select null,bpmnstr,#{newid} from "+TbaleName+" where flowID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set  bpmnstr =#{bpmnstr} where flowID=#{flowID}")
    int updateByPrimaryKey(FlowDetailEntity entity);
    //查询一个Person
    @Select("select id,bpmnstr ,flowID from  "+TbaleName+"  where flowID = #{id}")
    FlowDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,ename,length,valuestr,optional,appid from "+TbaleName+" where appid=#{id}")
    List<FlowDetailEntity> getList(Integer id);
}
