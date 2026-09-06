package com.example.demo.dto;

public class AnswerSubmitRequest {

    private String selectedTile;
    private String clientId;

    public String getSelectedTile() { return selectedTile; }
    public void setSelectedTile(String selectedTile) { this.selectedTile = selectedTile; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}
