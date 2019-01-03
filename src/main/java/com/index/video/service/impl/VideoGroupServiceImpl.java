package com.index.video.service.impl;

import com.index.video.mapper.VideoGroupMapper;
import com.index.video.model.VideoGroup;
import com.index.video.pojo.VideoWallBean;
import com.index.video.service.VideoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/30 15:01
 * @Description:
 */
@Service
public class VideoGroupServiceImpl implements VideoGroupService {
    @Autowired
    private VideoGroupMapper videoGroupMapper;

    public int deleteByPrimaryKey(String id) {
        return videoGroupMapper.deleteByPrimaryKey(id);
    }

    public int insert(VideoGroup record) {
        return videoGroupMapper.insert(record);
    }

    public int insertSelective(VideoGroup record) {
        return videoGroupMapper.insertSelective(record);
    }

    public int updateByPrimaryKeySelective(VideoGroup record) {
        return videoGroupMapper.updateByPrimaryKeySelective(record);
    }

    public List<VideoWallBean> selectAll() {
        return videoGroupMapper.selectAll();
    }

    public List<VideoWallBean> selectByType(String type) {
        return videoGroupMapper.selectByType(type);
    }

    public List<VideoWallBean> selectByKey(String key) {
        return videoGroupMapper.selectByKey(key);
    }

    public List<VideoWallBean> selectByVideoTip(String tipname) {
        return videoGroupMapper.selectByVideoTip(tipname);
    }

    public VideoWallBean selectByPrimaryKey(String id) {
        return videoGroupMapper.selectByPrimaryKey(id);
    }
}
