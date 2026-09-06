package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.Problem;
import com.example.demo.entity.Source;
import com.example.demo.service.ProblemService;
import com.example.demo.service.SourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/problems")
public class ProblemController {

    private final ProblemService service;
    private final SourceService sourceService;
    private final ObjectMapper objectMapper;

    public ProblemController(ProblemService service, SourceService sourceService, ObjectMapper objectMapper) {
        this.service = service;
        this.sourceService = sourceService;
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

        Map<String, Object> result = new HashMap<>();
        result.put("id", p.getId());
        result.put("questionText", p.getQuestionText());
        result.put("tehai", tehai);
        result.put("doraTile", p.getDoraTile());
        result.put("createdAt", p.getCreatedAt());

        if (p.getSourceId() != null) {
            Source source = sourceService.getSource(p.getSourceId());
            if (source != null) {
                result.put("sourceName", source.getName());
                result.put("sourceAuthor", source.getAuthor());
            }
        }
        result.put("sourceNumber", p.getSourceNumber());

        return result;
    }

    @GetMapping("/{id}/answer")
    public AnswerResponse getAnswer(@PathVariable Long id) {
        return service.getAnswer(id);
    }

    @PostMapping("/{id}/answers")
    public AnswerSubmitResponse submitAnswer(@PathVariable Long id, @RequestBody AnswerSubmitRequest request) {
        return service.submitAnswer(id, request);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.deleteProblem(id);
        return Map.of("message", "deleted");
    }
}