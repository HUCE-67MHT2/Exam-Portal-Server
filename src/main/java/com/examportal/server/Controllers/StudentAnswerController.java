package com.examportal.server.Controllers;

import com.examportal.server.Service.StudentAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentAnswerController {
    @Autowired
    private StudentAnswerService studentAnswerService;

//    @GetMapping("/student_answer")
//    public String studentAnswer(Model model) {
//        List<StudentAnswer> studentAnswers = studentAnswerService.getList();
//        model.addAttribute("studentAnswers", studentAnswers);
//        return "StudentAnswer";
//    }
//
//    @PostMapping("/addOrUpdateStudentAnswer")
//    public String addOrUpdateStudentAnswer(StudentAnswer studentAnswer) {
//        studentAnswerService.save(studentAnswer);
//        return "redirect:/studentAnswer";
//    }
//
//    @PostMapping("/delete/studentAnswer/{id}")
//    public String deleteStudentAnswer(Long id) {
//        studentAnswerService.delete(id);
//        return "redirect:/studentAnswer";
//    }
//
//    @GetMapping("/edit/studentAnswer/{id}")
//    public String editStudentAnswer(Long id, Model model) {
//        StudentAnswer studentAnswer = studentAnswerService.getStudentAnswerById(id);
//        model.addAttribute("studentAnswer", studentAnswer);
//        return "Update_StudentAnswer";
//    }
}
