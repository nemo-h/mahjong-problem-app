package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.entity.AnswerLog;
import com.example.demo.entity.Problem;
import com.example.demo.repository.AnswerLogRepository;
import com.example.demo.repository.ProblemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository repository;
    private final AnswerLogRepository answerLogRepository;
    private final ObjectMapper objectMapper;

    public ProblemService(ProblemRepository repository, AnswerLogRepository answerLogRepository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.answerLogRepository = answerLogRepository;
        this.objectMapper = objectMapper;
    }

    public Long createProblem(ProblemRequest request) throws Exception {

        Problem problem = new Problem();
        problem.setQuestionText(request.getQuestionText());
        problem.setTehaiJson(objectMapper.writeValueAsString(request.getTehai()));
        problem.setAnswerTile(request.getAnswerTile());
        problem.setDoraTile(request.getDoraTile());
        problem.setSourceId(request.getSourceId());
        problem.setSourceNumber(request.getSourceNumber());
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
        long answerCount = answerLogRepository.countByProblemId(id);
        long correctCount = answerLogRepository.countByProblemIdAndCorrectTrue(id);
        double correctRate = answerCount == 0 ? 0.0 : (double) correctCount / answerCount;
        return new AnswerResponse(p.getAnswerTile(), p.getExplanation(), answerCount, correctRate);
    }

    public AnswerSubmitResponse submitAnswer(Long id, AnswerSubmitRequest request) {
        Problem p = getProblem(id);
        boolean correct = p.getAnswerTile().equals(request.getSelectedTile());

        AnswerLog log = new AnswerLog();
        log.setProblemId(id);
        log.setClientId(request.getClientId());
        log.setSelectedTile(request.getSelectedTile());
        log.setCorrect(correct);
        log.setAnsweredAt(LocalDateTime.now());
        answerLogRepository.save(log);

        long answerCount = answerLogRepository.countByProblemId(id);
        long correctCount = answerLogRepository.countByProblemIdAndCorrectTrue(id);
        double correctRate = (double) correctCount / answerCount;

        return new AnswerSubmitResponse(correct, p.getAnswerTile(), p.getExplanation(), answerCount, correctRate);
    }

    public void deleteProblem(Long id) {
        repository.deleteById(id);
    }
}