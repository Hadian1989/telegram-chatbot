package com.chatbot.service;

import com.chatbot.controller.dto.ChatRequest;
import com.chatbot.model.BookModel;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenAIServiceImp implements GenAIService {

    private final Assistant assistant;

    @Override
    public String getResponse(ChatRequest request, Integer userId) {
        return assistant.chat(request.userId(), request.question());

    }

    @Override
    public BookModel getBookModelFromText(String question) {
        var popularGenres = List.of("Fiction", "Mystery", "Fantasy", "Science Fiction", "Romance", "Thriller", "Historical Fiction", "Non-Fiction", "Biography", "Self-Help", "Young Adult", "Children's Literature", "Horror", "Dystopian", "Graphic Novel");
        return assistant.extractBookFromText(question, popularGenres);
    }

    public String getResponseSimple(ChatRequest request) {
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(SystemMessage.systemMessage("Respond in French"));
        messages.add(UserMessage.userMessage(request.question()));
        var model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();
        return model.generate(messages).content().text();
    }
}
