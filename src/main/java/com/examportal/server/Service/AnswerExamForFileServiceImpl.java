package com.examportal.server.Service;

import com.examportal.server.Entity.AnswerForExamFile;
import com.examportal.server.Repositories.AnswerExamForFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class AnswerExamForFileServiceImpl implements AnswerExamForFileService {

    @Autowired
    private AnswerExamForFileRepository answerExamForFileRepository;

    @Override
    public List<AnswerForExamFile> getLists() {
        return answerExamForFileRepository.getLists();
    }

    @Override
    public AnswerForExamFile getAnswerForExamFileById(Long id) {
        return answerExamForFileRepository.getAnswerForExamFileById(id);
    }

    @Override
    public void addorUpdateAnswerForExamFile(AnswerForExamFile answerForExamFile) {
        answerExamForFileRepository.addorUpdateAnswerForExamFile(answerForExamFile);
    }

    @Override
    public void deleteAnswerForExamFile(Long id) {
        answerExamForFileRepository.deleteAnswerForExamFile(id);
    }

    @Override
    public List<AnswerForExamFile> getAnswerForExamFileByExamId(Long examId) {
        return answerExamForFileRepository.getAnswerForExamFileByExamId(examId);
    }
}
