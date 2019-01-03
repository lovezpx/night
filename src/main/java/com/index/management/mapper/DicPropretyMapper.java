package com.index.management.mapper;

import com.index.management.model.DicProprety;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface DicPropretyMapper {
    int deleteByPrimaryKey(String id);

    int insert(DicProprety record);

    int insertSelective(DicProprety record);

    DicProprety selectByPropretyKey(String proprety);

    List<DicProprety> selectAll();

    List<DicProprety> selectByName(String name);

    List<DicProprety> selectByIsUsing(Integer isusing);

    int updateByPrimaryKeySelective(DicProprety record);

    int updateByPrimaryKeyWithBLOBs(DicProprety record);
}