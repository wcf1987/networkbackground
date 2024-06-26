package com.flow.network.mapper2;

import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.FieldsStatisticsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FieldsDetailMapper {
    public static String TbaleName="t_fieldsdetail";
    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,Type,Describes,CreateTime,AuthorID,ShortName,DFIID,DUINO,DUIVersion,EName,TypeCode,Length,TableName,TableSaveName,Flag)values(null,#{Name},#{Type},#{Describes},DATE_FORMAT(now(),'%Y-%m-%d %H:%i:%S'),#{AuthorID},#{ShortName},#{DFIID},#{DUINO},#{DUIVersion},#{EName},#{TypeCode},#{Length},#{TableName},#{TableSaveName},#{Flag})")
    int insert(FieldsDetailEntity entity);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where DFIID = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName+" set Name =#{Name},  Type=#{Type},DUINO=#{DUINO},ShortName=#{ShortName},DUIVersion=#{DUIVersion}, Describes =#{Describes},EName =#{EName},TypeCode =#{TypeCode},Length =#{Length},TableName =#{TableName},TableSaveName =#{TableSaveName} ,Flag =#{Flag}  where ID=#{ID}")
    int updateByPrimaryKey(FieldsDetailEntity entity);
    //查询一个Person
    @Select("select * from  "+TbaleName+"  where id = #{id}")
    FieldsDetailEntity getById(Integer id);
    //查询所有的Person
    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<FieldsDetailEntity> getList(Integer uid);

    @Select("select count(*) from  "+TbaleName+"  where Name = #{name} and DFIID=#{pid} and ID!=#{id}")
    Integer selectByName(String name,Integer pid,Integer id);
    @Select("select count(*) from  "+TbaleName+"  where dfiid = #{dfi} and duino=#{dui} and ID!=#{id}")
    Integer selectByDFIDUI(Integer dfi,String dui,Integer id);
    @Select("select t0.ID,t0.Name,t0.Type,t0.Describes,t0.CreateTime,t0.AuthorID,t0.ShortName,t0.DFIID,t1.IDNO as DFINO,t1.Version as DFIVersion,t0.DUINO,t0.DUIVersion,t0.EName,t0.TypeCode,t0.Length,t0.TableName,t0.TableSaveName,t0.Flag from "+TbaleName+" t0, t_fields t1 where t0.DFIID=t1.ID and  t0.DFIID=#{pid} and t0.DUINO like concat('%',#{name},'%') order by DUINO ${order}")
    List<FieldsDetailEntity> searchByName(String name,Integer uid,Integer pid,String order);
    @Select("select t0.ID,t0.Name,t0.Type,t0.Describes,t0.CreateTime,t0.AuthorID,t0.ShortName,t0.DFIID,t1.IDNO as DFINO,t1.Version as DFIVersion,t0.DUINO,t0.DUIVersion,t0.EName,t0.TypeCode,t0.Length,t0.TableName,t0.TableSaveName,t0.Flag from "+TbaleName+" t0, t_fields t1 where t0.DFIID=t1.ID  and t0.Name like concat('%',#{name},'%') order by DUINO ${order}")
    List<FieldsDetailEntity> searchByNameNODFI(String name,Integer uid,Integer pid,String order);


    @Select("select t0.ID,t0.Name,t0.Type,t0.Describes,t0.CreateTime,t0.AuthorID,t0.ShortName,t0.DFIID,t1.IDNO as DFINO,t1.Version as DFIVersion,t0.DUINO,t0.DUIVersion,t0.EName,t0.TypeCode,t0.Length,t0.TableName,t0.TableSaveName,t0.Flag from "+TbaleName+" t0, t_fields t1 where t0.DFIID=t1.ID and t0.Name like concat('%',#{name},'%') order by t0.DUINO asc")
    List<FieldsDetailEntity> searchByNameAll(String name,Integer uid);

    @Select("select t1.IDNO as DFINO,count(t0.DUINO) as DUINum ,count(t0.DUINO)/#{counts} as DUIPer   from "+TbaleName+" t0, t_fields t1 where t0.DFIID=t1.ID group by t1.IDNO")
    List<FieldsStatisticsEntity> statistics(Integer counts);

    @Select("select count(*) from "+TbaleName+" t0 where t0.DFIID!='' ")
    Integer searchAllDUI();

}
