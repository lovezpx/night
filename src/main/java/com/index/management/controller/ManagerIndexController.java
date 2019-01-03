package com.index.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Index
 * @Date: 2018/11/21 16:59
 * @Description:
 */
@Controller
@RequestMapping("/manager")
public class ManagerIndexController {
    @RequestMapping("")
    public String index() {
        return "management/index";
    }

    @RequestMapping("/menuManage")
    public String menuManage() {
        return "management/menuManage/menuManage";
    }

    @RequestMapping("/dicManage")
    public String dicManage() {
        return "management/dicManage/index";
    }

    @RequestMapping("/dicSex")
    public String dicSex() {
        return "management/dicManage/dic_sex";
    }

    @RequestMapping("/dicVideoType")
    public String dicVideoType() {
        return "management/dicManage/dic_videotype";
    }

    @RequestMapping("/dicPictureType")
    public String dicPictureType() {
        return "management/dicManage/dic_picturetype";
    }

    @RequestMapping("/videoSystemManage")
    public String videoSystemManage() {
        return "management/video/index";
    }

    @RequestMapping("/videoUpload")
    public String videoUpload() {
        return "management/video/videoUpload/videoUpload";
    }

    @RequestMapping("/videoTrailer")
    public String videoTrailer() {
        return "management/video/videoTrailer/videoTrailer";
    }
}
