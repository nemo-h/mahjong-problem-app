package com.example.demo.dto;

import java.util.List;

public class AnswerSubmitResponse {

    private boolean correct;
    private List<String> tehai;
    private String answerTile;
    private String explanation;
    private long answerCount;
    private double correctRate;

    public AnswerSubmitResponse(boolean correct, List<String> tehai, String answerTile, String explanation, long answerCount, double correctRate) {
        this.correct = correct;
        this.tehai = tehai;
        this.answerTile = answerTile;
        this.explanation = explanation;
        this.answerCount = answerCount;
        this.correctRate = correctRate;
    }

    // getter
    public boolean isCorrect() { return correct; }
    public List<String> getTehai() { return tehai; }
    public String getAnswerTile() { return answerTile; }
    public String getExplanation() { return explanation; }
    public long getAnswerCount() { return answerCount; }
    public double getCorrectRate() { return correctRate; }
}
