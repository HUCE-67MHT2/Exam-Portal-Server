package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "exam_questions")
public class ExamQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "exam_id", nullable = false)
    private Long examId;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "ordering", nullable = false)
    private int ordering;

    @Column(name = "score", nullable = false)
    private float score;

    // Constructor mặc định (bắt buộc cho JPA)
    public ExamQuestion() {
    }

    public ExamQuestion(Long id, Long examId, Long questionId, int ordering, float score) {
        this.id = id;
        this.examId = examId;
        this.questionId = questionId;
        this.ordering = ordering;
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
