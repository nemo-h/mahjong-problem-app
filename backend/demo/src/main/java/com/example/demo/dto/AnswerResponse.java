package com.example.demo.dto;

public class AnswerResponse {

    private String answerTile;
    private String explanation;

    public AnswerResponse(String answerTile, String explanation) {
        this.answerTile = answerTile;
        this.explanation = explanation;
    }

    // getter
    public String getAnswerTile() { return answerTile; }
    public String getExplanation() { return explanation; }
}