package com.examportal.server.Controllers;

import com.examportal.server.Configs.JwtTokenUtil;
import com.examportal.server.DTO.*;
import com.examportal.server.Entity.*;
import com.examportal.server.Repositories.ExamResultRepository;
import com.examportal.server.Request.ExamRequest;
import com.examportal.server.Service.*;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/exam")
public class ApiExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private QuestionAnswerService questionAnswerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExamResultService examResultService;

    @Autowired
    private StudentAnswerService studentAnswerService;

    @PostMapping(value = "/add/exam/with/file", consumes = "multipart/form-data")
    public ResponseEntity<?> addExamWithFile(@ModelAttribute ExamRequest examRequest,
                                             @RequestParam("file") MultipartFile file) {
        try {
            // Create a new Exam entity from the examRequest data
            Exam exam = new Exam();
            exam.setExamSessionId(examRequest.getExamSessionId());
            exam.setName(examRequest.getName());
            exam.setDescription(examRequest.getDescription());
            exam.setTotalQuestions(examRequest.getTotalQuestions());
            exam.setDuration(examRequest.getDuration());
            exam.setSubject(examRequest.getSubject());
            exam.setStartDate(examRequest.getStartDate());
            exam.setEndDate(examRequest.getEndDate());
            exam.setCreateDate(new Timestamp(System.currentTimeMillis()));

            // Upload file and save exam
            Exam savedExam = examService.createExamByFile(exam, file);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exam created successfully");
            response.put("examId", savedExam.getId());

            /**
             * Dạng respone mà client sẽ nhận được:
             * {
             *     "message": "Exam created successfully",
             *     "examId": 123
             * }
             */

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to create exam: " + e.getMessage()));
        }
    }

    @PostMapping(value = "/update/exam", consumes = "multipart/form-data")
    public ResponseEntity<?> updateExam(@ModelAttribute ExamRequest examRequest,
                                        @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            // Create a new Exam entity from the examRequest data
            Exam exam = new Exam();
            exam.setExamSessionId(examRequest.getExamSessionId());
            exam.setId(examRequest.getId());
            exam.setName(examRequest.getName());
            exam.setTotalQuestions(examRequest.getTotalQuestions());
            exam.setDescription(examRequest.getDescription());
            exam.setTotalQuestions(examRequest.getTotalQuestions());
            exam.setDuration(examRequest.getDuration());
            exam.setSubject(examRequest.getSubject());
            exam.setStartDate(examRequest.getStartDate());
            exam.setEndDate(examRequest.getEndDate());

            Exam updatedExam = examService.updateExamByFile(exam, file);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exam updated successfully");
            response.put("examId", updatedExam.getId());

            /**
             * Dạng respone mà client sẽ nhận được:
             * {
             *     "message": "Exam updated successfully",
             *     "examId": 123
             * }
             */
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to update exam: " + e.getMessage()));
        }
    }

    @PostMapping("/add/exam/manually")
    public ResponseEntity<?> addExamManually(@ModelAttribute ExamRequest examRequest) {
        try {
            // Xóa tất cả các exam cũ của session này có type là "auto-generate"
            List<Exam> oldExams = examService.getExamBySessionId(examRequest.getExamSessionId());
            for (Exam oldExam : oldExams) {
                if ("auto-generate".equals(oldExam.getType())) {
                    examService.delete(oldExam.getId());
                }
            }
            Exam exam = new Exam();
            exam.setExamSessionId(examRequest.getExamSessionId());
            exam.setName(examRequest.getName());
            exam.setDescription(examRequest.getDescription());
            exam.setDuration(examRequest.getDuration());
            exam.setSubject(examRequest.getSubject());
            exam.setStartDate(examRequest.getStartDate());
            exam.setEndDate(examRequest.getEndDate());
            exam.setCreateDate(new Timestamp(System.currentTimeMillis()));

            // Save the exam manually
            Exam savedExam = examService.createExamManually(exam);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Exam created successfully");
            response.put("examId", savedExam.getId());
            /**
             * Dạng respone mà client sẽ nhận được:
             * {
             *     "message": "Exam created successfully",
             *     "examId": 123
             * }
             */

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("Failed to create exam: " + e.getMessage()));
        }
    }

    @GetMapping("/get/list/exams/by/sessionId/{examSessionId}")
    public ResponseEntity<?> getListExams(@PathVariable("examSessionId") Long examSessionId) {
        try {
            List<Exam> exams = examService.getExamBySessionId(examSessionId);
            if (exams.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("No exams found for the given session ID"));
            }
            Map<String, Object> response = new HashMap<>();
            response.put("exams", exams);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/get/exam/{id}")
    public ResponseEntity<?> getExamById(@PathVariable("id") Long id) {
        try {
            Exam exam = examService.getExamById(id);
            if (exam == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("Exam not found"));
            }
            return ResponseEntity.ok(exam);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO("An error occurred: " + e.getMessage()));
        }
    }


    @PostMapping("/get/test/state/upload/exam/{examId}")
    public ResponseEntity<?> getTestState(@PathVariable("examId") Long examId, HttpServletRequest request) {
        try {
            String token = jwtTokenUtil.resolveToken(request);
            if (token == null || !jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Token không hợp lệ hoặc thiếu"));
            }
            Long userId = jwtTokenUtil.getIdFromToken(token);


            // Gọi service để kiểm tra trạng thái làm bài và tạo mới nếu cần
            UploadExamStateResponseDTO state = examService.getStateUploadExam(examId, userId);

            return ResponseEntity.ok(state);

        } catch (Exception e) {
            String message = e.getMessage();
            if (message != null && message.contains(":")) {
                message = message.substring(message.lastIndexOf(":") + 1).trim(); // lấy phần sau dấu ":"
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", message));
        }
    }


    @PostMapping("upload/submit/{examId}")
    public ResponseEntity<?> uploadSubmit(@PathVariable("examId") Long examId, HttpServletRequest request) {
        try {
            String token = jwtTokenUtil.resolveToken(request);
            if (token == null || !jwtTokenUtil.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Token không hợp lệ hoặc thiếu"));
            }
            Long userId = jwtTokenUtil.getIdFromToken(token);

            examService.submitUploadExam(examId, userId);

            return ResponseEntity.ok(Collections.singletonMap("message", "Exam submitted successfully"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // test ham (cos ther xoa di)

    @Autowired
    private ExamResultRepository examResultRepository;
    @PostMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest request) {
        try {
            LocalDateTime now = LocalDateTime.now();
            List<ExamResult> list = examResultRepository.findExpiredExamsSimple(now);

            for (ExamResult result : list) {
                System.out.println("Expired ExamResult: " + result.getId() + " | User: " + result.getUserId());
            }

            List<ExamResult> list2 = examResultRepository.findExamsForWarning(now, now.plusMinutes(5));

            for (ExamResult result : list2) {
                System.out.println("Warning ExamResult: " + result.getId() + " | User: " + result.getUserId());
            }

            return ResponseEntity.ok(Collections.singletonMap("count", list2.size()));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get/all/questions/and/answers/{examId}")
    public ResponseEntity<?> getAllQuestionsAndAnswers(@PathVariable("examId") Long examId) {
        try {
            Map<String, Object> response = new HashMap<>();
            ExamAutoGenDataDTO examAutoGenDataDTO = new ExamAutoGenDataDTO();
            examAutoGenDataDTO.setExam_id(examId);
            Exam exam = examService.getExamById(examId);
            if (exam == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("Exam not found"));
            }
            List<ExamQuestion> examQuestions =  examQuestionService.getExamQuestionsByExamId(examId);
            if (examQuestions.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("No questions found for the given exam ID"));
            }
            List<QuestionAutoGenDTO> questionAutoGenDTOList = new ArrayList<>();
            for (ExamQuestion examQuestion : examQuestions) {
                Question question = questionService.getQuestionById(examQuestion.getQuestionId());
                List<QuestionAnswer> questionAnswers = questionAnswerService.getAnswersByQuestionIdRand(question.getId());
                if (questionAnswers.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO("No answers found for the given question ID"));
                }
                QuestionAutoGenDTO questionAutoGenDTO = new QuestionAutoGenDTO();
                questionAutoGenDTO.setQuestion_id(question.getId());
                questionAutoGenDTO.setQuestion_text(question.getContent());
                List<AnswerAutoGenDTO> answerAutoGenDTOList = new ArrayList<>();
                for (QuestionAnswer questionAnswer : questionAnswers) {
                    AnswerAutoGenDTO answerAutoGenDTO = new AnswerAutoGenDTO();
                    answerAutoGenDTO.setAnswer_id(questionAnswer.getId());
                    answerAutoGenDTO.setAnswer_text(questionAnswer.getAnswerText());
                    answerAutoGenDTOList.add(answerAutoGenDTO);
                }
                questionAutoGenDTO.setQuestion_answers(answerAutoGenDTOList);
                questionAutoGenDTOList.add(questionAutoGenDTO);
            }
            examAutoGenDataDTO.setQuestions(questionAutoGenDTOList);
            return ResponseEntity.ok(examAutoGenDataDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO("An error occurred: " + e.getMessage()));
        }
    }
    @PostMapping("/submit/exam/type/auto-generate")
    public ResponseEntity<?> submitExamAutoGenerate(@RequestBody long examId, HttpServletRequest request) {
        try {
            String jwt = request.getHeader("Authorization");

            if (jwt == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
            }
            if (jwt.startsWith("Bearer ")) {
                jwt = jwt.substring(7);
            }
            Claims claims = jwtTokenUtil.getClaimsFromToken(jwt);
            java.util.Date expiration = claims.getExpiration();
            if (expiration.before(new java.util.Date())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token expired");
            }
            String username = claims.getSubject();

            if (username == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }

            User user = userService.getUserByUsername(username);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
            }
            ExamResult examResult = examResultService.getExamResultByExamIdAndUserId(examId, user.getId());
            examResult.setSubmit(true);
            examResult.setSubmitTime(new Timestamp(System.currentTimeMillis()));
            List<StudentAnswer> studentAnswers = studentAnswerService.getStudentAnswers(examId, user.getId());
            int count = 0;
            for (StudentAnswer studentAnswer : studentAnswers) {
                if(studentAnswer.getAnswerId() != null) {
                    QuestionAnswer questionAnswer = questionAnswerService.getAnswerById(studentAnswer.getAnswerId());
                    if(questionAnswer != null) {
                        if (questionAnswer.isCorrect()) {
                            count++;
                        }
                    }
                }

            }
            float percentage = (float) count / (float) examResult.getExam().getTotalQuestions();
            examResult.setTotalScore( percentage * 10);
            examResultService.save(examResult);
            return ResponseEntity.ok(examResult);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
