package com.flow.network.mapper;

import com.flow.network.domain.PackageDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageDetailMapper {
    //增加一个Person
    @Insert("insert into packageDetail(id,name,ename,length,valuestr,packageID)values(null,#{name},#{ename},#{length},#{valuestr},#{packageID})")
    int insert(PackageDetailEntity packageDetailEntity);

    @Insert("insert into packageDetail(id,name,ename,length,valuestr,packageID) select null,name,ename,length,valuestr,#{newid} from packageDetail where packageID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from packageDetail where id = #{id}")
    int delete(Integer id);

    @Delete("delete from packageDetail where packageID = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update packageDetail set name =#{name}, ename =#{ename}, valuestr =#{valuestr} where id=#{id}")
    int updateByPrimaryKey(PackageDetailEntity packageDetailEntity);
    //查询一个Person
    @Select("select id,name ,age from person where id = #{id}")
    PackageDetailEntity selectByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select id,name,ename,length,valuestr,packageID from packageDetail where packageID=#{id}")
    List<PackageDetailEntity> getList(Integer id);
}
