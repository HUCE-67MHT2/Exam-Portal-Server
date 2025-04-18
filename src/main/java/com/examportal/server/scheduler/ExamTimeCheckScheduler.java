package com.examportal.server.scheduler;

 // Service để xử lý logic nộp bài, chấm điểm
import com.examportal.server.Entity.ExamResult;
import com.examportal.server.Repositories.ExamResultRepository;
import com.examportal.server.Service.ExamResultService;
import com.examportal.server.Service.ExamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // Quan trọng

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class ExamTimeCheckScheduler {

    private static final Logger log = LoggerFactory.getLogger(ExamTimeCheckScheduler.class);
    private static final long FIVE_MINUTES = 5;
    private static final long ONE_MINUTE = 1; // Để tránh xử lý lại exam đã hết hạn quá lâu

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // Để gửi WebSocket messages

    @Autowired
    private ExamResultRepository examResultRepository;

    @Autowired
    private ExamService examService;


    // Chạy mỗi 60 giây (60000ms)
    @Scheduled(fixedRate = 60000)
    @Transactional // Đảm bảo các thao tác DB và gửi message là nhất quán
    public void checkExamTimes() {
        LocalDateTime now = LocalDateTime.now();
        log.info("Running scheduled check for exam times at {}", now);

        // --- Xử lý gửi cảnh báo 5 phút ---
        LocalDateTime fiveMinutesFromNow = now.plusMinutes(FIVE_MINUTES);
        List<ExamResult> examsToWarn = examResultRepository.findExamsForWarning(now, fiveMinutesFromNow);

        for (ExamResult examResult : examsToWarn) {
            try {
                //Lấy userId thực tế từ examResult
                String userId = String.valueOf(examResult.getUserId()); // Chuyển Long thành String
                String destination = "/user/" + userId + "/queue/notifications"; // Đích đến user-specific

                // Tạo message (nên dùng JSON object thay vì String đơn giản)
                String warningMessage = "{\"type\":\"WARNING\", \"message\":\"Còn dưới 5 phút làm bài!\"}";
                // String warningMessage = "WARNING: 5 minutes left!"; //Cách cũ

                messagingTemplate.convertAndSend(destination, warningMessage);
                log.info("Sent 5-minute warning to user {} for examResult {}", userId, examResult.getId());

                // Đánh dấu đã gửi cảnh báo
                examResult.setWarningSent(true);
                examResultRepository.save(examResult); // Lưu lại trạng thái

            } catch (Exception e) {
                log.error("Error sending warning for examResult {}: {}", examResult.getId(), e.getMessage(), e);
            }
        }

        // --- Xử lý tự động nộp bài khi hết giờ ---
        LocalDateTime oneMinuteAgo = now.minusMinutes(ONE_MINUTE);
        List<ExamResult> expiredExams = examResultRepository.findExpiredExams(now, oneMinuteAgo);
//      List<ExamResult> expiredExams = examResultRepository.findExpiredExamsSimple(now); // Dùng query đơn giản hơn


        for (ExamResult examResult : expiredExams) {
            try {
                // Thực hiện logic tự động nộp bài (trong service)
                // Service này nên cập nhật is_submitted = true và chấm điểm (hoặc đưa vào queue)

                //
                if(Objects.equals(examResult.getExamType(), "upload")){
                    examService.submitUploadExam(examResult.getExamId(), examResult.getUserId());
                }
                else{
                    // xử lý nộp bài cho exam với type là autogen
                }


                // Gửi thông báo Force Submit cho client
                String userId = String.valueOf(examResult.getUserId());
                String destination = "/user/" + userId + "/queue/notifications";
                // String forceSubmitMessage = "FORCE_SUBMIT: Time's up!"; // Cách cũ
                String forceSubmitMessage = "{\"type\":\"FORCE_SUBMIT\", \"message\":\"Đã hết giờ làm bài. Hệ thống tự động nộp bài.\"}";


                messagingTemplate.convertAndSend(destination, forceSubmitMessage);
                log.info("Sent force submit notification to user {} for examResult {}", userId, examResult.getId());

            } catch (Exception e) {
                log.error("Error auto-submitting or notifying for examResult {}: {}", examResult.getId(), e.getMessage(), e);
            }
        }
        log.info("Finished scheduled check.");
    }
}