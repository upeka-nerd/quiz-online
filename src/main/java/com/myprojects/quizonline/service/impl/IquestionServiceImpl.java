package com.myprojects.quizonline.service.impl;

import com.myprojects.quizonline.model.Question;
import com.myprojects.quizonline.repository.QuestionRepo;
import com.myprojects.quizonline.service.IquestionService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class IquestionServiceImpl implements IquestionService {

    private final QuestionRepo questionRepo;

    public IquestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }
    @Override
    public Question createQuestion(Question question) {
       return questionRepo.save(question);
    }
    @Override
    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }
    @Override
    public Optional<Question> getQuestionById(long id) {

        return questionRepo.findById(id);
    }

    @Override
    public List<String> getAllSubject() {
        return questionRepo.findAllDistinctSubject();
    }

    @Override
    public Question updateQuestion(long id, Question question) throws ChangeSetPersister.NotFoundException {

        Optional<Question> selectedQuestion = questionRepo.findById(id);
        if(selectedQuestion.isPresent()){
            Question question1=new Question(
                    selectedQuestion.get().getId(),selectedQuestion.get().getQuestion(),
                    selectedQuestion.get().getSubject(),selectedQuestion.get().getQuestionType(),
                    selectedQuestion.get().getChoices(),selectedQuestion.get().getCorrectAnswers()
            );

           return questionRepo.save(question1);

        }else{
            throw  new ChangeSetPersister.NotFoundException();

        }

    }

    @Override
    public void deleteQuestion(long id) {

        questionRepo.deleteById(id);

    }

    @Override
    public List<Question> getQuestionForUser(int numberOfQuestion, String subject) {


        Pageable pageable= PageRequest.of(0,numberOfQuestion);
        return questionRepo.findBySubject(subject,pageable).getContent();





    }
}
