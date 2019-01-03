package com.index.management.service;

import com.index.management.model.Menu;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 14:54
 * @Description:
 */
public interface MenuManageService {
    public int deleteByPrimaryKey(String id);

    public int insertSelective(Menu record);

    public List<Menu> selectByParentId(String parentId);

    public List<Menu> selectAll();

    public int updateByPrimaryKeySelective(Menu record);
}
