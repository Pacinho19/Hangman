package pl.pacinho.hangman.service;

import org.springframework.stereotype.Service;
import pl.pacinho.hangman.utils.FileUtils;
import pl.pacinho.hangman.utils.RandomUtils;

import java.util.List;

@Service
public class WordService {

    public final String FILE_NAME = "words.txt";
    private final String WORDS_DICTIONARY_PATH = "src\\main\\resources\\" + FILE_NAME;
    private List<String> words;

    public WordService() {
//        words = FileUtils.read(WORDS_DICTIONARY_PATH);
        words = FileUtils.readFromResources(FILE_NAME);
    }

    public String getRandomWord() {
        return RandomUtils.randomString(words, words.size());
    }
}