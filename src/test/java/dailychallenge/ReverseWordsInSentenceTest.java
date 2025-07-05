package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseWordsInSentenceTest {

    public String reverseWordsWithStream(String sentence) {
        return Arrays.stream(sentence.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return String.join(" ", list);
                        }));
    }

    @Test
    public void testReverseWordsWithStream() {
        String input = "Hola mundo Java";
        String expected = "Java mundo Hola";

        String result = reverseWordsWithStream(input);

        System.out.println("Input: " + input);
        System.out.println("Output: " + result);

        assertEquals(expected, result);
    }
}
