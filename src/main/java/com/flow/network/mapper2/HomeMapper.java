package com.flow.network.mapper2;

import com.flow.network.domain2.GatewayEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeMapper {
    public static String TbaleName="t_gateway";


    @Select("select ID,Name,IP,Describes,CreateTime,AuthorID from "+TbaleName+" where  Name like concat('%',#{name},'%')")
    List<GatewayEntity> searchByName();
}
