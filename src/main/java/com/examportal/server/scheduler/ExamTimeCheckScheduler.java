package com.examportal.server.scheduler;

import com.examportal.server.Entity.ExamResult;
import com.examportal.server.Repositories.ExamResultRepository;
import com.examportal.server.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class ExamTimeCheckScheduler {

    private static final long ONE_MINUTE = 1; // Để tránh xử lý lại exam đã hết hạn quá lâu

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // Để gửi WebSocket messages

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private ExamService examService;

    @Scheduled(fixedRate = 30000)
    @Transactional // Đảm bảo các thao tác DB và gửi message là nhất quán
    public void checkExamTimes() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Running scheduled check for exam times at " + now);

        // --- Xử lý gửi cảnh báo 5 phút ---
        List<ExamResult> examsToWarn = examResultRepository.findExamsForWarning(now, now.plusMinutes(5));

        for (ExamResult examResult : examsToWarn) {
            try {
                //Lấy userId thực tế từ examResult
                String userId = String.valueOf(examResult.getUserId()); // Chuyển Long thành String
                String destination = "/user/" + userId + "/queue/notifications"; // Đích đến user-specific

                // Tạo message (nên dùng JSON object thay vì String đơn giản)
                String warningMessage = "{\"type\":\"WARNING\", \"message\":\"Còn dưới 5 phút làm bài!\"}";

                messagingTemplate.convertAndSend(destination, warningMessage);
                System.out.println("Sent 5-minute warning to user " + userId + " for examResult " + examResult.getId());

                // Đánh dấu đã gửi cảnh báo
                examResult.setWarningSent(true);
                examResultRepository.save(examResult); // Lưu lại trạng thái

            } catch (Exception e) {
                System.out.println("Error sending warning for examResult " + examResult.getId() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }

        // --- Xử lý tự động nộp bài khi hết giờ ---
        LocalDateTime oneMinuteAgo = now.minusMinutes(ONE_MINUTE);
        List<ExamResult> expiredExams = examResultRepository.findExpiredExams(now, oneMinuteAgo);

        System.out.println("Đã gửi cảnh báo còn 5 phút cho " + examsToWarn.size() + " bài thi");
        System.out.println("Tìm thấy " + expiredExams.size() + " bài thi hết hạn cần xử lý");

        for (ExamResult examResult : expiredExams) {
            try {
                if(Objects.equals(examResult.getExamType(), "upload") && !examResult.isSubmit()){
                    examService.submitUploadExam(examResult.getExamId(), examResult.getUserId());
                }
                else{
                    // xử lý nộp bài cho exam với type là autogen
                }

                // Gửi thông báo Force Submit cho client
                String userId = String.valueOf(examResult.getUserId());
                String destination = "/user/" + userId + "/queue/notifications";
                // String forceSubmitMessage = "FORCE_SUBMIT: Time's up!"; // Cách cũ
                String forceSubmitMessage = "{\"type\":\"FORCE_SUBMIT\", \"message\":\"Đã hết giờ làm bài. Hệ thống tự động nộp bài.\"}";

                messagingTemplate.convertAndSend(destination, forceSubmitMessage);
                System.out.println("Sent force submit notification to user " + userId + " for examResult " + examResult.getId());

            } catch (Exception e) {
                System.out.println("Error auto-submitting or notifying for examResult " + examResult.getId() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("Finished scheduled check.");
    }
}