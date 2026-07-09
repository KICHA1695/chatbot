package com.example.chatbot;


import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.ChatResponse;
import com.example.chatbot.ChatService;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class ChatController {

    private final OllamaChatModel chatModel;

    public ChatController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/")
    public String showChatPage(Model model) {
        ChatRequest defaultRequest = new ChatRequest();
        defaultRequest.setType("text"); // default to text mode
        model.addAttribute("chatRequest", defaultRequest);
        return "chat";
    }

    @PostMapping("/send")
    public String handleRequest(@ModelAttribute("chatRequest") ChatRequest request, Model model) {
        model.addAttribute("userMessage", request.getMessage());
        model.addAttribute("selectedType", request.getType());

        try {
            if ("image".equalsIgnoreCase(request.getType())) {
                // Generate a safe URL-encoded prompt for the Image Engine
                String encodedPrompt = URLEncoder.encode(request.getMessage(), StandardCharsets.UTF_8);
                // Building a high-quality production prompt wrapper around the user text
                String generatedImageUrl = "https://image.pollinations.ai/prompt/" + encodedPrompt + "?width=1024&height=1024&nologo=true";
                
                model.addAttribute("imageUrl", generatedImageUrl);
            } else {
                // Handle normal local Ollama Text Response
                String responseText = chatModel.call(request.getMessage());
                model.addAttribute("aiResponse", responseText);
            }
        } catch (Exception e) {
            model.addAttribute("error", "Generation Failed: " + e.getMessage());
        }

        return "chat";
    }
}