package com.examportal.server.Controllers;

import com.examportal.server.Entity.Question;
import com.examportal.server.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


}
