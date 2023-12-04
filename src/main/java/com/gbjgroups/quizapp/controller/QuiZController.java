package com.gbjgroups.quizapp.controller;

import com.gbjgroups.quizapp.model.AnswerResponse;
import com.gbjgroups.quizapp.model.Question;
import com.gbjgroups.quizapp.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gbjgroups.quizapp.service.QuizService;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuiZController {
    
    @Autowired
    QuizService quizService;
    
        @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id) {
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> sumbitQuiz(@PathVariable Integer id, @RequestBody List<AnswerResponse> responses) {
        return quizService.calculateResult(id, responses);
    }


}
