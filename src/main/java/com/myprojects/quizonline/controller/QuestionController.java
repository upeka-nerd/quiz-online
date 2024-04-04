package com.myprojects.quizonline.controller;
import com.myprojects.quizonline.model.Question;
import com.myprojects.quizonline.service.IquestionService;
import org.apache.catalina.connector.Response;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/all")
    public ResponseEntity<List<Question>>getAllQuestion(){
        List<Question> allQuestion = iquestionService.getAllQuestion();
        return ResponseEntity.ok(allQuestion);

    }

    @GetMapping("{id}")
    public ResponseEntity<Question>getQuestionById(@PathVariable long id) throws ChangeSetPersister.NotFoundException {

        Optional<Question> selectedQuestionById = iquestionService.getQuestionById(id);
        if(selectedQuestionById.isPresent()){
            return ResponseEntity.ok(selectedQuestionById.get());
        }else{
            throw new ChangeSetPersister.NotFoundException();
        }

    }


   @PutMapping("{id}")
   public ResponseEntity<Question>updateQuestion(@PathVariable long id,@RequestBody Question question) throws ChangeSetPersister.NotFoundException {
      Question updatedQuestion = iquestionService.updateQuestion(id, question);
      return ResponseEntity.ok(updatedQuestion);
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void>deleteQuestion(@PathVariable long id){
          iquestionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
   }

   @GetMapping("/subjects")
   public ResponseEntity<List<String>>getAllSubjects(){

       List<String> allSubject = iquestionService.getAllSubject();
       return ResponseEntity.ok(allSubject);

   }


   public ResponseEntity<List<Question>>getQuestionForUser(@RequestParam int numOfQuestions,
                                                           @RequestParam String subjects){

              List<Question>allQuestions =iquestionService.getQuestionForUser(numOfQuestions,subjects);
              List<Question>mutableQuestions=new ArrayList<>(allQuestions);
              Collections.shuffle(mutableQuestions);

              int avaiableQuestions = Math.min(numOfQuestions, mutableQuestions.size());
              List<Question>randomQuestions=mutableQuestions.subList(0,avaiableQuestions);


                return ResponseEntity.ok(randomQuestions);

   }



}
