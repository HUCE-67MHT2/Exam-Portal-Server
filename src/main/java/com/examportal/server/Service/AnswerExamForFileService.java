package com.examportal.server.Service;

import com.examportal.server.Entity.AnswerForExamFile;

import java.util.List;

public interface AnswerExamForFileService {
    List<AnswerForExamFile> getLists();

    AnswerForExamFile getAnswerForExamFileById(Long id);

    void addorUpdateAnswerForExamFile(AnswerForExamFile answerForExamFile);

    void deleteAnswerForExamFile(Long id);

    List<AnswerForExamFile> getAnswerForExamFileByExamId(Long examId);
}
