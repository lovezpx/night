package com.index.picture.mapper;

import com.index.picture.model.PictureGroup;
import com.index.picture.pojo.PictureGroupBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PictureGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(PictureGroup record);

    int insertSelective(PictureGroup record);

    PictureGroupBean selectByPrimaryKey(String id);

    List<PictureGroupBean> selectByName(String name);

    List<PictureGroupBean> selectByType(String type);

    List<PictureGroupBean> selectByAuthor(String author);

    PictureGroupBean selectByNumCode(String numcode);

    int updateByPrimaryKeySelective(PictureGroup record);

    int updateByPrimaryKey(PictureGroup record);
}