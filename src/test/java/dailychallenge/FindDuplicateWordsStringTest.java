package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindDuplicateWordsStringTest {

    @Test
    public void testFindRepeatedWords() {
        String[] inputArray = {"java", "code", "stream", "java", "loop", "stream"};

        Set<String> repeatedWords = Arrays.stream(inputArray)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println(repeatedWords);

        Set<String> expected = Set.of("java", "stream");
        assertEquals(expected, repeatedWords);
    }
}

