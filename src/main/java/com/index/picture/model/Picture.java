package com.index.picture.model;

import java.util.Date;

public class Picture {
    private String id;

    private String pgid;

    private Date uploadtime;

    private String uploadby;

    private Date updatetime;

    private String updateby;

    private String src;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPgid() {
        return pgid;
    }

    public void setPgid(String pgid) {
        this.pgid = pgid == null ? null : pgid.trim();
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src == null ? null : src.trim();
    }
}