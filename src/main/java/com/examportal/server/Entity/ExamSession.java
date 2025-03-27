package com.examportal.server.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "exam_sessions")
public class ExamSession implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_date", nullable = false)
    private Timestamp endDate;


    @Column(name = "create_date", nullable = false, updatable = false)
    private Timestamp createDate;

    @Column(name = "code", nullable = false, length = 255)
    private String code;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "status", length = 30)
    private String status;

    public ExamSession() {
    }

    public ExamSession(Long id, Long teacherId, String name, String description, Timestamp startDate, Timestamp endDate, Timestamp createDate, String code, String password, String status) {
        this.id = id;
        this.teacherId = teacherId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.code = code;
        this.password = password;
        this.status = status;
    }

}

