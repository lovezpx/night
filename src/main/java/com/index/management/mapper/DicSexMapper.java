package com.index.management.mapper;

import com.index.management.model.DicSex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DicSexMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicSex record);

    int insertSelective(DicSex record);

    DicSex selectBySexKey(String sex);

    List<DicSex> selectAll();

    List<DicSex> selectByName(String name);

    List<DicSex> selectByIsUsing(Integer isusing);

    int updateByPrimaryKeySelective(DicSex record);

    int updateByPrimaryKeyWithBLOBs(DicSex record);
}