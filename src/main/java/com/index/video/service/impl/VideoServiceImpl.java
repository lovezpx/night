package com.index.video.service.impl;

import com.index.video.mapper.VideoMapper;
import com.index.video.model.Video;
import com.index.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/3 14:24
 * @Description:
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    public int deleteByPrimaryKey(String id) {
        return videoMapper.deleteByPrimaryKey(id);
    }

    public int insert(Video record) {
        return videoMapper.insert(record);
    }

    public int insertSelective(Video record) {
        return videoMapper.insertSelective(record);
    }

    public List<Video> selectByVgid(String vgid) {
        return videoMapper.selectByVgid(vgid);
    }

    public int updateByPrimaryKeySelective(Video record) {
        return videoMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(Video record) {
        return videoMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(Video record) {
        return videoMapper.updateByPrimaryKey(record);
    }

    public int updateVideoStatus(Video record) {
        return videoMapper.updateVideoStatus(record);
    }
}
