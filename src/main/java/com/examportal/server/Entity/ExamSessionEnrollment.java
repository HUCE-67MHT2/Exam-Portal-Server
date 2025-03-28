package com.examportal.server.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
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

    @Column(name = "enroll_date", nullable = false)
    private Timestamp enrollDate;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private User user;  // Liên kết tới bảng users

    public ExamSessionEnrollment() {
    }

    public ExamSessionEnrollment(Long id, Long examSessionId, User user, Timestamp enrollDate) {
        this.id = id;
        this.examSessionId = examSessionId;
        this.user = user;
        this.enrollDate = enrollDate;
    }
}
