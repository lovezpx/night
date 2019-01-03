package com.index.video.rest;

import com.index.video.model.Video;
import com.index.video.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/3 14:27
 * @Description:
 */
@RestController
@RequestMapping("/video/videoServer")
public class VideoRestController {
    @Autowired
    private VideoService videoService;

    @RequestMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return videoService.deleteByPrimaryKey(id);
    }

    @RequestMapping("insert")
    public int insert(Video record) {
        return videoService.insert(record);
    }

    @RequestMapping("insertSelective")
    public int insertSelective(Video record) {
        return videoService.insertSelective(record);
    }

    @RequestMapping("selectByVgid")
    public List<Video> selectByVgid(String vgid) {
        return videoService.selectByVgid(vgid);
    }

    @RequestMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Video record) {
        return videoService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("updateByPrimaryKeyWithBLOBs")
    public int updateByPrimaryKeyWithBLOBs(Video record) {
        return videoService.updateByPrimaryKeyWithBLOBs(record);
    }

    @RequestMapping("updateByPrimaryKey")
    public int updateByPrimaryKey(Video record) {
        return videoService.updateByPrimaryKey(record);
    }
}
