package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.WelcomeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    private final WelcomeService service = new WelcomeService();

    @GetMapping("/welcome")
    public List<String> getWelcome() throws InterruptedException {
        return service.getMessages();
    }
}