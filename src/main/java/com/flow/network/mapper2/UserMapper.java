package com.flow.network.mapper2;

import com.flow.network.domain2.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    public static String TbaleName="t_user";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(id,userName,userNickname,roleSign,department,phone,email,status,describes,createTime,password)values(null,#{userName},#{userNickname},#{roleSign},#{department},#{phone},#{email},#{status},#{describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{password})")
    int insert(UserEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where id = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set userNickname =#{userNickname},  roleSign=#{roleSign}, phone =#{phone}, email =#{email} ,status=#{status},describes=#{describes} ,password=#{password} where id=#{id}")
    int updateByPrimaryKey(UserEntity entity);
    //查询一个Person
    @Select("select t0.id,t0.userName,t0.userNickname,t0.roleSign ,t0.phone,t0.email ,t1.menustr from  "+TbaleName+"  t0, t_role t1 where userName = #{name} and password=#{pass} and t0.roleSign=t1.id")
    UserEntity check(String name,String pass );
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<UserEntity> getList(Integer uid);


    @Select("select t0.id,userName,userNickname,t0.roleSign,t1.roleName as roleStr,department,phone,email,t0.status,t0.describes,t0.createTime from "+TbaleName+" t0 LEFT JOIN t_role t1 ON t0.roleSign=t1.id where  userName like concat('%',#{userName},'%')")
    List<UserEntity> searchByName(String userName,Integer uid);
}
