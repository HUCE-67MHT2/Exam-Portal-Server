package com.examportal.server.Controllers;

import com.examportal.server.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


}
