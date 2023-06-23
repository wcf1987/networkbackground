package com.flow.network.mapper;

import com.flow.network.domain.RuleDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RuleDetailMapper {
    public static String TbaleName="ruleDetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID)values(null,#{name},#{ename},#{length},#{rulestr},#{optional},#{type},#{sourceid},#{targetid},#{sourcedata},#{targetdata},#{funcrule},#{ruleID})")
    int insert(RuleDetailEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,valuestr,optional,appID) select null,name,ename,length,valuestr,optional,#{newid} from "+TbaleName+" where appID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set name =#{name},  sourcedata =#{sourcedata} ,  targetdata =#{targetdata},funcrule=#{funcrule}, rulestr =#{rulestr}, type =#{type}  where id=#{id}")
    int updateByPrimaryKey(RuleDetailEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    RuleDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID from "+TbaleName+" where ruleid=#{id}")
    List<RuleDetailEntity> getList(Integer id);
}
