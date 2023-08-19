package com.zjj.Mapper;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import com.zjj.Beans.User;
import org.apache.ibatis.annotations.Param;

public interface MapperFile {
    List<User> selectAll();
    User selectbyID(int id);
    int SelectCountByUsername(@Param("username") String username);
    User SeletByUserName(@Param("username") String username);
    void add(User user);
    int updatePassword(@Param("name")String name,@Param("password")String password);
    void DeleteById(int id);

    void DeleteByIds(@Param("ids") int[] ids);
}
