package com.examportal.server.controllers;

import com.examportal.server.Entity.WordExamAnswer;
import com.examportal.server.Service.WordExamAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WordExamAnswerController {
    @Autowired
    private WordExamAnswerService wordExamAnswerService;

    @GetMapping("/word_exam_answer")
    public String wordExamAnswer(Model model) {
        List<WordExamAnswer> wordExamAnswers = wordExamAnswerService.getList();
        model.addAttribute("wordExamAnswers", wordExamAnswers);
        return "WordExamAnswer";
    }

    @PostMapping("/addOrUpdateWordExamAnswer")
    public String addOrUpdateWordExamAnswer(WordExamAnswer wordExamAnswer) {
        wordExamAnswerService.save(wordExamAnswer);
        return "redirect:/word_exam_answer";
    }

    @PostMapping("/delete/word_exam_answer/{id}")
    public String deleteWordExamAnswer(Long id) {
        wordExamAnswerService.delete(id);
        return "redirect:/word_exam_answer";
    }

    @GetMapping("/edit/word_exam_answer/{id}")
    public String editWordExamAnswer(Long id, Model model) {
        WordExamAnswer wordExamAnswer = wordExamAnswerService.getWordExamAnswerById(id);
        model.addAttribute("wordExamAnswer", wordExamAnswer);
        return "Update_WordExamAnswer";
    }
}
