package com.examportal.server.DTO;

import java.sql.Timestamp;

public class ExamResultWithSessionInfoDTO {
    private Long examResultId;
    private Float totalScore;
    private Timestamp startTime;
    private Timestamp submitTime;
    private Timestamp endTime;
    private String examSessionId;
    private Long TeacherId;

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

    public String getExamSessionId() {
        return examSessionId;
    }

    public void setExamSessionId(String examSessionId) {
        this.examSessionId = examSessionId;
    }

    public Long getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.TeacherId = teacherId;
    }
}
