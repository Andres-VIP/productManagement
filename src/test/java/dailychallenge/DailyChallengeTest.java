package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Optional;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DailyChallengeTest {

    // 1. Are Anagrams
    public boolean areAnagrams(String str1, String str2) {
        String s1 = str1.replaceAll("\\s+", "").toLowerCase();
        String s2 = str2.replaceAll("\\s+", "").toLowerCase();
        if (s1.length() != s2.length()) return false;
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }

    @Test
    public void testAreAnagrams() {
        String input1 = "listen";
        String input2 = "silent";
        boolean result = areAnagrams(input1, input2);
        System.out.println("Input: " + input1 + ", " + input2);
        System.out.println("Output: " + result);
    }

    // 2. Count character occurrences
    @Test
    public void testCountCharacterOccurrence() {
        String input = "Hola Mundo";
        Map<String, Long> output = Arrays.stream(input.split(""))
                .filter(i -> !" ".equals(i))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Output: " + output);
    }

    // 3. Find duplicate words
    @Test
    public void testFindRepeatedWords() {
        String[] inputArray = {"java", "code", "stream", "java", "loop", "stream"};
        Set<String> repeatedWords = Arrays.stream(inputArray)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println("Output: " + repeatedWords);
        Set<String> expected = Set.of("java", "stream");
        assertEquals(expected, repeatedWords);
    }

    // 4. First non-repeating character
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
        System.out.println("Input: " + input);
        System.out.println("First non-repeating character: " + result);
        assertEquals('c', result);
    }

    // 5. Group by first letter
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
        System.out.println("Output: " + result);
    }

    // 6. Recursive digit sum
    public static int digitalRootStream(int n) {
        if (n < 10) return n;
        int sum = String.valueOf(n)
                .chars()
                .map(c -> c - '0')
                .sum();
        return digitalRootStream(sum);
    }

    @Test
    public void testDigitalRootStream() {
        int result = digitalRootStream(9875);
        System.out.println("Resultado: " + result);
        assertEquals(2, result);
    }

    // 7. Remove duplicates from list
    private List<Integer> removeDuplicates(List<Integer> input) {
        if (input == null) return null;
        return input.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Test
    public void testRemoveDuplicates() {
        List<Integer> input = Arrays.asList(1, 2, 3, 2, 1, 4);
        List<Integer> result = removeDuplicates(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
    }

    // 8. Reverse words in sentence
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
        String result = reverseWordsWithStream(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
        assertEquals("Java mundo Hola", result);
    }

    // 9. Sum of even numbers
    @Test
    public void printSumOfEvenNumbers() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .reduce(Integer::sum)
                .ifPresent(result -> System.out.println("Sum of even numbers: " + result));
    }

    // 10. Count words in sentence
    public static long countWords(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return 0;
        }
        return Stream.of(sentence.trim().split("\\s+")).count();
    }

    @Test
    public void testCountWords() {
        String sentence = "Java is fun and powerful";
        long result = countWords(sentence);
        System.out.println("Input: \"" + sentence + "\"");
        System.out.println("Output: " + result);
        assertEquals(5, result);
    }

    // 11. Sum all digits in list
    public int sumAllDigits(List<Integer> numbers) {
        int total = 0;

        for (int number : numbers) {
            int n = Math.abs(number);
            while (n > 0) {
                total += n % 10;
                n /= 10;
            }
        }

        return total;
    }

    @Test
    public void testSumAllDigits() {
        List<Integer> input = Arrays.asList(12, 34);

        int result = sumAllDigits(input);

        System.out.println("Input: " + input);
        System.out.println("Output (sum of digits): " + result);
    }

    // 12. Find Longest Word With Stream
    @Test
    public void testFindLongestWord() {
        List<String> input = Arrays.asList("hello", "world", "programming");

        String result = input.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null); // In case the list is empty

        System.out.println("Input: " + input);
        System.out.println("Output (longest word): " + result);
    }

    //13. Count Word Frequency Test
    @Test
    public void testCountWordFrequencyWithStream() {
        String input = "Java is great and Java is fast";

        Map<String, Long> output = Arrays.stream(input.toLowerCase().split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }

    // 14. Check if list is sorted in ascending order using Stream
    @Test
    public void testIsSortedAscendingStream() {
        List<Integer> input1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> input2 = Arrays.asList(1, 3, 2);

        boolean result1 = IntStream.range(0, input1.size() - 1)
                .allMatch(i -> input1.get(i) <= input1.get(i + 1));

        boolean result2 = IntStream.range(0, input2.size() - 1)
                .allMatch(i -> input2.get(i) <= input2.get(i + 1));

        System.out.println("Input: " + input1);
        System.out.println("Output (is sorted ascending): " + result1);

        System.out.println("Input: " + input2);
        System.out.println("Output (is sorted ascending): " + result2);
    }

    // 15. Generate Pascal's Triangle
    @Test
    public void testGeneratePascalTriangle() {
        int input = 5;

        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < input; row++) {
            List<Integer> currentRow = new ArrayList<>();

            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) {
                    currentRow.add(1);
                } else {
                    int value = triangle.get(row - 1).get(col - 1) + triangle.get(row - 1).get(col);
                    currentRow.add(value);
                }
            }

            triangle.add(currentRow);
        }

        System.out.println("Input: " + input);
        System.out.println("Output (Pascal's Triangle): " + triangle);
    }

    // 16. Reverse a String
    @Test
    public void testReverseStringWithStream() {
        String input = "hello";

        String output = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return list.stream()
                                    .map(String::valueOf)
                                    .collect(Collectors.joining());
                        }));

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        assertEquals("olleh", output);
    }

    // 17. Sum the first N natural numbers using Stream
    @Test
    public void testSumFirstNNumbersWithStream() {
        int input = 5;

        int output = IntStream.rangeClosed(1, input).sum();

        System.out.println("Input: " + input);
        System.out.println("Output (sum from 1 to " + input + "): " + output);

        assertEquals(15, output);
    }

    // 18. Calculate factorial using Stream
    @Test
    public void testCalculateFactorialWithStream() {
        int input = 5;

        int output = IntStream.rangeClosed(1, input)
                .reduce(1, (a, b) -> a * b);

        System.out.println("Input: " + input);
        System.out.println("Output (factorial): " + output);

        assertEquals(120, output);
    }

    // 19. Find the most frequent number in a list
    @Test
    public void testFindMostFrequentNumberCompact() {
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 3, 3, 4);

        int output = input.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();

        System.out.println("Input: " + input);
        System.out.println("Output (most frequent number): " + output);

        assertEquals(3, output);
    }

    // 20. Find the sum of the main diagonal of a square matrix
    @Test
    public void testDiagonalSumWithStream() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int output = IntStream.range(0, matrix.length)
                .map(i -> matrix[i][i])
                .sum();

        System.out.println("Input: " + Arrays.deepToString(matrix));
        System.out.println("Output (diagonal sum): " + output);

        assertEquals(15, output);
    }

    @Test // 21. Calculate the intersection of two lists
    public void testFindIntersectionBetweenLists() {
        List<Integer> listA = Arrays.asList(1, 2, 3, 4), listB = Arrays.asList(3, 4, 5);
        List<Integer> result = listA.stream().filter(listB::contains).distinct().toList();
        System.out.println("Input listA: " + listA + " | Input listB: " + listB + " | Output (intersection): " + result);
        assertEquals(List.of(3, 4), result);
    }

    @Test // 22. Check if a number is prime
    public void testIsPrime() {
        int input = 7;
        boolean result = input > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(input)).noneMatch(n -> input % n == 0);
        System.out.printf("Input: %d | Output (is prime): %b%n", input, result);
        assertTrue(result);
    }
    @Test // 23. Remove null or empty values from a list
    public void testRemoveNullOrEmptyStrings() {
        List<String> input = Arrays.asList("Java", "", null, "Stream", "");
        List<String> output = input.stream().filter(s -> s != null && !s.isEmpty()).toList();
        System.out.printf("Input: %s | Output: %s%n", input, output);
        assertEquals(List.of("Java", "Stream"), output);
    }

    @Test // 24. Calculate average of a list of integers
    public void testCalculateAverage() {
        List<Integer> input = List.of(2, 4, 6, 8);
        double output = input.stream().mapToInt(i -> i).average().orElse(0);
        System.out.printf("Input: %s | Output: %.1f%n", input, output);
        assertEquals(5.0, output);
    }

    @Test // 25. Get first n even numbers
    public void testGetFirstNEvenNumbers() {
        int input = 4;
        List<Integer> result = IntStream.iterate(0, n -> n + 2).limit(input).boxed().toList();
        System.out.printf("Input: %d | Output (first %d even numbers): %s%n", input, input, result);
        assertEquals(List.of(0, 2, 4, 6), result);
    }

    @Test // 26. Find the second largest number
    public void testFindSecondLargest() {
        List<Integer> input = List.of(10, 5, 20, 8);
        int result = input.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElseThrow();
        System.out.printf("Input: %s | Output (second largest): %d%n", input, result);
        assertEquals(10, result);
    }

    @Test // 27. Remove consecutive duplicates from a list
    public void testRemoveConsecutiveDuplicates() {
        List<String> input = List.of("a", "a", "b", "b", "c", "a", "a");
        List<String> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++)
            if (i == 0 || !input.get(i).equals(input.get(i - 1))) result.add(input.get(i));
        System.out.printf("Input: %s | Output: %s%n", input, result);
        assertEquals(List.of("a", "b", "c", "a"), result);
    }

    @Test // 28. Convert a list of words into an acronym
    public void testConvertToAcronym() {
        var input = List.of("Java", "Virtual", "Machine");
        var result = input.stream().map(w -> "" + w.charAt(0)).collect(Collectors.joining());
        System.out.printf("Input: %s | Output (acronym): %s%n", input, result);
        assertEquals("JVM", result);
    }

    @Test // 29. Find missing number in sequence
    public void testFindMissingNumber() {
        var input = List.of(1, 2, 4, 5);
        int missing = ((input.size() + 1) * (input.size() + 2) / 2) - input.stream().mapToInt(i -> i).sum();
        System.out.printf("Input: %s | Output (missing number): %d%n", input, missing);
        assertEquals(3, missing);
    }

    @Test // 30. Check if list contains duplicates
    public void testContainsDuplicates() {
        List<String> input = List.of("a", "b", "c", "a");
        boolean hasDuplicates = input.size() != new HashSet<>(input).size();
        System.out.printf("Input: %s | Output (has duplicates): %b%n", input, hasDuplicates);
        assertTrue(hasDuplicates);
    }

    @Test // 31. Sum all values in a map
    public void testSumMapValues() {
        Map<String, Integer> input = Map.of("a", 1, "b", 2, "c", 3);
        int result = input.values().stream().mapToInt(i -> i).sum();
        System.out.printf("Input: %s | Output (sum of values): %d%n", input, result);
        assertEquals(6, result);
    }

    @Test // 32. Reverse word order in a sentence
    public void testReverseWordOrder() {
        String input = "Java is powerful";
        String result = Stream.of(input.split(" "))
                .collect(Collectors.collectingAndThen(Collectors.toList(), l -> { Collections.reverse(l); return String.join(" ", l); }));
        System.out.printf("Input: \"%s\" | Output: \"%s\"%n", input, result);
        assertEquals("powerful is Java", result);
    }

    @Test // 33. Count occurrences of a specific character
    public void testCountCharOccurrences() {
        String input = "banana";
        char target = 'a';
        long count = input.chars().filter(c -> c == target).count();
        System.out.printf("Input: \"%s\", Target: '%c' | Output (count): %d%n", input, target, count);
        assertEquals(3, count);
    }

    @Test // 34. Check if a word is a palindrome
    public void testIsPalindrome() {
        String input = "radar";
        boolean result = input.equals(new StringBuilder(input).reverse().toString());
        System.out.printf("Input: \"%s\" | Output (is palindrome): %b%n", input, result);
        assertTrue(result);
    }
}