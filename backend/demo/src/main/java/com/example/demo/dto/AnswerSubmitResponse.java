package com.example.demo.dto;

import java.util.List;

public class AnswerSubmitResponse {

    private boolean correct;
    private List<String> tehai;
    private String answerTile;
    private String doraTile;
    private Integer sourceNumber;
    private String sourceName;
    private String sourceAuthor;
    private String explanation;
    private long answerCount;
    private double correctRate;

    public AnswerSubmitResponse(boolean correct, List<String> tehai, String answerTile, String doraTile, Integer sourceNumber, String sourceName, String sourceAuthor, String explanation, long answerCount, double correctRate) {
        this.correct = correct;
        this.tehai = tehai;
        this.answerTile = answerTile;
        this.doraTile = doraTile;
        this.sourceNumber = sourceNumber;
        this.sourceName = sourceName;
        this.sourceAuthor = sourceAuthor;
        this.explanation = explanation;
        this.answerCount = answerCount;
        this.correctRate = correctRate;
    }

    // getter
    public boolean isCorrect() { return correct; }
    public List<String> getTehai() { return tehai; }
    public String getAnswerTile() { return answerTile; }
    public String getDoraTile() { return doraTile; }
    public Integer getSourceNumber() { return sourceNumber; }
    public String getSourceName() { return sourceName; }
    public String getSourceAuthor() { return sourceAuthor; }
    public String getExplanation() { return explanation; }
    public long getAnswerCount() { return answerCount; }
    public double getCorrectRate() { return correctRate; }
}
