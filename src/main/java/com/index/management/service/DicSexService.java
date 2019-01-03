package com.index.management.service;

import com.index.management.model.DicSex;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 16:41
 * @Description:
 */
public interface DicSexService {
    public int deleteByPrimaryKey(String id);

    public int insert(DicSex record);

    public int insertSelective(DicSex record);

    public DicSex selectBySexKey(String sex);

    public List<DicSex> selectAll();

    public List<DicSex> selectByName(String name);

    public List<DicSex> selectByIsUsing(Integer isusing);

    public int updateByPrimaryKeySelective(DicSex record);

    public int updateByPrimaryKeyWithBLOBs(DicSex record);
}
