package com.examportal.server.controllers;

import com.examportal.server.Entity.Subject;
import com.examportal.server.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subject")
    public String subjectPage(Model model) {
        List<Subject> subjects = subjectService.getList();
        model.addAttribute("subjects", subjects);
        return "Subject";
    }

    @PostMapping("/addOrUpdateSubject")
    public String addOrUpdateSubject(Subject subject) {
        subjectService.save(subject);
        return "redirect:/subject";
    }

    @PostMapping("/delete/subject/{id}")
    public String deleteSubject(@PathVariable Long id) {
        subjectService.delete(id);
        return "redirect:/subject";
    }
}
