package com.example.chatbot;



import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    // ChatClient is automatically initialized via AiConfig
    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public ChatResponse generateResponse(ChatRequest request) {
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        
        // Fluid Fluent API syntax native to modern Spring AI
        String aiReply = chatClient.prompt()
                .user(request.getMessage())
                .call()
                .content();
        
        return new ChatResponse(aiReply);
    }
}