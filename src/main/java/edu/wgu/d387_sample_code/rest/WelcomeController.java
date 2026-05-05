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


    @GetMapping("/console-welcome")
    public String showConsoleWelcome() {
        translationService.displayWelcomeMessages();
        return "Check IntelliJ console for messages.";
    }


    @GetMapping("/welcome")
    public String showWelcomeMessages() {
        return translationService.getWelcomeMessages();
    }
}