package com.chatbot.service;

import com.chatbot.controller.dto.ChatRequest;
import com.chatbot.model.BookModel;

public interface GenAIService {
    String getResponse(ChatRequest request, Integer userId);

    BookModel getBookModelFromText(String question);
}
