package com.index.video.mapper;

import com.index.video.model.VideoTip;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoTipMapper {
    int deleteByPrimaryKey(String id);

    int insert(VideoTip record);

    VideoTip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(VideoTip record);

    List<VideoTip> selectAll();

    int insertSelective(VideoTip record);

    List<VideoTip> selectByVgid(String vgid);
}