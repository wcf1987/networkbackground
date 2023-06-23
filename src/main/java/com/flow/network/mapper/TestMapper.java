package com.flow.network.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    public List<Map<String,Object>> selectUserList();

    // public int insertUser(User user);

    public int insertUser(Map<String,Object> map);

    public int updateUser(Map<String,Object> map);

    //根据用户ID查找这个对象
    public Map<String,Object> selectUserById(int userId);

    public int deleteUser(List<String> list);
}
