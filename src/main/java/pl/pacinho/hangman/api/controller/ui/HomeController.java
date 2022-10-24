package pl.pacinho.hangman.api.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.pacinho.hangman.api.config.ApiConfig;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "redirect:" + ApiConfig.UI_HANGMAN_HOME;
    }
}
