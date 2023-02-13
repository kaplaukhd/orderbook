package ru.kata.spring.boot_security.demo.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class NotificationBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "rodnik_store_bot";
    }

    @Override
    public String getBotToken() {
        return "5953546926:AAHJwtal1U4iXtYPCAddQlupndl4btGV9HM";
    }

    @Override
    public void onUpdateReceived(Update update) {

    }


    public void sendMessage(String message, Long chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException var) {
            log.error(var.getMessage());
        }
    }
}
