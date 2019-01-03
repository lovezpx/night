package com.index.picture.service.impl;

import com.index.picture.mapper.PictureMapper;
import com.index.picture.model.Picture;
import com.index.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Index
 * @Date: 2019/1/3 14:30
 * @Description:
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    public int deleteByPrimaryKey(String id) {
        return pictureMapper.deleteByPrimaryKey(id);
    }

    public int insert(Picture record) {
        return pictureMapper.insert(record);
    }

    public int insertSelective(Picture record) {
        return pictureMapper.insert(record);
    }

    public Picture selectByPrimaryKey(String id) {
        return pictureMapper.selectByPrimaryKey(id);
    }

    public String findPicSrcByPrimaryKey(String id) {
        return pictureMapper.findPicSrcByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Picture record) {
        return pictureMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(Picture record) {
        return pictureMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(Picture record) {
        return pictureMapper.updateByPrimaryKey(record);
    }
}
