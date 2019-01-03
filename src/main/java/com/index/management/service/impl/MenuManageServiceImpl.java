package com.index.management.service.impl;

import com.index.management.mapper.MenuMapper;
import com.index.management.model.Menu;
import com.index.management.service.MenuManageService;
import com.index.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 14:57
 * @Description:
 */
@Service
public class MenuManageServiceImpl implements MenuManageService {
    @Autowired
    private MenuMapper menuMapper;

    public int deleteByPrimaryKey(String id){
        return menuMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(Menu record){
        record.setId(UUID.captchaNumber(32));
        return menuMapper.insertSelective(record);
    }

    public List<Menu> selectByParentId(String parentId){
        return menuMapper.selectByParentId(parentId);
    }

    public List<Menu> selectAll(){
        return menuMapper.selectAll();
    }

    public int updateByPrimaryKeySelective(Menu record){
        return menuMapper.updateByPrimaryKeySelective(record);
    }
}
