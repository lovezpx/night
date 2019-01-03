package com.index.video.service.impl;

import com.index.video.mapper.VideoTipMapper;
import com.index.video.model.VideoTip;
import com.index.video.service.VideoTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/4 12:06
 * @Description:
 */
@Service
public class VideoTipServiceImpl implements VideoTipService {
    @Autowired
    private VideoTipMapper videoTipMapper;

    public int deleteByPrimaryKey(String id) {
        return videoTipMapper.deleteByPrimaryKey(id);
    }

    public int insert(VideoTip record) {
        return videoTipMapper.insert(record);
    }

    public int updateByPrimaryKeySelective(VideoTip record) {
        return videoTipMapper.updateByPrimaryKeySelective(record);
    }

    public int insertSelective(VideoTip record) {
        return videoTipMapper.insertSelective(record);
    }

    public List<VideoTip> selectAll() {
        return videoTipMapper.selectAll();
    }

    public List<VideoTip> selectByVgid(String vgid) {
        return videoTipMapper.selectByVgid(vgid);
    }

    public VideoTip selectByPrimaryKey(String id) {
        return videoTipMapper.selectByPrimaryKey(id);
    }
}
