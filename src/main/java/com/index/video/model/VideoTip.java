package com.index.video.model;

import java.util.Date;

public class VideoTip {
    private String id;

    private String vgid;

    private String tipname;

    private Date createtime;

    private String createby;

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

    public String getTipname() {
        return tipname;
    }

    public void setTipname(String tipname) {
        this.tipname = tipname == null ? null : tipname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby == null ? null : createby.trim();
    }
}