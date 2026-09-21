package com.example.demo.repository;

import com.example.demo.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

    @Query("SELECT COALESCE(MAX(p.sourceNumber), 0) FROM Problem p WHERE p.sourceId = :sourceId")
    Integer findMaxSourceNumber(@Param("sourceId") Long sourceId);
}