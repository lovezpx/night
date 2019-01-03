package com.index.management.service;

import com.index.management.model.DicVideoType;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 16:41
 * @Description:
 */
public interface DicVideoTypeService {
    public int deleteByPrimaryKey(String id);

    public int insert(DicVideoType record);

    public int insertSelective(DicVideoType record);

    public DicVideoType selectByTypeKey(String type);

    public List<DicVideoType> selectAll();

    public List<DicVideoType> selectByName(String name);

    public List<DicVideoType> selectByIsUsing(Integer isusing);

    public int updateByPrimaryKeySelective(DicVideoType record);

    public int updateByPrimaryKeyWithBLOBs(DicVideoType record);
}
