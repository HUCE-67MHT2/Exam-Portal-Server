package com.examportal.server.Entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student_answers")
public class Student_Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "result_id", nullable = false)
    private Exam_Result examResult;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "selected_option", length = 10)
    private String selectedOption;

    @Column(name = "answer_text", columnDefinition = "TEXT")
    private String answerText;

    public Student_Answer() {}

    public Student_Answer(Long id, Exam_Result examResult, Question question, String selectedOption, String answerText) {
        this.id = id;
        this.examResult = examResult;
        this.question = question;
        this.selectedOption = selectedOption;
        this.answerText = answerText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Exam_Result getExamResult() {
        return examResult;
    }

    public void setExamResult(Exam_Result examResult) {
        this.examResult = examResult;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
