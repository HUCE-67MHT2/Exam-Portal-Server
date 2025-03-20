package com.examportal.server.Request;

import java.sql.Timestamp;

public class ExamRequest {
    private String examId ;
    private String examName;
    private String examType;
    private String examPassword;
    private Timestamp examCreatedDate;
    private String examStatus;
    private int examDuration;
    private String examSourceType;
    public ExamRequest() {
    }

    public ExamRequest(String examId, String examName, String examType, String examPassword, Timestamp examCreatedDate, String examStatus , String examSourceType, int examDuration) {
        this.examId = examId;
        this.examName = examName;
        this.examType = examType;
        this.examPassword = examPassword;
        this.examCreatedDate = examCreatedDate;
        this.examStatus = examStatus;
        this.examDuration = examDuration;
        this.examSourceType = examSourceType;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamPassword() {
        return examPassword;
    }

    public void setExamPassword(String examPassword) {
        this.examPassword = examPassword;
    }

    public Timestamp getExamCreatedDate() {
        return examCreatedDate;
    }

    public void setExamCreatedDate(Timestamp examCreatedDate) {
        this.examCreatedDate = examCreatedDate;
    }

    public String getExamStatus() {
        return examStatus;
    }

    public void setExamStatus(String examStatus) {
        this.examStatus = examStatus;
    }

    public int getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(int examDuration) {
        this.examDuration = examDuration;
    }

    public String getExamSourceType() {
        return examSourceType;
    }

    public void setExamSourceType(String examSourceType) {
        this.examSourceType = examSourceType;
    }

}
