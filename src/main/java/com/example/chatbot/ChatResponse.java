package com.example.chatbot;


public class ChatResponse {
    private String response;
  

        // Explicit No-Args Constructor
        public ChatResponse() {
        }

        // Explicit All-Args Constructor
        public ChatResponse(String response) {
            this.response = response;
        }

        // Getter and Setter
        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    
}