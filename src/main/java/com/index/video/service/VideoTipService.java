package com.index.video.service;

import com.index.video.model.VideoTip;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/4 12:06
 * @Description:
 */
public interface VideoTipService {
    public int deleteByPrimaryKey(String id);

    public int insert(VideoTip record);

    public int updateByPrimaryKeySelective(VideoTip record);

    public int insertSelective(VideoTip record);

    public List<VideoTip> selectAll();

    public List<VideoTip> selectByVgid(String vgid);

    public VideoTip selectByPrimaryKey(String id);
}
