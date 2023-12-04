package com.gbjgroups.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gbjgroups.quizapp.model.AnswerResponse;
import com.gbjgroups.quizapp.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gbjgroups.quizapp.dao.QuestionDao;
import com.gbjgroups.quizapp.dao.QuizDao;
import com.gbjgroups.quizapp.model.Question;
import com.gbjgroups.quizapp.model.Quiz;

@Service
public class  QuizService {
   
    @Autowired
    QuizDao quizDao;
    
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        System.out.println(questions);
        System.out.println(quiz);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),  q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<AnswerResponse> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int score = 0;
        int i = 0;
        for (AnswerResponse response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {
                score++;
            }
            i++;
        }

        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}
