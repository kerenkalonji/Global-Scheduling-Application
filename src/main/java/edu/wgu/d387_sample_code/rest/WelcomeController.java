package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.service.TranslationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private final TranslationService translationService;

    public WelcomeController(TranslationService translationService) {
        this.translationService = translationService;
    }

    // ✅ For console output (multithreaded messages + time zone)
    @GetMapping("/console-welcome")
    public String showConsoleWelcome() {
        translationService.displayWelcomeMessages();
        return "Check IntelliJ console for English, French, and time‑zone messages.";
    }

    // ✅ For browser display (English + French + ET/MT/UTC)
    @GetMapping("/welcome")
    public String showWelcomeMessages() {
        return translationService.getWelcomeMessagesWithTimes();
    }
}
