package pl.pacinho.hangman.api.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pacinho.hangman.api.config.ApiConfig;
import pl.pacinho.hangman.service.WordService;
import pl.pacinho.hangman.utils.HangmanSessionService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HangmanController {

    private final HangmanSessionService sessionService;

    @GetMapping(ApiConfig.UI_HANGMAN_HOME)
    public String home(Model model,
                       HttpSession session) {
        model.addAttribute("hangman", sessionService.getHangman(session));
        model.addAttribute("word", sessionService.getWord(session));

        return "hangman-home";
    }

    @PostMapping(ApiConfig.UI_HANGMAN_CHECK)
    public String check(String letter,
                        Model model,
                        HttpSession session) {
        if (letter == null || letter.length() != 1)
            model.addAttribute("error", "Invalid letter length! Input only single letter!");
        else {
            try {
                sessionService.checkLetter(session, letter);
            } catch (Exception ex) {
                model.addAttribute("error", ex.getMessage());
            }
        }
        return home(model, session);
    }

    @GetMapping(ApiConfig.UI_HANGMAN_NEW_GAME)
    public String newGame(HttpSession session){
        sessionService.newGame(session);
        return "redirect:" + ApiConfig.UI_HANGMAN_HOME;
    }
}
