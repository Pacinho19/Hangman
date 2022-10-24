package pl.pacinho.hangman.service;

import org.springframework.stereotype.Service;
import pl.pacinho.hangman.utils.FileUtils;
import pl.pacinho.hangman.utils.RandomUtils;

import java.util.List;

@Service
public class WordService {

    private final String WORDS_DICTIONARY_PATH = "src\\main\\resources\\words.txt";
    private List<String> words;

    public WordService() {
        words = FileUtils.read(WORDS_DICTIONARY_PATH);
    }

    public String getRandomWord() {
        return RandomUtils.randomString(words, words.size());
    }
}
