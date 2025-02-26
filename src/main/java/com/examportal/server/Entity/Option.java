package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "option_question")
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "option_text")
    private String option_text;

    @Column(name = "is_correct")
    private Boolean is_correct;

    public Option(Long id, Long questionId, String option_text, Boolean is_correct) {
        this.id = id;
        this.questionId = questionId;
        this.option_text = option_text;
        this.is_correct = is_correct;
    }

    public Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getOption_text() {
        return option_text;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }
}
