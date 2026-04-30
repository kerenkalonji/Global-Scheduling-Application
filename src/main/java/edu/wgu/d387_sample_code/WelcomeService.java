package edu.wgu.d387_sample_code;

import java.util.*;

public class WelcomeService {

    public List<String> getMessages() throws InterruptedException {

        List<String> messages = Collections.synchronizedList(new ArrayList<>());

        Thread englishThread = new Thread(() -> {
            ResourceBundle enBundle = ResourceBundle.getBundle("messages", Locale.ENGLISH);
            messages.add(enBundle.getString("welcome"));
        });

        Thread frenchThread = new Thread(() -> {
            ResourceBundle frBundle = ResourceBundle.getBundle("messages", Locale.FRENCH);
            messages.add(frBundle.getString("welcome"));
        });

        englishThread.start();
        frenchThread.start();

        englishThread.join();
        frenchThread.join();

        return messages;
    }
}