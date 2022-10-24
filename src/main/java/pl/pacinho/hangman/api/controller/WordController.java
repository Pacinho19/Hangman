package pl.pacinho.hangman.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pacinho.hangman.api.config.ApiConfig;
import pl.pacinho.hangman.service.WordService;

@RequiredArgsConstructor
@RestController
public class WordController {

    private final WordService wordService;

    @GetMapping(ApiConfig.RANDOM_WORD_PATH)
    public String getRandomWord() {
        return wordService.getRandomWord();
    }
}
