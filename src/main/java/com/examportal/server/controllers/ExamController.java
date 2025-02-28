package com.examportal.server.controllers;

import com.examportal.server.Entity.Exam;
import com.examportal.server.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/exam")
    public String exam(Model model) {
        List<Exam> exams = this.examService.getList();
        model.addAttribute("exams", exams);
        return "Exam";
    }

    @PostMapping("/addOrUpdateExam")
    public String addOrUpdateExam(Exam exam) {
        exam.setCreated_at(new Timestamp(System.currentTimeMillis()));
        examService.save(exam);
        return "redirect:/exam";
    }

    @GetMapping("/edit/exam/{id}")
    public String editExam(@PathVariable("id") Long id, Model model) {
        Exam exam = examService.getExamById(id);
        model.addAttribute("exam", exam);
        return "UpdateExam";
    }

    @PostMapping("/delete/exam/{id}")
    public String deleteExam(@PathVariable("id") Long id) {
        examService.delete(id);
        return "redirect:/exam";

    }
}
