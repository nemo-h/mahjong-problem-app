package com.example.demo.dto;

import java.util.List;

public class ProblemRequest {

    private String questionText;
    private List<String> tehai;
    private String answerTile;
    private String explanation;

    public ProblemRequest() {}

    // getter / setter
    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public List<String> getTehai() { return tehai; }
    public void setTehai(List<String> tehai) { this.tehai = tehai; }

    public String getAnswerTile() { return answerTile; }
    public void setAnswerTile(String answerTile) { this.answerTile = answerTile; }

    public String getExplanation() { return explanation; }
    public void setExplanation(String explanation) { this.explanation = explanation; }
}