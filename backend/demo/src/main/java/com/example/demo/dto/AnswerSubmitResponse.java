package com.example.demo.dto;

public class AnswerSubmitResponse {

    private boolean correct;
    private String answerTile;
    private String explanation;
    private long answerCount;
    private double correctRate;

    public AnswerSubmitResponse(boolean correct, String answerTile, String explanation, long answerCount, double correctRate) {
        this.correct = correct;
        this.answerTile = answerTile;
        this.explanation = explanation;
        this.answerCount = answerCount;
        this.correctRate = correctRate;
    }

    // getter
    public boolean isCorrect() { return correct; }
    public String getAnswerTile() { return answerTile; }
    public String getExplanation() { return explanation; }
    public long getAnswerCount() { return answerCount; }
    public double getCorrectRate() { return correctRate; }
}
