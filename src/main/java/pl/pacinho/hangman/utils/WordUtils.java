package pl.pacinho.hangman.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordUtils {
    public static List<String> emptyWordList(int length) {
        return IntStream.range(0, length)
                .boxed()
                .map(i -> "_")
                .collect(Collectors.toList());
    }
}
