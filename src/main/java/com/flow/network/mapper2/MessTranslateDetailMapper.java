package com.flow.network.mapper2;

import com.flow.network.domain2.FieldsDetailEntity;
import com.flow.network.domain2.MessDetailEntity;
import com.flow.network.domain2.MessTraslateDetailEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessTranslateDetailMapper {
    public static String TbaleName="t_messdetail";
    public static String TbaleName1="t_messdetailcustom";
    public static String TbaleName2="t_fieldsdetail";

    public static String TbaleName3="t_messtranslatedetail";

    //增加一个Person
    @Insert("insert into "+TbaleName+"(ID,Name,TType,OrderID,OutID,OutType,PID,NestID,Flag) values(null,#{Name},#{TType},#{OrderID},#{OutID},#{OutType},#{PID},#{NestID},#{Flag})")
    int insert(MessDetailEntity entity);

    @Insert("insert into "+TbaleName1+"(ID,Type,Describes,CreateTime,AuthorID,ShortName,EName,Length)values(null,#{Type},#{Describes},#{CreateTime},#{AuthorID},#{ShortName},#{EName},#{Length})")
    @Options(useGeneratedKeys = true, keyProperty = "ID", keyColumn = "ID")
    int insertCustom(MessDetailEntity entity);

    @Insert("insert into "+TbaleName3+"(ID,FieldsID,TransID) values (null,#{fid},#{tid})")
    int insertByFieldsID(Integer fid,Integer tid);

    @Insert("insert into "+TbaleName+" (id,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,ruleID) select null,name,ename,length,rulestr,optional,type,sourceid,targetid,sourcedata,targetdata,funcrule,#{newid} from "+TbaleName+" where ruleID=#{oldid}")
    int copyByPID(Integer oldid,Integer newid);
    //删除一个Person
    @Delete("delete from "+TbaleName+" where ID = #{id}")
    int delete(Integer id);

    @Delete("delete from "+TbaleName+" where appid = #{id}")
    int deleteByPID(Integer id);
    //更改一个Person
    @Update("update "+TbaleName3+" set TName =#{TName},Optional=#{Optional}, Transrule=#{Transrule}, Describes =#{Describes}, CreateTime =#{CreateTime} , Funcrule =#{Funcrule}, SourceData =#{SourceData} where ID=#{TransDetailID}")
    int updateByPrimaryKey(MessTraslateDetailEntity entity);

    @Update("update "+TbaleName1+" set EName =#{EName}, ShortName=#{ShortName},Length =#{Length},Type =#{Type}  where ID=#{OutID}")
    int updateCustomByPrimaryKey(MessDetailEntity entity);

    @Update("update "+TbaleName+" set OrderID =#{OrderID}  where ID=#{ID}")
    int updateOrderIDByPrimaryKey(MessDetailEntity entity);

    //查询一个Person
    @Select("select ID,EName,Type,ShortName ,Length from  "+TbaleName1+"  where ID = #{id}")
    MessTraslateDetailEntity selectCustomByPrimaryKey(Integer id);
    //查询所有的Person
    @Select("select ID,EName,Type,ShortName ,Length,DFIID from  "+TbaleName2+"  where ID = #{id}")
    MessTraslateDetailEntity selectFieldsByPrimaryKey(Integer id);

    @Select("select ID,NestID,OrderID,OutType from  "+TbaleName+"  where ID = #{id}")
    MessDetailEntity selecByPrimaryKey(Integer id);
    @Select("select ID,TName,FieldsID,TransID,Optional,Transrule,Describes,CreateTime,Funcrule,SourceData from  "+TbaleName3+"  where FieldsID = #{fid} and TransID=#{transid}")
    MessTraslateDetailEntity selectByFieldsID(Integer fid,Integer transid);
    @Select("select count(ID) from  "+TbaleName+"  where NestID = #{id}")
    Integer countByNestID(Integer id);

    @Select("select ID,Name,Type,IP,Port,Protocol,Describes,CreateTime,AuthorID from "+TbaleName+" where AuthorID=#{uid}")
    List<FieldsDetailEntity> getList(Integer uid);


    @Select("select t0.ID,t0.Name,t0.OutID,t0.OutType,t0.PID,t0.NestID from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=0 order by OrderID asc")
    List<MessTraslateDetailEntity> searchByName(String name, Integer uid, Integer pid, String ttype);
    @Select("select t0.ID,t0.Name,t0.OutID,t0.OutType,t0.PID,t0.NestID from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and t0.Name like concat('%',#{name},'%') and NestID=#{nestid} order by OrderID asc")
    List<MessTraslateDetailEntity> getListByNestID(String name,Integer uid, Integer pid,String ttype,Integer nestid);
    @Select("select t0.ID,t0.Name,t0.TType,t0.OrderID,t0.OutID,t0.OutType,t0.PID,t0.NestID,t0.Flag from "+TbaleName+" t0 where t0.PID=#{pid} and t0.TType=#{ttype}  and NestID=#{nestid} order by OrderID asc")
    List<MessDetailEntity> searchByPID(Integer pid,Integer nestid,String ttype);

}
