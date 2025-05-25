package com.examportal.server.DTO;

import java.sql.Timestamp;

public class ExamResultWithSessionInfoDTO {
    private Long examResultId;
    private Float totalScore;
    private Timestamp startTime;
    private Timestamp submitTime;
    private Timestamp endTime;
    private String sessionName;
    private Long sessionTeacherId;

    public Long getExamResultId() {
        return examResultId;
    }

    public void setExamResultId(Long examResultId) {
        this.examResultId = examResultId;
    }

    public Float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Float totalScore) {
        this.totalScore = totalScore;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Long getSessionTeacherId() {
        return sessionTeacherId;
    }

    public void setSessionTeacherId(Long sessionTeacherId) {
        this.sessionTeacherId = sessionTeacherId;
    }
}
