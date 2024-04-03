package com.myprojects.quizonline.controller;


import com.myprojects.quizonline.model.Question;
import com.myprojects.quizonline.service.IquestionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuestionController {


    private final IquestionService iquestionService;

    @Autowired
    public QuestionController(IquestionService iquestionService) {
        this.iquestionService = iquestionService;
    }

    @PostMapping()
    public ResponseEntity<Question>createQuestion(@Validated @RequestBody Question question){
        Question savedQuestion = iquestionService.createQuestion(question);
        return ResponseEntity.status(CREATED).body(savedQuestion);

    }

















}
