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
        return List.of();
    }

    @Override
    public AnswerForExamFile getAnswerForExamFileById(Long id) {
        return null;
    }

    @Override
    public void addorUpdateAnswerForExamFile(AnswerForExamFile answerForExamFile) {

    }

    @Override
    public void deleteAnswerForExamFile(Long id) {

    }

    @Override
    public List<AnswerForExamFile> getAnswerForExamFileByExamId(Long examId) {
        return List.of();
    }
}
