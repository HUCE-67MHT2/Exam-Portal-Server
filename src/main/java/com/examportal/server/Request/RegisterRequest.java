package com.examportal.server.Request;

import java.sql.Date;

public class RegisterRequest {
    private String name;
    private String className;
    private String schoolName;
    private Date dob;
    private String consious;
    private String email;
    private String password;
    private String repassword;

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String className, String schoolName, Date dob, String consious, String email, String password, String repassword) {
        this.name = name;
        this.className = className;
        this.schoolName = schoolName;
        this.dob = dob;
        this.consious = consious;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getConsious() {
        return consious;
    }

    public void setConsious(String consious) {
        this.consious = consious;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
