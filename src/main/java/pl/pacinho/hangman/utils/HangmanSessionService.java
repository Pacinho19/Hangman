package pl.pacinho.hangman.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.hangman.model.HangmanDto;
import pl.pacinho.hangman.service.WordService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class HangmanSessionService {

    private final WordService wordService;
    private static final String WORD_PARAM_NAME = "word";
    private static final String HANGMAN_PARAM_NAME = "hangman";

    public String getWord(HttpSession session) {
        String word = (String) session.getAttribute(WORD_PARAM_NAME);
        if (word == null) word = generateWord(session);
        return word;

    }

    private String generateWord(HttpSession session) {
        String word = wordService.getRandomWord();
        session.setAttribute(WORD_PARAM_NAME, word);
        return word;
    }


    public HangmanDto getHangman(HttpSession session) {
        String word = getWord(session);

        HangmanDto hangman = (HangmanDto) session.getAttribute(HANGMAN_PARAM_NAME);
        if (hangman == null) hangman = initHangman(session, word);

        return hangman;
    }

    public HangmanDto checkLetter(HttpSession session, String letter) throws IllegalArgumentException, IllegalStateException {
        letter = letter.toUpperCase();

        String word = getWord(session);
        HangmanDto hangman = getHangman(session);
        if (hangman.isEndGame())
            throw new IllegalStateException("Game is over ! If you want play again, you must start new game!");

        checkJustCheckedLetters(hangman, letter);
        checkLetter(hangman, word, letter);

        checkEndGame(hangman, word);

        return hangman;
    }

    private void checkEndGame(HangmanDto hangman, String word) {
        boolean win = hangman.getWordLetters().stream()
                .noneMatch(s -> s.equals("_"));

        hangman.setEndGame(win || hangman.getLifeCount() == 0);
        hangman.setWin(hangman.isEndGame() && win);
        if(hangman.isEndGame()) hangman.setWord(word);
    }

    private void checkLetter(HangmanDto hangmanDto, String word, String letter) {
        hangmanDto.addCheckedLetter(letter);

        boolean find = false;
        int index = word.indexOf(letter);
        while (index >= 0) {
            find = true;
            hangmanDto.addWordLetters(index, letter);
            index = word.indexOf(letter, index + 1);
        }
        if (!find) hangmanDto.decrementLifeCount();
    }

    private void checkJustCheckedLetters(HangmanDto hangman, String letter) throws IllegalArgumentException {
        if (hangman.getCheckedLetters()
                .contains(letter))
            throw new IllegalArgumentException("Letter " + letter + " just checked!");
    }

    private HangmanDto initHangman(HttpSession session, String word) {
        HangmanDto hangmanDto = new HangmanDto(word);
        session.setAttribute(HANGMAN_PARAM_NAME, hangmanDto);
        return hangmanDto;
    }

    public void newGame(HttpSession session) {
        session.removeAttribute(WORD_PARAM_NAME);
        session.removeAttribute(HANGMAN_PARAM_NAME);
    }
}
