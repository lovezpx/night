package com.index.security.model;

import java.util.Date;

public class User {
    private String id;

    private String username;

    private String nickname;

    private String sex;

    private String proprety;

    private String role;

    private String photoid;

    private String telephone;

    private String email;

    private Date registrationtime;

    private Date updatetime;

    private String updateby;

    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getProprety() {
        return proprety;
    }

    public void setProprety(String proprety) {
        this.proprety = proprety == null ? null : proprety.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getPhotoid() {
        return photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid == null ? null : photoid.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getRegistrationtime() {
        return registrationtime;
    }

    public void setRegistrationtime(Date registrationtime) {
        this.registrationtime = registrationtime;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", proprety='" + proprety + '\'' +
                ", role='" + role + '\'' +
                ", photoid='" + photoid + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", registrationtime='" + registrationtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", updateby='" + updateby + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}