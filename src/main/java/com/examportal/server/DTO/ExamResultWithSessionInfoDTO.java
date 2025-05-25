package com.examportal.server.DTO;

import java.sql.Timestamp;

public class ExamResultWithSessionInfoDTO {
    private Long examResultId;
    private Float totalScore;
    private Timestamp startTime;
    private Timestamp submitTime;
    private Timestamp endTime;
    private String sessionName;
    private String teacherFullName;

    public ExamResultWithSessionInfoDTO(
        Long examResultId,
        Float totalScore,
        Timestamp startTime,
        Timestamp submitTime,
        Timestamp endTime,
        String sessionName,
        String teacherFullName
    ) {
        this.examResultId = examResultId;
        this.totalScore = totalScore;
        this.startTime = startTime;
        this.submitTime = submitTime;
        this.endTime = endTime;
        this.sessionName = sessionName;
        this.teacherFullName = teacherFullName;
    }

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

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public void setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
    }
}
