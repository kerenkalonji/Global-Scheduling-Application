package edu.wgu.d387_sample_code;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayMessage implements Runnable {
    private Locale locale;
    private String message;

    public DisplayMessage(Locale locale) {
        this.locale = locale;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void run() {

        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        this.message = bundle.getString("welcome.message");
    }
}
