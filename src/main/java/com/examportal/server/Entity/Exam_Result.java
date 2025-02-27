package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "exam_results")
public class Exam_Result implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "student_id", nullable = false)
    private Long student_id;

    @JoinColumn(name = "exam_id", nullable = false)
    private Long exam_id;

    @Column(name = "score", precision = 5, scale = 2, nullable = false)
    private BigDecimal score;

    @Column(name = "submitted_at", nullable = false)
    private Timestamp submittedAt;

    public Exam_Result() {
    }

    public Exam_Result(Long id, Long student_id, Long exam_id, BigDecimal score, Timestamp submittedAt) {
        this.id = id;
        this.student_id = student_id;
        this.exam_id = exam_id;
        this.score = score;
        this.submittedAt = submittedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }
}
