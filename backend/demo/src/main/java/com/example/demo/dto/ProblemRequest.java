package com.example.demo.dto;

import java.util.List;

public class ProblemRequest {

    private String questionText;
    private List<String> tehai;
    private String answerTile;
    private String doraTile;
    private Long sourceId;
    private Integer sourceNumber;
    private String explanation;

    public ProblemRequest() {}

    // getter / setter
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public List<String> getTehai() { return tehai; }
    public void setTehai(List<String> tehai) { this.tehai = tehai; }

    public String getAnswerTile() { return answerTile; }
    public void setAnswerTile(String answerTile) { this.answerTile = answerTile; }

    public String getDoraTile() { return doraTile; }
    public void setDoraTile(String doraTile) { this.doraTile = doraTile; }

    public Long getSourceId() { return sourceId; }
    public void setSourceId(Long sourceId) { this.sourceId = sourceId; }

    public Integer getSourceNumber() { return sourceNumber; }
    public void setSourceNumber(Integer sourceNumber) { this.sourceNumber = sourceNumber; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}