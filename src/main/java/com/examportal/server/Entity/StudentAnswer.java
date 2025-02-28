package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "student_answers")
public class StudentAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "result_id", nullable = false)
    private Long result_id;


    @Column(name = "question_id", nullable = false)
    private Long question_id;

    @Column(name = "selected_option", length = 10)
    private String selectedOption;

    @Column(name = "answer_text", columnDefinition = "TEXT")
    private String answerText;

    public StudentAnswer() {
    }

    public StudentAnswer(Long id, Long result_id, Long question_id, String selectedOption, String answerText) {
        this.id = id;
        this.result_id = result_id;
        this.question_id = question_id;
        this.selectedOption = selectedOption;
        this.answerText = answerText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResult_id() {
        return result_id;
    }

    public void setResult_id(Long result_id) {
        this.result_id = result_id;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
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
