package com.examportal.server.Entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "answer_for_exam_file")
public class AnswerForExamFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_id")
    private String questionId;  // ID của câu hỏi trong file Word
    @Column(name = "selected_option")
    private String selectedOption;  // Đáp án đã chọn

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;  // Liên kết với bảng Exam

    public AnswerForExamFile() {
    }

    public AnswerForExamFile(String questionId, String selectedOption, Exam exam) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.exam = exam;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
