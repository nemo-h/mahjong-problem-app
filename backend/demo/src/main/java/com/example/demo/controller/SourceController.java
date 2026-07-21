package com.example.demo.controller;

import com.example.demo.dto.SourceRequest;
import com.example.demo.entity.Source;
import com.example.demo.service.SourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/sources")
public class SourceController {

    private final SourceService service;

    public SourceController(SourceService service) {
        this.service = service;
    }

    @PostMapping
    public Map<String, Object> create(@RequestBody SourceRequest request) {
        Long id = service.createSource(request);
        return Map.of("id", id, "message", "source created");
    }

    @GetMapping
    public List<Source> getAll() {
        return service.getSources();
    }
}
