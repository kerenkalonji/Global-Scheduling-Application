package edu.wgu.d387_sample_code.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TranslationService {

    private final MessageSource messageSource;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public TranslationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // ✅ REQUIRED for B1b (multithreading - console output)
    public void displayWelcomeMessages() {

        executorService.submit(() -> {
            LocaleContextHolder.setLocale(Locale.ENGLISH);
            String english = messageSource.getMessage("welcome.message", null, Locale.ENGLISH);
            System.out.println("English: " + english);
            LocaleContextHolder.resetLocaleContext();
        });

        executorService.submit(() -> {
            LocaleContextHolder.setLocale(Locale.FRENCH);
            String french = messageSource.getMessage("welcome.message", null, Locale.FRENCH);
            System.out.println("French: " + french);
            LocaleContextHolder.resetLocaleContext();
        });

        executorService.shutdown();
    }

    // ✅ For displaying in browser
    public String getWelcomeMessages() {
        String english = messageSource.getMessage("welcome.message", null, Locale.ENGLISH);
        String french = messageSource.getMessage("welcome.message", null, Locale.FRENCH);

        return "English: " + english + "<br>French: " + french;
    }
}