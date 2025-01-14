package com.flow.network.mapper2;

import com.flow.network.domain2.MessTranslateEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessTraslateMapper {
    public static String TbaleName="t_messtranslate";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,sourceID,targetID,Describes,CreateTime,AuthorID)values(null,#{Name},#{sourceID},#{targetID},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID})")
    int insert(MessTranslateEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);

    @Select("select max(ID) from t_messbody")
    Integer getMaxMessBodyID();
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},   Describes =#{Describes}  where ID=#{ID}")
    int updateByPrimaryKey(MessTranslateEntity entity);
    //查询一个Person
    @Select("select id,name ,age from  "+TbaleName+"  where id = #{id}")
    MessTranslateEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<MessTranslateEntity> getList(Integer uid);
    @Select("select count(*) from  "+TbaleName+"  where Name = #{name}  and ID!=#{id}")
    Integer selectByName(String name,Integer id);

    @Select("select t0.ID,t0.Name,t0.sourceID,t0.targetID,t1.name as sourmess,t2.name as tarmess,t0.Describes,t0.CreateTime,t0.AuthorID from "+TbaleName+" t0,t_messbody t1,t_messbody t2 where t0.sourceID=t1.ID and  t0.targetID=t2.ID and    t0.Name like concat('%',#{name},'%')")
    List<MessTranslateEntity> searchByName(String name,Integer uid);
}
