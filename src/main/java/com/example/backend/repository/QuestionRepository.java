package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
