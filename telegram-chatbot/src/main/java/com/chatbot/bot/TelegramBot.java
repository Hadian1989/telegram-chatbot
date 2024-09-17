package com.chatbot.bot;

import com.chatbot.service.Assistant;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {
    private final String botName;
    Assistant assistant;

    public TelegramBot(String botName, String botToken) {
        super(botToken);
        this.botName = botName;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            var message = update.getMessage();
            var chatId = message.getChatId();
            log.info("Message received: {}", message.getText());
            String response = assistant.chat(Math.toIntExact(chatId), message.getText());
            try {
                execute(new SendMessage(chatId.toString(), response));
            } catch (TelegramApiException e) {
                log.error("Execution during telegram api: {}", e.getMessage());
            }
        } else {
            log.info("update has no text: {}", update);
        }

    }

    @Override
    public String getBotUsername() {
        return this.botName;
    }
}
