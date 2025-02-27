package com.examportal.server.controllers;

import com.examportal.server.Entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.examportal.server.Service.QuestionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question")
    public String Question(Model model){
        List<Question> questions = questionService.getList();
        model.addAttribute("questions", questions);
        return "Question";
    }

    @PostMapping("/addOrUpdateQuestion")
    public String addOrUpdateQuestion(Question question){
        questionService.save(question);
        return "redirect:/question";
    }
    @PostMapping("/delete/question/{id}")
    public String deleteQuestion(Long id){
        questionService.delete(id);
        return "redirect:/question";
    }
    @GetMapping("/edit/question/{id}")
    public String editQuestion(Long id, Model model){
        Question question = questionService.getQuestionById(id);
        model.addAttribute("question", question);
        return "Update_Question";
    }


}
