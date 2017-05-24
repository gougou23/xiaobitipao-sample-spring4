package com.xiaobitipao.sample.spring4.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userid;

    private String username;

    private String email;

    private int age;

    private Date birthday;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User() {

    }

    public User(String userid, String username, String email, int age, Date birthday) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "userid");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "userid");
    }
}
