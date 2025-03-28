package com.examportal.server.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "exams")
public class Exam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "exam_session_id", nullable = false)
    private Long examSessionId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "subject", nullable = false, length = 100)
    private String subject;

    @Column(name = "file_url", length = 500)
    private String fileUrl;

    @Column(name = "create_date", nullable = false, updatable = false)
    private Timestamp createDate;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;

    // Constructor mặc định (bắt buộc cho JPA)
    public Exam() {
    }

    public Exam(Long id, Long examSessionId, String name, String description, String type, int duration, String subject, String fileUrl, Timestamp createDate, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.examSessionId = examSessionId;
        this.name = name;
        this.description = description;
        this.type = type;
        this.duration = duration;
        this.subject = subject;
        this.fileUrl = fileUrl;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
