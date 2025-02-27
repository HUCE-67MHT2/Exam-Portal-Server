package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "exams")

public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "subject_id", nullable = false)
    private Long subject_id;

    @Column(name = "teacher_id", nullable = false)
    private Long teacher_id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "exam_type", length = 50, nullable = false)
    private String examType;

    @Column(name = "file_url", length = 255)
    private String fileUrl;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp created_at;

    @Column(nullable = false)
    private String password;

    public Exam() {
    }

    public Exam(Long id, String title, Long subject_id, Long teacher_id, LocalDateTime startTime, Integer duration, String examType, String fileUrl, Timestamp created_at, String password) {
        this.id = id;
        this.title = title;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.startTime = startTime;
        this.duration = duration;
        this.examType = examType;
        this.fileUrl = fileUrl;
        this.created_at = created_at;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
