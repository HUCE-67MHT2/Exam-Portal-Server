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
    // Hàm cập nhập thông tin exam (không bao gồm file mới để tránh tải lên gg drive)
    @Override
    public Exam updateExamByFile(Exam newExamData, MultipartFile file) throws Exception {
        try {
            // Lấy exam cũ từ DB
            Exam existingExam = examRepository.getExamById(newExamData.getId());
            if (existingExam == null) {
                throw new Exception("Exam not found with id: " + newExamData.getId());
            }

            // Cập nhật thông tin từ dữ liệu mới
            existingExam.setName(newExamData.getName());
            existingExam.setDescription(newExamData.getDescription());
            existingExam.setTotalQuestions(newExamData.getTotalQuestions());
            existingExam.setDuration(newExamData.getDuration());
            existingExam.setSubject(newExamData.getSubject());
            existingExam.setStartDate(newExamData.getStartDate());
            existingExam.setEndDate(newExamData.getEndDate());
            existingExam.setExamSessionId(newExamData.getExamSessionId());

            // Nếu có file thì upload lên Google Drive
            if (file != null && !file.isEmpty()) {
                ResponseEntity<String> response = googleDriveController.uploadFile(file);
                if (response.getStatusCode().is2xxSuccessful()) {
                    String fileUrl = response.getBody();
                    existingExam.setFileUrl(fileUrl);
                } else {
                    throw new Exception("Upload file lên Google Drive thất bại");
                }
            }

            // Lưu lại vào DB
            examRepository.save(existingExam);
            return existingExam;

        } catch (Exception e) {
            // Ghi log lỗi ra console
            System.err.println("❌ Lỗi khi cập nhật Exam:");
            e.printStackTrace();

            // Ném lại lỗi để controller xử lý response cho client
            throw new Exception("Có lỗi xảy ra khi cập nhật Exam: " + e.getMessage());
        }
    }

    @Override
    public Exam createExamManually(Exam exam) throws Exception {
        // Gán type "manual" vào đối tượng exam
        exam.setType("manual");
        examRepository.save(exam);
        return exam;
    }
}
