package com.examportal.server.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamStateResponseDTO {
    private String message;
    private List<AnswerItem> answers;

    public ExamStateResponseDTO(String message, List<AnswerItem> answers) {
        this.message = message;
        this.answers = answers;
    }

    @Getter
    @Setter
    public static class AnswerItem {
        private int questionNo;
        private String answerText;

        public AnswerItem(int questionNo, String answerText) {
            this.questionNo = questionNo;
            this.answerText = answerText;
        }
    }
}
