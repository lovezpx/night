package com.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Index
 * @Date: 2018/11/29 11:01
 * @Description:
 */
@Controller
@RequestMapping("/night")
public class NightIndexController {
    @RequestMapping("")
    public String index() {
        return "/index";
    }
}
