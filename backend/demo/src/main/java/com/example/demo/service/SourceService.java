package com.example.demo.service;

import com.example.demo.dto.SourceRequest;
import com.example.demo.entity.Source;
import com.example.demo.repository.SourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceService {

    private final SourceRepository repository;

    public SourceService(SourceRepository repository) {
        this.repository = repository;
    }

    public Long createSource(SourceRequest request) {
        Source source = new Source();
        source.setName(request.getName());
        source.setAuthor(request.getAuthor());
        return repository.save(source).getId();
    }

    public List<Source> getSources() {
        return repository.findAll();
    }

    public Source getSource(Long id) {
        return repository.findById(id).orElse(null);
    }
}
