package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "exam_period_enrollment")

public class ExamPeriodEnrollment  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "exam_period_id", nullable = false)
    private Long examPeriodId;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "enroll_date", nullable = false)
    private Timestamp enrollDate;

    public ExamPeriodEnrollment() {
    }

    public ExamPeriodEnrollment(Long id, Long examPeriodId, Long studentId, Timestamp enrollDate) {
        this.id = id;
        this.examPeriodId = examPeriodId;
        this.studentId = studentId;
        this.enrollDate = enrollDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExamPeriodId() {
        return examPeriodId;
    }

    public void setExamPeriodId(Long examPeriodId) {
        this.examPeriodId = examPeriodId;
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
