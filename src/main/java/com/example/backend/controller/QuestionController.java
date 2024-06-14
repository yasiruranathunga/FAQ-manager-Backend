package com.example.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.backend.exception.QuestionNotFoundException;
import com.example.backend.exception.UserNotFoundException;
import com.example.backend.model.Question;
import com.example.backend.model.User;
import com.example.backend.repository.QuestionRepository;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")

public class QuestionController {
	
	 @Autowired
	    private QuestionRepository questionRepository;
	 
	    @PostMapping("/qestion")
	   Question newQuestion(@RequestBody  Question newQuestion) {
	        return questionRepository.save(newQuestion);
	    }

	    @GetMapping("/qestions")
	    List<Question> getAllUsers() {
	        return questionRepository.findAll();
	    }

	    @GetMapping("/qestion/{id}")
	    Question getUserById(@PathVariable Long id) {
	        return questionRepository.findById(id)
	                .orElseThrow(() -> new QuestionNotFoundException(id));
	    }
	    
	    
	    
	    @PutMapping("/qestion/{id}")
	    Question updateUser(@RequestBody Question newQuestion, @PathVariable Long id) {
	        return questionRepository.findById(id)
	                .map(question -> {
//	                	question.setQuestion(newQuestion.getQuestion());
//	                	question.setCategory(newQuestion.getCategory());
	                	question.setStatus(newQuestion.getStatus());
	                    return questionRepository.save(question);
	                }).orElseThrow(() -> new UserNotFoundException(id));
	    }

	    @DeleteMapping("/qestion/{id}")
	    String deleteUser(@PathVariable Long id){
	        if(!questionRepository.existsById(id)){
	            throw new UserNotFoundException(id);
	        }
	        questionRepository.deleteById(id);
	        return  "User with id "+id+" has been deleted success.";
	    }


}
