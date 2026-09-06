package com.example.demo.repository;

import com.example.demo.entity.AnswerLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerLogRepository extends JpaRepository<AnswerLog, Long> {

    long countByProblemId(Long problemId);

    long countByProblemIdAndCorrectTrue(Long problemId);
}
