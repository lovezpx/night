package com.index.management.rest;

import com.alibaba.fastjson.JSONObject;
import com.index.common.ResultMap;
import com.index.management.model.DicVideoType;
import com.index.management.service.DicVideoTypeService;
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
@RequestMapping("/manager/dicManage/dicVideoType")
public class DicVideoTypeRestController {
    @Autowired
    private DicVideoTypeService dicVideoTypeService;

    @RequestMapping("saveData")
    @ResponseBody
    public ResultMap<DicVideoType> saveData(String insertedRows, String updatedRows, String deletedRows) {
        ResultMap<DicVideoType> result = new ResultMap<DicVideoType>();
        try {
            List<DicVideoType> insertedList = (List<DicVideoType>) JSONObject.parseArray(insertedRows, DicVideoType.class);
            List<DicVideoType> updatedList = (List<DicVideoType>) JSONObject.parseArray(updatedRows, DicVideoType.class);
            List<DicVideoType> deletedList = (List<DicVideoType>) JSONObject.parseArray(deletedRows, DicVideoType.class);

            for (DicVideoType inserted : insertedList) {
                dicVideoTypeService.insertSelective(inserted);
            }

            for (DicVideoType updated : updatedList) {
                dicVideoTypeService.updateByPrimaryKeyWithBLOBs(updated);
            }

            for (DicVideoType deleted : deletedList) {
                dicVideoTypeService.deleteByPrimaryKey(deleted.getId());
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

    @RequestMapping("selectBySexKey")
    public DicVideoType selectByTypeKey(String type) {
        return dicVideoTypeService.selectByTypeKey(type);
    }

    @RequestMapping("selectAll")
    public List<DicVideoType> selectAll() {
        return dicVideoTypeService.selectAll();
    }

    @RequestMapping("selectByName")
    public List<DicVideoType> selectByName(String name) {
        return dicVideoTypeService.selectByName(name);
    }

    @RequestMapping("selectByIsUsing")
    public List<DicVideoType> selectByIsUsing(Integer isusing) {
        return dicVideoTypeService.selectByIsUsing(isusing);
    }
}
