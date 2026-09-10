package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "problem")
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    @Column(columnDefinition = "TEXT")
    private String tehaiJson;

    private String answerTile;

    private String doraTile;

    private Long sourceId;

    private Integer sourceNumber;

    private String ba;

    private String kaze;

    private Integer jun;

    @Column(columnDefinition = "TEXT")
    private String explanation;

    private LocalDateTime createdAt;

    // getter / setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getTehaiJson() { return tehaiJson; }
    public void setTehaiJson(String tehaiJson) { this.tehaiJson = tehaiJson; }

    public String getAnswerTile() { return answerTile; }
    public void setAnswerTile(String answerTile) { this.answerTile = answerTile; }

    public String getDoraTile() { return doraTile; }
    public void setDoraTile(String doraTile) { this.doraTile = doraTile; }

    public Long getSourceId() { return sourceId; }
    public void setSourceId(Long sourceId) { this.sourceId = sourceId; }

    public Integer getSourceNumber() { return sourceNumber; }
    public void setSourceNumber(Integer sourceNumber) { this.sourceNumber = sourceNumber; }

    public String getBa() { return ba; }
    public void setBa(String ba) { this.ba = ba; }

    public String getKaze() { return kaze; }
    public void setKaze(String kaze) { this.kaze = kaze; }

    public Integer getJun() { return jun; }
    public void setJun(Integer jun) { this.jun = jun; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}