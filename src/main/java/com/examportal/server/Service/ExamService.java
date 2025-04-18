package com.examportal.server.Service;

import com.examportal.server.DTO.UploadExamStateResponseDTO;
import com.examportal.server.Entity.Exam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExamService {
    List<Exam> getList();

    Exam getExamById(Long id);

    void save(Exam exam);

    void delete(Long id);

    List<Exam> getExamBySessionId(Long id);

    // Phương thức mới: nhận Exam và MultipartFile để upload file và lưu URL
    Exam createExamByFile(Exam exam, MultipartFile file) throws Exception;

    Exam createExamManually(Exam exam) throws Exception;

    Exam updateExamByFile(Exam exam, MultipartFile file) throws Exception;

    void newStudentTesting(Long examId, Long userId) throws Exception;

    UploadExamStateResponseDTO getStateUploadExam(Long examId, Long userId) throws Exception;

    void submitUploadExam(Long examId, Long userId);

}
