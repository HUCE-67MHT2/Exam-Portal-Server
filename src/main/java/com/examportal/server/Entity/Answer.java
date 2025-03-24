package com.examportal.server.Entity;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "question_answers")
public class Answer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @JoinColumn(name = "question_id", nullable = false)
    private Long question_id;


    @JoinColumn(name = "exam_id", nullable = false)
    private Long exam_id;

    @Column(name = "question_no", nullable = false)
    private int questionNo;

    @Column(name = "answer_text", columnDefinition = "TEXT", nullable = false)
    private String answerText;

    @Column(name = "is_correct", nullable = false)
    private boolean isCorrect;

    @Column(name = "ordering", nullable = false)
    private int ordering;

    @Column(name = "source", length = 50)
    private String source;

    public Answer() {
    }

    public Answer(Long id, Long question_id, Long exam_id, int questionNo, String answerText, boolean isCorrect, int ordering, String source) {
        this.id = id;
        this.question_id = question_id;
        this.exam_id = exam_id;
        this.questionNo = questionNo;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.ordering = ordering;
        this.source = source;
    }
// Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public Long getExam_id() {
        return exam_id;
    }

    public void setExam_id(Long exam_id) {
        this.exam_id = exam_id;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

