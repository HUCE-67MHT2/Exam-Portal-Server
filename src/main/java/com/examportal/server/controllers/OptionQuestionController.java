package com.examportal.server.controllers;

import com.examportal.server.Entity.Option;
import com.examportal.server.Service.OptionQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OptionQuestionController {
    @Autowired
    private OptionQuestionService optionQuestionService;

    @GetMapping("/question_option")
    public String Option(Model model) {
        List<Option> options = optionQuestionService.getList();
        model.addAttribute("options", options);
        return "OptionQuestion";
    }

    @GetMapping("/edit/option/{id}")
    public String editOption(Model model, @PathVariable("id") Long id) {
        model.addAttribute("option", optionQuestionService.getOptionById(id));
        return "Update_Option";
    }

    @PostMapping("/delete/option/{id}")
    public String deleteOption(@PathVariable("id") Long id) {
        optionQuestionService.delete(id);
        return "redirect:/question_option";
    }

    @PostMapping("/addOrUpdateOption")
    public String addOrUpdateOption(Option option) {
        optionQuestionService.save(option);
        return "redirect:/question_option";
    }
}
