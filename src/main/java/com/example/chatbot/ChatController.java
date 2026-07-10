package com.example.chatbot;

import com.example.chatbot.dto.ChatRequest;
import com.example.chatbot.ChatResponse;
import com.example.chatbot.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class ChatController {

    private final ChatService chatService;

    // Injecting the ChatService instead of the raw OllamaChatModel
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public String showChatPage(Model model) {
        ChatRequest defaultRequest = new ChatRequest();
        defaultRequest.setType("text"); 
        model.addAttribute("chatRequest", defaultRequest);
        return "chat";
    }

    @PostMapping("/send")
    public String handleRequest(@ModelAttribute("chatRequest") ChatRequest request, Model model) {
        model.addAttribute("userMessage", request.getMessage());
        model.addAttribute("selectedType", request.getType());

        try {
            if ("image".equalsIgnoreCase(request.getType())) {
                // Image requests stay fast because they generate via a quick public URL string
                String encodedPrompt = URLEncoder.encode(request.getMessage(), StandardCharsets.UTF_8);
                String generatedImageUrl = "https://image.pollinations.ai/prompt/" + encodedPrompt + "?width=1024&height=1024&nologo=true";
                model.addAttribute("imageUrl", generatedImageUrl);
            } else {
                // Route text queries through your system-optimized ChatService
                ChatResponse response = chatService.generateResponse(request);
                model.addAttribute("aiResponse", response.getResponse()); // Assumes ChatResponse has a getAiReply() getter
            }
        } catch (Exception e) {
            model.addAttribute("error", "Generation Failed: " + e.getMessage());
        }

        return "chat";
    }
}