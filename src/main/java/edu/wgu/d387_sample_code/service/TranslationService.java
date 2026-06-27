package edu.wgu.d387_sample_code.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
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

    // ✅ B1b: Display multilingual welcome messages on console (multithreaded)
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

        // ✅ B3: Add time‑zone message on console
        executorService.submit(() -> System.out.println(getPresentationTimes()));

        executorService.shutdown();
    }

    // ✅ For frontend/browser display of messages (B1 + B3)
    public String getWelcomeMessagesWithTimes() {
        String english = messageSource.getMessage("welcome.message", null, Locale.ENGLISH);
        String french = messageSource.getMessage("welcome.message", null, Locale.FRENCH);
        String times = getPresentationTimes();

        return "English: " + english + "<br>"
                + "French: " + french + "<br>"
                + times;
    }

    // ✅ B3a/B3b: Time conversion and display message
    private String getPresentationTimes() {
        // Example presentation scheduled for 2:00 PM Eastern Time
        LocalDateTime presentationTime = LocalDateTime.of(2026, 5, 5, 14, 0);

        ZoneId etZone = ZoneId.of("America/Toronto"); // Eastern Time
        ZoneId mtZone = ZoneId.of("America/Denver");  // Mountain Time
        ZoneId utcZone = ZoneId.of("UTC");            // Coordinated Universal Time

        ZonedDateTime etTime = presentationTime.atZone(etZone);
        ZonedDateTime mtTime = etTime.withZoneSameInstant(mtZone);
        ZonedDateTime utcTime = etTime.withZoneSameInstant(utcZone);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        return String.format(
                "Online Live Presentation Times → ET: %s | MT: %s | UTC: %s",
                etTime.format(formatter),
                mtTime.format(formatter),
                utcTime.format(formatter)
        );
    }
}
