package com.example.demo.dto;

import java.util.List;

public class AnswerResponse {

    private List<String> tehai;
    private String answerTile;
    private String doraTile;
    private String explanation;
    private long answerCount;
    private double correctRate;

    public AnswerResponse(List<String> tehai, String answerTile, String doraTile, String explanation, long answerCount, double correctRate) {
        this.tehai = tehai;
        this.answerTile = answerTile;
        this.doraTile = doraTile;
        this.explanation = explanation;
        this.answerCount = answerCount;
        this.correctRate = correctRate;
    }

    // getter
    public List<String> getTehai() { return tehai; }
    public String getAnswerTile() { return answerTile; }
    public String getDoraTile() { return doraTile; }
    public String getExplanation() { return explanation; }
    public long getAnswerCount() { return answerCount; }
    public double getCorrectRate() { return correctRate; }
}
