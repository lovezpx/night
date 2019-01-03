package com.index.video.model;

import java.util.Date;

public class VideoTrailer {
    private String id;

    private String name;

    private String vgid;

    private String type;

    private String author;

    private String numcode;

    private String coverid;

    private Date uploadtime;

    private String uploadby;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVgid() {
        return vgid;
    }

    public void setVgid(String vgid) {
        this.vgid = vgid == null ? null : vgid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getNumcode() {
        return numcode;
    }

    public void setNumcode(String numcode) {
        this.numcode = numcode == null ? null : numcode.trim();
    }

    public String getCoverid() {
        return coverid;
    }

    public void setCoverid(String coverid) {
        this.coverid = coverid == null ? null : coverid.trim();
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
}