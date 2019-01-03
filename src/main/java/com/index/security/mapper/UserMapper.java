package com.index.security.mapper;

import com.index.security.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    User selectByPrimaryKey(String id);

    User findByUserName(String username);

    List<User> selectAll();

    int insert(User record);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updatePassword(User record);
}