package com.index.management.mapper;

import com.index.management.model.DicPictureType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper

public interface DicPictureTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicPictureType record);

    int insertSelective(DicPictureType record);

    DicPictureType selectByTypeKey(String type);

    List<DicPictureType> selectAll();

    List<DicPictureType> selectByName(String name);

    List<DicPictureType> selectByIsUsing(Integer isusing);

    int updateByPrimaryKeySelective(DicPictureType record);

    int updateByPrimaryKeyWithBLOBs(DicPictureType record);
}