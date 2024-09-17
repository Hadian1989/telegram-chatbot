package com.chatbot.controller;

import com.chatbot.controller.dto.ChatRequest;
import com.chatbot.controller.dto.ChatResponse;
import com.chatbot.model.BookModel;
import com.chatbot.service.GenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class GenerativeController {
    private final GenAIService genAIService;

    @PostMapping
    public ChatResponse getChatResponse(@RequestBody ChatRequest request) {
        return new ChatResponse(genAIService.getResponse(request, request.userId()));
    }
  @PostMapping("/book")
    public BookModel getBookModelFromText(@RequestBody ChatRequest request){
        return genAIService.getBookModelFromText(request.question());
  }
}
