package com.index.management.service.impl;

import com.index.management.mapper.DicVideoTypeMapper;
import com.index.management.model.DicVideoType;
import com.index.management.service.DicVideoTypeService;
import com.index.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 16:41
 * @Description:
 */
@Service
public class DicVideoTypeServiceImpl implements DicVideoTypeService {
    @Autowired
    private DicVideoTypeMapper dicVideoTypeMapper;

    public int deleteByPrimaryKey(String id){
        return dicVideoTypeMapper.deleteByPrimaryKey(id);
    }

    public int insert(DicVideoType record){
        record.setId(UUID.captchaNumber(32));
        return dicVideoTypeMapper.insert(record);
    }

    public int insertSelective(DicVideoType record){
        record.setId(UUID.captchaNumber(32));
        return dicVideoTypeMapper.insertSelective(record);
    }

    public DicVideoType selectByTypeKey(String type){
        return dicVideoTypeMapper.selectByTypeKey(type);
    }

    public List<DicVideoType> selectAll(){
        return dicVideoTypeMapper.selectAll();
    }

    public List<DicVideoType> selectByName(String name){
        return dicVideoTypeMapper.selectByName(name);
    }

    public List<DicVideoType> selectByIsUsing(Integer isusing){
        return dicVideoTypeMapper.selectByIsUsing(isusing);
    }

    public int updateByPrimaryKeySelective(DicVideoType record){
        return dicVideoTypeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(DicVideoType record){
        return dicVideoTypeMapper.updateByPrimaryKeyWithBLOBs(record);
    }
}
