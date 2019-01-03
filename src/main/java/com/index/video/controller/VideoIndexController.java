package com.index.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Index
 * @Date: 2018/11/29 15:50
 * @Description:
 */
@Controller
@RequestMapping("/video")
public class VideoIndexController {
    @RequestMapping("")
    public String index() {
        return "/video/index";
    }

    @RequestMapping("trailer")
    public String trailer() {
        return "/video/trailer/trailer";
    }

    @RequestMapping("videoWall")
    public String videoWall() {
        return "/video/videoWall/videoWall";
    }

    @RequestMapping("video")
    public String video() {
        return "/video/videoWall/video";
    }
}
