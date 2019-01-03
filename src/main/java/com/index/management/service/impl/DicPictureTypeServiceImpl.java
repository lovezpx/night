package com.index.management.service.impl;

import com.index.management.mapper.DicPictureTypeMapper;
import com.index.management.model.DicPictureType;
import com.index.management.service.DicPictureTypeService;
import com.index.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/29 10:01
 * @Description:
 */
@Service
public class DicPictureTypeServiceImpl implements DicPictureTypeService {
    @Autowired
    private DicPictureTypeMapper dicPictureTypeMapper;

    public int deleteByPrimaryKey(String id){
        return dicPictureTypeMapper.deleteByPrimaryKey(id);
    }

    public int insert(DicPictureType record){
        record.setId(UUID.captchaNumber(32));
        return dicPictureTypeMapper.insert(record);
    }

    public int insertSelective(DicPictureType record){
        record.setId(UUID.captchaNumber(32));
        return dicPictureTypeMapper.insertSelective(record);
    }

    public DicPictureType selectByTypeKey(String type){
        return dicPictureTypeMapper.selectByTypeKey(type);
    }

    public List<DicPictureType> selectAll(){
        return dicPictureTypeMapper.selectAll();
    }

    public List<DicPictureType> selectByName(String name){
        return dicPictureTypeMapper.selectByName(name);
    }

    public List<DicPictureType> selectByIsUsing(Integer isusing){
        return dicPictureTypeMapper.selectByIsUsing(isusing);
    }

    public int updateByPrimaryKeySelective(DicPictureType record){
        return dicPictureTypeMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(DicPictureType record){
        return dicPictureTypeMapper.updateByPrimaryKeyWithBLOBs(record);
    }
}
