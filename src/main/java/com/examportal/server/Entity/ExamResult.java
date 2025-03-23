package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "exam_results")
public class ExamResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "exam_id", nullable = false)
    private Long examId;

    @Column(name = "total_score", nullable = false)
    private float totalScore;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "submit_time", nullable = false)
    private Timestamp submitTime;
    // Constructor mặc định (bắt buộc cho JPA)
    public ExamResult() {
    }

    public ExamResult(Long id, Long studentId, Long examId, float totalScore, Timestamp startTime, Timestamp submitTime) {
        this.id = id;
        this.studentId = studentId;
        this.examId = examId;
        this.totalScore = totalScore;
        this.startTime = startTime;
        this.submitTime = submitTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
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
}
