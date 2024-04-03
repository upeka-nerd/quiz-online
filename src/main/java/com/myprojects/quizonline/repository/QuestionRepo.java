package com.myprojects.quizonline.repository;

import com.myprojects.quizonline.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface QuestionRepo extends JpaRepository<Question,Long> {

    @Query("SELECT DISTINCT q.subject FROM Question q ")
    List<String> findAllDistinctSubject();
    Page<Question> findBySubject(String subject, Pageable pageable);
}
