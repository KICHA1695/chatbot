package com.example.chatbot;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRequest {
    private String message;
    private String type; // Value will be "text" or "image"

    public ChatRequest() {}

    public ChatRequest(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
