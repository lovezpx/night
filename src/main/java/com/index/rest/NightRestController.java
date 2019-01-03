package com.index.rest;

import com.index.management.model.Menu;
import com.index.management.service.MenuManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 15:03
 * @Description:
 */
@RestController
@RequestMapping("/night")
public class NightRestController {
    @Autowired
    private MenuManageService menuManageService;

    @RequestMapping("/menu/selectByParentId")
    public List<Menu> selectByParentId(String parentId) {
        return menuManageService.selectByParentId(parentId);
    }
}
