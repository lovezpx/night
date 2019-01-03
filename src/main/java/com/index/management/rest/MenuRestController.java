package com.index.management.rest;

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
@RequestMapping("/manager/menuManage")
public class MenuRestController {
    @Autowired
    private MenuManageService menuManageService;

    @RequestMapping("deleteByPrimaryKey")
    public int deleteByPrimaryKey(String id) {
        return menuManageService.deleteByPrimaryKey(id);
    }

    @RequestMapping("insertSelective")
    public int insertSelective(Menu record) {
        return menuManageService.insertSelective(record);
    }

    @RequestMapping("selectByParentId")
    public List<Menu> selectByParentId(String parentId) {
        return menuManageService.selectByParentId(parentId);
    }

    @RequestMapping("selectAll")
    public List<Menu> selectAll() {
        return menuManageService.selectAll();
    }

    @RequestMapping("updateByPrimaryKeySelective")
    public int updateByPrimaryKeySelective(Menu record) {
        return menuManageService.updateByPrimaryKeySelective(record);
    }
}
