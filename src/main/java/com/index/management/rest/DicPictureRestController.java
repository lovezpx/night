package com.index.management.rest;

import com.alibaba.fastjson.JSONObject;
import com.index.common.ResultMap;
import com.index.management.model.DicPictureType;
import com.index.management.service.DicPictureTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Index
 * @Date: 2018/11/26 16:51
 * @Description:
 */
@RestController
@RequestMapping("/manager/dicManage/dicPictureType")
public class DicPictureRestController {
    @Autowired
    private DicPictureTypeService dicPictureTypeService;

    @RequestMapping("saveData")
    @ResponseBody
    public ResultMap<DicPictureType> saveData(String insertedRows, String updatedRows, String deletedRows) {
        ResultMap<DicPictureType> result = new ResultMap<DicPictureType>();
        try {
            List<DicPictureType> insertedList = (List<DicPictureType>) JSONObject.parseArray(insertedRows, DicPictureType.class);
            List<DicPictureType> updatedList = (List<DicPictureType>) JSONObject.parseArray(updatedRows, DicPictureType.class);
            List<DicPictureType> deletedList = (List<DicPictureType>) JSONObject.parseArray(deletedRows, DicPictureType.class);

            for(DicPictureType inserted : insertedList){
                dicPictureTypeService.insertSelective(inserted);
            }

            for(DicPictureType updated : updatedList){
                dicPictureTypeService.updateByPrimaryKeyWithBLOBs(updated);
            }

            for(DicPictureType deleted : deletedList){
                dicPictureTypeService.deleteByPrimaryKey(deleted.getId());
            }

            result.setSuccess(true);
            result.setMessage("保存成功！");
        } catch (Exception ex) {
            result.setSuccess(false);
            result.setMessage("保存失败！");

            ex.printStackTrace();
        }

        return result;
    }

    @RequestMapping("selectByTypeKey")
    public DicPictureType selectByTypeKey(String type) {
        return dicPictureTypeService.selectByTypeKey(type);
    }

    @RequestMapping("selectAll")
    public List<DicPictureType> selectAll() {
        return dicPictureTypeService.selectAll();
    }

    @RequestMapping("selectByName")
    public List<DicPictureType> selectByName(String name) {
        return dicPictureTypeService.selectByName(name);
    }

    @RequestMapping("selectByIsUsing")
    public List<DicPictureType> selectByIsUsing(Integer isusing) {
        return dicPictureTypeService.selectByIsUsing(isusing);
    }
}
