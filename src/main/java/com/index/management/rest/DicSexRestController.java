package com.index.management.rest;

import com.alibaba.fastjson.JSONObject;
import com.index.common.ResultMap;
import com.index.management.model.DicSex;
import com.index.management.service.DicSexService;
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
@RequestMapping("/manager/dicManage/dicSex")
public class DicSexRestController {
    @Autowired
    private DicSexService dicSexService;

    @RequestMapping("saveData")
    @ResponseBody
    public ResultMap<DicSex> saveData(String insertedRows, String updatedRows, String deletedRows) {
        ResultMap<DicSex> result = new ResultMap<DicSex>();
        try {
            List<DicSex> insertedList = (List<DicSex>) JSONObject.parseArray(insertedRows, DicSex.class);
            List<DicSex> updatedList = (List<DicSex>) JSONObject.parseArray(updatedRows, DicSex.class);
            List<DicSex> deletedList = (List<DicSex>) JSONObject.parseArray(deletedRows, DicSex.class);

            for(DicSex inserted : insertedList){
                dicSexService.insertSelective(inserted);
            }

            for(DicSex updated : updatedList){
                dicSexService.updateByPrimaryKeyWithBLOBs(updated);
            }

            for(DicSex deleted : deletedList){
                dicSexService.deleteByPrimaryKey(deleted.getId());
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
    public DicSex selectBySexKey(String sex) {
        return dicSexService.selectBySexKey(sex);
    }

    @RequestMapping("selectAll")
    public List<DicSex> selectAll() {
        return dicSexService.selectAll();
    }

    @RequestMapping("selectByName")
    public List<DicSex> selectByName(String name) {
        return dicSexService.selectByName(name);
    }

    @RequestMapping("selectByIsUsing")
    public List<DicSex> selectByIsUsing(Integer isusing) {
        return dicSexService.selectByIsUsing(isusing);
    }
}
