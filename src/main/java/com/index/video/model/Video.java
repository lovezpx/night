package com.index.video.model;

import java.util.Date;

public class Video {
    private String id;

    private String vgid;

    private String name;

    private Integer videostatus;

    private Integer sort;

    private Date uploadtime;

    private String uploadby;

    private Date updatetime;

    private String updateby;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVgid() {
        return vgid;
    }

    public void setVgid(String vgid) {
        this.vgid = vgid == null ? null : vgid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getVideoStatus() {
        return videostatus;
    }

    public void setVideoStatus(Integer videostatus) {
        this.videostatus = videostatus;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUploadby() {
        return uploadby;
    }

    public void setUploadby(String uploadby) {
        this.uploadby = uploadby == null ? null : uploadby.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}