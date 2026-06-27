package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.Problem;
import com.example.demo.repository.ProblemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository repository;
    private final ObjectMapper objectMapper;

    public ProblemService(ProblemRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    public Long createProblem(ProblemRequest request) throws Exception {

        Problem problem = new Problem();
        problem.setQuestionText(request.getQuestionText());
        problem.setTehaiJson(objectMapper.writeValueAsString(request.getTehai()));
        problem.setAnswerTile(request.getAnswerTile());
        problem.setExplanation(request.getExplanation());
        problem.setCreatedAt(LocalDateTime.now());

        return repository.save(problem).getId();
    }

    public List<Problem> getProblems() {
        return repository.findAll();
    }

    public Problem getProblem(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public AnswerResponse getAnswer(Long id) {
        Problem p = getProblem(id);
        return new AnswerResponse(p.getAnswerTile(), p.getExplanation());
    }

    public void deleteProblem(Long id) {
        repository.deleteById(id);
    }
}