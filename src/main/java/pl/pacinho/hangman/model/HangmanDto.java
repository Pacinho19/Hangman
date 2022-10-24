package pl.pacinho.hangman.model;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.hangman.utils.WordUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class HangmanDto {

    public final int MAX_LIFE = 10;
    private List<String> checkedLetters;
    private List<String> wordLetters;
    private int lifeCount;
    @Setter
    private boolean endGame;
    @Setter
    private boolean win;
    @Setter
    private String word;

    public HangmanDto(String word) {
        checkedLetters = new ArrayList<>();
        wordLetters = WordUtils.emptyWordList(word.length());
        win = false;
        endGame = false;
        lifeCount = 10;
    }

    public void addCheckedLetter(String letter) {
        if (letter == null) return;
        letter = letter.toUpperCase();
        checkedLetters.add(letter);
    }

    public void addWordLetters(int pos, String letter) {
        if (letter == null) return;
        letter = letter.toUpperCase();
        wordLetters.set(pos, letter);
    }

    public void decrementLifeCount() {
        lifeCount--;
    }

}
