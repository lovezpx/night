package com.index.picture.mapper;

import com.index.picture.model.Picture;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(String id);

    String findPicSrcByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKeyWithBLOBs(Picture record);

    int updateByPrimaryKey(Picture record);
}