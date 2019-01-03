package com.index.video.rest;

import com.index.video.model.VideoGroup;
import com.index.video.pojo.VideoWallBean;
import com.index.video.service.VideoGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/30 15:04
 * @Description:
 */
@RestController
@RequestMapping("/video/videoGroupServer")
public class VideoGroupRestController {
    @Autowired
    private VideoGroupService videoGroupService;

    @RequestMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return videoGroupService.deleteByPrimaryKey(id);
    }

    @RequestMapping("insert")
    public int insert(VideoGroup record) {
        return videoGroupService.insert(record);
    }

    @RequestMapping("insertSelective")
    public int insertSelective(VideoGroup record) {
        return videoGroupService.insertSelective(record);
    }

    @RequestMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(VideoGroup record) {
        return videoGroupService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("selectAll")
    public List<VideoWallBean> selectAll() {
        return videoGroupService.selectAll();
    }

    @RequestMapping("selectByType")
    public List<VideoWallBean> selectByType(String type) {
        return videoGroupService.selectByType(type);
    }

    @RequestMapping("selectByKey")
    public List<VideoWallBean> selectByKey(String key) {
        return videoGroupService.selectByKey(key);
    }

    @RequestMapping("selectByVideoTip")
    public List<VideoWallBean> selectByVideoTip(String tipname) {
        return videoGroupService.selectByVideoTip(tipname);
    }

    @RequestMapping("selectByPrimaryKey")
    public VideoWallBean selectByPrimaryKey(String id) {
        return videoGroupService.selectByPrimaryKey(id);
    }
}
