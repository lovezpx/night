package com.index.management.model;

public class DicProprety {
    private String id;

    private String proprety;

    private String name;

    private Integer isusing;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProprety() {
        return proprety;
    }

    public void setProprety(String proprety) {
        this.proprety = proprety == null ? null : proprety.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsusing() {
        return isusing;
    }

    public void setIsusing(Integer isusing) {
        this.isusing = isusing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}