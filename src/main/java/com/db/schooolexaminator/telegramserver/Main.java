package com.db.schooolexaminator.telegramserver;

import com.db.schooolexaminator.telegramserver.telegrambot.ExaminatorMessageHandler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

/**
 * Created by JavaSchoolStudent on 01.09.2016.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.db.schooolexaminator.telegramserver");

        ExaminatorMessageHandler examinatorMessageHandler = context.getBean("examinatorMessageHandler", ExaminatorMessageHandler.class);

        /*MailSender ms = context.getBean("mailSender", MailSender.class);
        ms.sendEmail("maxim.usmanov23@gmail.com", "Introduction", "Hello. Stas is unbelievable.");*/
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(examinatorMessageHandler);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        context.close();
        /*PictureManager pm = new PictureManager("pictures/picture.jpg", 10, 10);
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();
        pm.openPiece();*/

    }
}
