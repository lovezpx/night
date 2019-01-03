package com.index.picture.service.impl;

import com.index.picture.mapper.PictureGroupMapper;
import com.index.picture.model.PictureGroup;
import com.index.picture.pojo.PictureGroupBean;
import com.index.picture.service.PictureGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/12 09:36
 * @Description:
 */
@Service
public class PictureGroupServiceImpl implements PictureGroupService {
    @Autowired
    private PictureGroupMapper pictureGroupMapper;

    public int deleteByPrimaryKey(String id) {
        return pictureGroupMapper.deleteByPrimaryKey(id);
    }

    public int insert(PictureGroup record) {
        return pictureGroupMapper.insert(record);
    }

    public int insertSelective(PictureGroup record) {
        return pictureGroupMapper.insertSelective(record);
    }

    public PictureGroupBean selectByPrimaryKey(String id) {
        return pictureGroupMapper.selectByPrimaryKey(id);
    }

    public List<PictureGroupBean> selectByName(String name) {
        return pictureGroupMapper.selectByName(name);
    }

    public List<PictureGroupBean> selectByType(String type) {
        return pictureGroupMapper.selectByType(type);
    }

    public List<PictureGroupBean> selectByAuthor(String author) {
        return pictureGroupMapper.selectByAuthor(author);
    }

    public PictureGroupBean selectByNumCode(String numcode) {
        return pictureGroupMapper.selectByNumCode(numcode);
    }

    public int updateByPrimaryKeySelective(PictureGroup record) {
        return pictureGroupMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(PictureGroup record) {
        return pictureGroupMapper.updateByPrimaryKey(record);
    }
}
