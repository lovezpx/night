package com.index.picture.service;

import com.index.picture.model.Picture;

/**
 * @Auther: Index
 * @Date: 2019/1/3 14:29
 * @Description:
 */
public interface PictureService {
    public int deleteByPrimaryKey(String id);

    public int insert(Picture record);

    public int insertSelective(Picture record);

    public Picture selectByPrimaryKey(String id);

    String findPicSrcByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(Picture record);

    public int updateByPrimaryKeyWithBLOBs(Picture record);

    public int updateByPrimaryKey(Picture record);
}
