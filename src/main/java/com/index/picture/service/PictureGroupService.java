package com.index.picture.service;

import com.index.picture.model.PictureGroup;
import com.index.picture.pojo.PictureGroupBean;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/12 09:35
 * @Description:
 */
public interface PictureGroupService {
    public int deleteByPrimaryKey(String id);

    public int insert(PictureGroup record);

    public int insertSelective(PictureGroup record);

    public PictureGroupBean selectByPrimaryKey(String id);

    public List<PictureGroupBean> selectByName(String name);

    public List<PictureGroupBean> selectByType(String type);

    public List<PictureGroupBean> selectByAuthor(String author);

    public PictureGroupBean selectByNumCode(String numcode);

    public int updateByPrimaryKeySelective(PictureGroup record);

    public int updateByPrimaryKey(PictureGroup record);
}
