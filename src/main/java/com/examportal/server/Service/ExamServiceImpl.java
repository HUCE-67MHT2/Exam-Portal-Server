package com.examportal.server.Service;

import com.examportal.server.Controllers.GoogleDriveController;
import com.examportal.server.Entity.Exam;
import com.examportal.server.Repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private GoogleDriveController googleDriveController;

    @Override
    public List<Exam> getList() {
        return examRepository.getList();
    }

    @Override
    public Exam getExamById(Long id) {
        return examRepository.getExamById(id);
    }

    @Override
    public void save(Exam exam) {
        examRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepository.delete(id);
    }

    @Override
    public List<Exam> getExamBySessionId(Long id) {
        return examRepository.getExamBySessionId(id);
    }

    // Triển khai phương thức mới sử dụng GoogleDriveController để upload file
    @Override
    public Exam createExamByFile(Exam exam, MultipartFile file) throws Exception {
        // Gọi GoogleDriveController để upload file lên Google Drive
        ResponseEntity<String> response = googleDriveController.uploadFile(file);
        if (response.getStatusCode().is2xxSuccessful()) {
            String fileUrl = response.getBody();
            // Gán fileUrl và type "upload" vào đối tượng exam
            exam.setFileUrl(fileUrl);
            exam.setType("upload");
            examRepository.save(exam);
            return exam;
        } else {
            throw new Exception("Upload file lên Google Drive thất bại");
        }
    }

}
