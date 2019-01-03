package com.index.video.service;

import com.index.video.model.Video;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/3 14:24
 * @Description:
 */
public interface VideoService {
    public int deleteByPrimaryKey(String id);

    public int insert(Video record);

    public int insertSelective(Video record);

    public List<Video> selectByVgid(String vgid);

    public int updateByPrimaryKeySelective(Video record);

    public int updateByPrimaryKeyWithBLOBs(Video record);

    public int updateByPrimaryKey(Video record);

    public int updateVideoStatus(Video record);
}
