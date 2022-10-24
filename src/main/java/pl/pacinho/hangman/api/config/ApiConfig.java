package pl.pacinho.hangman.api.config;

public class ApiConfig {
    private static final String API_PATH = "/api/v1/";
    public static final String WORD_PATH = API_PATH + "/word";
    public static final String RANDOM_WORD_PATH = WORD_PATH + "/random";
    // ==============
    //    UI
    // ==============
    public static final String UI_HANGMAN_HOME = "/hangman";
    public static final String UI_HANGMAN_CHECK = UI_HANGMAN_HOME + "/check";
    public static final String UI_HANGMAN_NEW_GAME = UI_HANGMAN_HOME + "/new-game";
}
