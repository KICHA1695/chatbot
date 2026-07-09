package com.example.chatbot;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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