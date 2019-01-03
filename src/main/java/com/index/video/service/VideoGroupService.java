package com.index.video.service;

import com.index.video.model.VideoGroup;
import com.index.video.pojo.VideoWallBean;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/30 14:58
 * @Description:
 */
public interface VideoGroupService {
    public int deleteByPrimaryKey(String id);

    public int insert(VideoGroup record);

    public int insertSelective(VideoGroup record);

    public int updateByPrimaryKeySelective(VideoGroup record);

    public List<VideoWallBean> selectAll();

    public List<VideoWallBean> selectByType(String type);

    public List<VideoWallBean> selectByKey(String key);

    public List<VideoWallBean> selectByVideoTip(String tipname);

    public VideoWallBean selectByPrimaryKey(String id);
}
