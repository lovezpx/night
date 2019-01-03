package com.index.video.mapper;

import com.index.video.model.VideoGroup;
import com.index.video.pojo.VideoWallBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(VideoGroup record);

    int insertSelective(VideoGroup record);

    int updateByPrimaryKeySelective(VideoGroup record);

    List<VideoWallBean> selectAll();

    List<VideoWallBean> selectByType(String type);

    List<VideoWallBean> selectByKey(String key);

    List<VideoWallBean> selectByVideoTip(String tipname);

    VideoWallBean selectByPrimaryKey(String id);
}