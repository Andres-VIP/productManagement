package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GroupByFirstLetterTest {

    public Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        Map<Character, List<String>> grouped = new HashMap<>();

        for (String word : words) {
            if (word == null || word.isEmpty()) continue;

            char firstChar = word.charAt(0);
            grouped.computeIfAbsent(firstChar, k -> new ArrayList<>()).add(word);
        }

        return grouped;
    }

    @Test
    public void testGroupByFirstLetter() {
        List<String> input = Arrays.asList("java", "junit", "code", "cat", "stream");

        Map<Character, List<String>> result = groupByFirstLetter(input);

        System.out.println(result);

        Map<Character, List<String>> expected = new HashMap<>();
        expected.put('j', Arrays.asList("java", "junit"));
        expected.put('c', Arrays.asList("code", "cat"));
        expected.put('s', List.of("stream"));
    }
}
