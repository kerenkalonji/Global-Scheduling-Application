package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.DisplayMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
public class WelcomeController {

    @GetMapping("/welcome")
    public List<String> getWelcomeMessages() {

        List<String> messages = new ArrayList<>();

        DisplayMessage englishDisplay = new DisplayMessage(Locale.US);
        DisplayMessage frenchDisplay = new DisplayMessage(Locale.CANADA_FRENCH);

        Thread englishThread = new Thread(englishDisplay);
        Thread frenchThread = new Thread(frenchDisplay);

        englishThread.start();
        frenchThread.start();

        try {

            englishThread.join();
            frenchThread.join();

            messages.add(englishDisplay.getMessage());
            messages.add(frenchDisplay.getMessage());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @GetMapping("/presentation")
    public String getPresentationTimes() {

        ZonedDateTime etTime =
                ZonedDateTime.now(ZoneId.of("America/New_York"));

        ZonedDateTime mtTime =
                etTime.withZoneSameInstant(ZoneId.of("America/Denver"));

        ZonedDateTime utcTime =
                etTime.withZoneSameInstant(ZoneId.of("UTC"));

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm");

        return "Live Presentation Times: "
                + etTime.format(formatter) + " ET, "
                + mtTime.format(formatter) + " MT, "
                + utcTime.format(formatter) + " UTC";
    }

}