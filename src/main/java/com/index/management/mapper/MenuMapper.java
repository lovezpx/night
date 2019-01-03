package com.index.management.mapper;

import com.index.management.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(Menu record);

    List<Menu> selectByParentId(String parentId);

    List<Menu> selectAll();

    int updateByPrimaryKeySelective(Menu record);
}