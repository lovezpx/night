package com.index.management.service.impl;

import com.index.management.mapper.DicSexMapper;
import com.index.management.model.DicSex;
import com.index.management.service.DicSexService;
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
public class DicSexServiceImpl implements DicSexService {
    @Autowired
    private DicSexMapper dicSexMapper;

    public int deleteByPrimaryKey(String id){
        return dicSexMapper.deleteByPrimaryKey(id);
    }

    public int insert(DicSex record){
        record.setId(UUID.captchaNumber(32));
        return dicSexMapper.insert(record);
    }

    public int insertSelective(DicSex record){
        record.setId(UUID.captchaNumber(32));
        return dicSexMapper.insertSelective(record);
    }

    public DicSex selectBySexKey(String sex){
        return dicSexMapper.selectBySexKey(sex);
    }

    public List<DicSex> selectAll(){
        return dicSexMapper.selectAll();
    }

    public List<DicSex> selectByName(String name){
        return dicSexMapper.selectByName(name);
    }

    public List<DicSex> selectByIsUsing(Integer isusing){
        return dicSexMapper.selectByIsUsing(isusing);
    }

    public int updateByPrimaryKeySelective(DicSex record){
        return dicSexMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(DicSex record){
        return dicSexMapper.updateByPrimaryKeyWithBLOBs(record);
    }

}
