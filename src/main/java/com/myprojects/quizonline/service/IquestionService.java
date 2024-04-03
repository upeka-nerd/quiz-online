package com.myprojects.quizonline.service;

import com.myprojects.quizonline.model.Question;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IquestionService {

    Question createQuestion(Question question);
    List<Question>getAllQuestion();
    Optional<Question>getQuestionById(long id);
    List<String>getAllSubject();
    Question updateQuestion(long id,Question question) throws ChangeSetPersister.NotFoundException;
    void deleteQuestion(long id);
    List<Question>getQuestionForUser(int numberOfQuestion,String subject);


}
