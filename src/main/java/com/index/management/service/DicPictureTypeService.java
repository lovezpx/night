package com.index.management.service;

import com.index.management.model.DicPictureType;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 16:41
 * @Description:
 */
public interface DicPictureTypeService {
    public int deleteByPrimaryKey(String id);

    public int insert(DicPictureType record);

    public int insertSelective(DicPictureType record);

    public DicPictureType selectByTypeKey(String type);

    public List<DicPictureType> selectAll();

    public List<DicPictureType> selectByName(String name);

    public List<DicPictureType> selectByIsUsing(Integer isusing);

    public int updateByPrimaryKeySelective(DicPictureType record);

    public int updateByPrimaryKeyWithBLOBs(DicPictureType record);
}
