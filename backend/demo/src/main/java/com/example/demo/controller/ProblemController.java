package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Problem;
import com.example.demo.service.ProblemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService service;
    private final ObjectMapper objectMapper;

    public ProblemController(ProblemService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody ProblemRequest request) throws Exception {
        Long id = service.createProblem(request);
        return Map.of("id", id, "message", "problem created");
    }


    
    @GetMapping
    public List<Map<String, Object>> getAll() {
        List<Problem> list = service.getProblems();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Problem p : list) {
            result.add(Map.of(
                    "id", p.getId(),
                    "questionText", p.getQuestionText(),
                    "createdAt", p.getCreatedAt()
            ));
        }
        return result;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getOne(@PathVariable Long id) throws Exception {

        Problem p = service.getProblem(id);

        List<String> tehai = objectMapper.readValue(p.getTehaiJson(), List.class);

        return Map.of(
                "id", p.getId(),
                "questionText", p.getQuestionText(),
                "tehai", tehai,
                "createdAt", p.getCreatedAt()
        );
    }

    @GetMapping("/{id}/answer")
    public AnswerResponse getAnswer(@PathVariable Long id) {
        return service.getAnswer(id);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.deleteProblem(id);
        return Map.of("message", "deleted");
    }
}