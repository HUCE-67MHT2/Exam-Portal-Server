package com.examportal.server.DTO;

import com.examportal.server.Entity.StudentAnswer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamStateResponseDTO {
    private String message;
    private List<StudentAnswer> answers;

    public ExamStateResponseDTO(String message, List<StudentAnswer> answers) {
        this.message = message;
        this.answers = answers;
    }
}
