package com.index.management.mapper;

import com.index.management.model.DicVideoType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DicVideoTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicVideoType record);

    int insertSelective(DicVideoType record);

    DicVideoType selectByTypeKey(String type);

    List<DicVideoType> selectAll();

    List<DicVideoType> selectByName(String name);

    List<DicVideoType> selectByIsUsing(Integer isusing);

    int updateByPrimaryKeySelective(DicVideoType record);

    int updateByPrimaryKeyWithBLOBs(DicVideoType record);
}