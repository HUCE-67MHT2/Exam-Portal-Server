package com.examportal.server.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
@Getter
@Setter
@Entity
@Table(name = "exam_results")
public class ExamResult implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_score", nullable = false)
    private float totalScore;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "submit_time", nullable = false)
    private Timestamp submitTime;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private User user;  // Liên kết tới bảng users

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private Exam exam;  // Liên kết tới bảng exams

    // Constructor mặc định (bắt buộc cho JPA)
    public ExamResult() {
    }

    public ExamResult(Long id, float totalScore, Timestamp startTime, Timestamp submitTime, User user, Exam exam) {
        this.id = id;
        this.user = user;
        this.exam = exam;
        this.totalScore = totalScore;
        this.startTime = startTime;
        this.submitTime = submitTime;
    }
}
