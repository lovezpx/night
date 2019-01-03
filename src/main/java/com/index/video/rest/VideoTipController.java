package com.index.video.rest;

import com.index.video.model.VideoTip;
import com.index.video.service.VideoTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/12/4 12:10
 * @Description:
 */
@RestController
@RequestMapping("/video/videoTipServer")
public class VideoTipController {
    @Autowired
    private VideoTipService videoTipService;

    @RequestMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return videoTipService.deleteByPrimaryKey(id);
    }

    @RequestMapping("insert")
    public int insert(VideoTip record) {
        return videoTipService.insert(record);
    }

    @RequestMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(VideoTip record) {
        return videoTipService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("insertSelective")
    public int insertSelective(VideoTip record) {
        return videoTipService.insertSelective(record);
    }

    @RequestMapping("selectAll")
    public List<VideoTip> selectAll() {
        return videoTipService.selectAll();
    }

    @RequestMapping("selectByVgid")
    public List<VideoTip> selectByVgid(String vgid) {
        return videoTipService.selectByVgid(vgid);
    }

    @RequestMapping("selectByPrimaryKey")
    public VideoTip selectByPrimaryKey(String id) {
        return videoTipService.selectByPrimaryKey(id);
    }
}
