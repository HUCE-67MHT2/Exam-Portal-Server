package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "exam_session_enrollments")

public class ExamSessionEnrollment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "exam_session_id", nullable = false)
    private Long examSessionId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "enroll_date", nullable = false)
    private Timestamp enrollDate;

    public ExamSessionEnrollment() {
    }

    public ExamSessionEnrollment(Long id, Long examSessionId, Long studentId, Timestamp enrollDate) {
        this.id = id;
        this.examSessionId = examSessionId;
        this.studentId = studentId;
        this.enrollDate = enrollDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamSessionId() {
        return examSessionId;
    }

    public void setExamSessionId(Long examSessionId) {
        this.examSessionId = examSessionId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Timestamp getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Timestamp enrollDate) {
        this.enrollDate = enrollDate;
    }
}
