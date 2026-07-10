package com.example.chatbot;

import com.example.chatbot.dto.ChatRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse generateResponse(ChatRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        
        // System instruction forces the model to process faster and output brief text
        String aiReply = chatClient.prompt()
                .system("You are a helpful assistant. Keep your responses very short, direct, and under 3 sentences.")
                .user(request.getMessage())
                .call()
                .content();
        
        return new ChatResponse(aiReply);
    }
}