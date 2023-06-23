package com.flow.network.mapper;

import com.flow.network.domain.InterfaceDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InterfaceDetailMapper {
    //增加一个Person
    @Insert("insert into interfaceDetail(id,name,ename,length,valuestr,interfaceID)values(null,#{name},#{ename},#{length},#{valuestr},#{interfaceID})")
    int insert(InterfaceDetailEntity interfaceDetailEntity);

    @Insert("insert into interfaceDetail(id,name,ename,length,valuestr,interfaceID) select null,name,ename,length,valuestr,#{newid} from interfaceDetail where interfaceID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from interfaceDetail where id = #{id}")
    int delete(Integer id);

    @Delete("delete from interfaceDetail where interfaceID = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update interfaceDetail set name =#{name}, ename =#{ename}, valuestr =#{valuestr} where id=#{id}")
    int updateByPrimaryKey(InterfaceDetailEntity interfaceDetailEntity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    InterfaceDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,ename,length,valuestr,interfaceid from interfaceDetail where interfaceID=#{id}")
    List<InterfaceDetailEntity> getList(Integer id);
}
