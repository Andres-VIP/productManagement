package dailychallenge;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstNonRepeatingCharacterTest {
    private Character findFirstNonRepeatingChar(String input) {
        if (input == null || input.isEmpty()) return null;

        Map<Character, Integer> charCount = new LinkedHashMap<>();
        input.chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> charCount.put(c, charCount.getOrDefault(c, 0) + 1));

        Optional<Character> result = charCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();

        return result.orElse(null);
    }

    @Test
    public void testFindFirstNonRepeatingChar() {
        String input = "aabbcddee";
        Character result = findFirstNonRepeatingChar(input);

        // 👇 Esto imprime el resultado en la consola de IntelliJ
        System.out.println("Input: " + input);
        System.out.println("First non-repeating character: " + result);

        assertEquals('c', result);
    }
}
