package dailychallenge;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class DailyChallengeTest {

    // 1. Anagram check
    @Test
    public void testAreAnagrams() {
        String input1 = "listen";
        String input2 = "silent";
        boolean result = areAnagrams(input1, input2);
        assertTrue(result, () -> "Input: " + input1 + ", " + input2 + " | Output: " + result);
    }

    private boolean areAnagrams(String str1, String str2) {
        return Arrays.equals(
                str1.replaceAll("\\s+", "").toLowerCase().chars().sorted().toArray(),
                str2.replaceAll("\\s+", "").toLowerCase().chars().sorted().toArray()
        );
    }

    // 2. Character count
    @Test
    public void testCountCharacterOccurrence() {
        String input = "Hola Mundo";
        Map<String, Long> result = Arrays.stream(input.toLowerCase().split(""))
                .filter(i -> !" ".equals(i))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        assertEquals(Map.of("h",1L,"o",2L,"l",1L,"a",1L,"m",1L,"u",1L,"n",1L,"d",1L), result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 3. Duplicate words
    @Test
    public void testFindRepeatedWords() {
        String[] inputArray = {"java", "code", "stream", "java", "loop", "stream"};
        Set<String> result = Arrays.stream(inputArray)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        assertEquals(Set.of("java", "stream"), result,
                () -> "Input: " + Arrays.toString(inputArray) + " | Output: " + result);
    }

    // 4. First unique character
    @Test
    public void testFindFirstNonRepeatingChar() {
        String input = "aabbcddee";
        Character result = findFirstNonRepeatingChar(input);
        assertEquals('c', result,
                () -> "Input: " + input + " | First non-repeating character: " + result);
    }

    private Character findFirstNonRepeatingChar(String input) {
        return input.chars()
                .mapToObj(c -> (char)c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // 5. Group words by first letter
    @Test
    public void testGroupByFirstLetter() {
        List<String> input = Arrays.asList("java", "junit", "code", "cat", "stream");
        Map<Character, List<String>> result = input.stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));
        assertEquals(Map.of('j', List.of("java", "junit"), 'c', List.of("code", "cat"), 's', List.of("stream")), result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 6. Digital root
    @Test
    public void testDigitalRootStream() {
        int input = 9875;
        int result = digitalRootStream(input);
        assertEquals(2, result,
                () -> "Input: " + input + " | Output (digital root): " + result);
    }

    private int digitalRootStream(int n) {
        return n < 10 ? n : digitalRootStream(String.valueOf(n).chars().map(c -> c - '0').sum());
    }

    // 7. Remove duplicates
    @Test
    public void testRemoveDuplicates() {
        List<Integer> input = Arrays.asList(1, 2, 3, 2, 1, 4);
        List<Integer> result = input.stream().distinct().toList();
        assertEquals(List.of(1, 2, 3, 4), result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 8. Reverse words in sentence
    @Test
    public void testReverseWordsWithStream() {
        String input = "Hola mundo Java";
        String result = Arrays.stream(input.split(" "))
                .reduce((a, b) -> b + " " + a).orElse("");
        assertEquals("Java mundo Hola", result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 9. Sum of even numbers
    @Test
    public void testSumOfEvenNumbers() {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int result = input.stream()
                .filter(i -> i % 2 == 0)
                .reduce(Integer::sum).orElse(0);
        assertEquals(30, result,
                () -> "Input: " + input + " | Output (sum of evens): " + result);
    }

    // 10. Word count
    @Test
    public void testCountWords() {
        String input = "Java is fun and powerful";
        long result = Stream.of(input.trim().split("\\s+")).count();
        assertEquals(5, result,
                () -> "Input: \"" + input + "\" | Output: " + result);
    }

    // 11. Sum all digits
    @Test
    public void testSumAllDigits() {
        List<Integer> input = Arrays.asList(12, 34);
        int result = input.stream()
                .flatMapToInt(n -> String.valueOf(n).chars().map(c -> c - '0'))
                .sum();
        assertEquals(10, result,
                () -> "Input: " + input + " | Output (sum of digits): " + result);
    }

    // 12. Longest word
    @Test
    public void testFindLongestWord() {
        List<String> input = Arrays.asList("hello", "world", "programming");
        String result = input.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
        assertEquals("programming", result,
                () -> "Input: " + input + " | Output (longest word): " + result);
    }

    // 13. Word frequency
    @Test
    public void testCountWordFrequency() {
        String input = "Java is great and Java is fast";
        Map<String, Long> result = Arrays.stream(input.toLowerCase().split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        assertEquals(Map.of("java",2L,"is",2L,"great",1L,"and",1L,"fast",1L), result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 14. Check sorted list
    @Test
    public void testIsSortedAscending() {
        List<Integer> input = List.of(1, 2, 3, 4);
        boolean result = IntStream.range(0, input.size() - 1)
                .allMatch(i -> input.get(i) <= input.get(i + 1));
        assertTrue(result,
                () -> "Input: " + input + " | Output (is sorted ascending): " + result);
    }

    // 15. Pascal's Triangle
    @Test
    public void testGeneratePascalTriangle() {
        int input = 5;
        List<List<Integer>> result = generatePascalTriangle(input);
        assertEquals(List.of(
                        List.of(1),
                        List.of(1,1),
                        List.of(1,2,1),
                        List.of(1,3,3,1),
                        List.of(1,4,6,4,1)), result,
                () -> "Input: " + input + " | Output (Pascal's Triangle): " + result);
    }

    private List<List<Integer>> generatePascalTriangle(int rows) {
        return IntStream.range(0, rows)
                .mapToObj(row -> IntStream.rangeClosed(0, row)
                        .map(col -> (col == 0 || col == row) ? 1 :
                                generatePascalTriangle(rows-1).get(row-1).get(col-1) +
                                        generatePascalTriangle(rows-1).get(row-1).get(col))
                        .boxed()
                        .toList())
                .toList();
    }

    // 16. Reverse string
    @Test
    public void testReverseString() {
        String input = "hello";
        String result = new StringBuilder(input).reverse().toString();
        assertEquals("olleh", result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 17. Sum first N numbers
    @Test
    public void testSumFirstNNumbers() {
        int input = 5;
        int result = IntStream.rangeClosed(1, input).sum();
        assertEquals(15, result,
                () -> "Input: " + input + " | Output (sum from 1 to " + input + "): " + result);
    }

    // 18. Factorial
    @Test
    public void testCalculateFactorial() {
        int input = 5;
        int result = IntStream.rangeClosed(1, input)
                .reduce(1, (a, b) -> a * b);
        assertEquals(120, result,
                () -> "Input: " + input + " | Output (factorial): " + result);
    }

    // 19. Most frequent number
    @Test
    public void testFindMostFrequentNumber() {
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        int result = input.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
        assertEquals(3, result,
                () -> "Input: " + input + " | Output (most frequent number): " + result);
    }

    // 20. Matrix diagonal sum
    @Test
    public void testDiagonalSum() {
        int[][] input = {{1,2,3},{4,5,6},{7,8,9}};
        int result = IntStream.range(0, input.length)
                .map(i -> input[i][i])
                .sum();
        assertEquals(15, result,
                () -> "Input: " + Arrays.deepToString(input) + " | Output (diagonal sum): " + result);
    }

    // 21. List intersection
    @Test
    public void testFindIntersection() {
        List<Integer> listA = Arrays.asList(1, 2, 3, 4);
        List<Integer> listB = Arrays.asList(3, 4, 5);
        List<Integer> result = listA.stream()
                .filter(listB::contains)
                .toList();
        assertEquals(List.of(3, 4), result,
                () -> "Input listA: " + listA + " | Input listB: " + listB + " | Output (intersection): " + result);
    }

    // 22. Prime check
    @Test
    public void testIsPrime() {
        int input = 7;
        boolean result = isPrime(input);
        assertTrue(result,
                () -> "Input: " + input + " | Output (is prime): " + result);
    }

    private boolean isPrime(int n) {
        return n > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    // 23. Filter non-empty strings
    @Test
    public void testFilterNonEmptyStrings() {
        List<String> input = Arrays.asList("Java", "", null, "Stream", "");
        List<String> result = input.stream()
                .filter(s -> s != null && !s.isEmpty())
                .toList();
        assertEquals(List.of("Java", "Stream"), result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 24. Average of numbers
    @Test
    public void testCalculateAverage() {
        List<Integer> input = List.of(2, 4, 6, 8);
        double result = input.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0);
        assertEquals(5.0, result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 25. First N even numbers
    @Test
    public void testGetFirstNEvenNumbers() {
        int input = 4;
        List<Integer> result = IntStream.iterate(0, n -> n + 2)
                .limit(input)
                .boxed()
                .toList();
        assertEquals(List.of(0, 2, 4, 6), result,
                () -> "Input: " + input + " | Output (first " + input + " even numbers): " + result);
    }

    // 26. Second largest number
    @Test
    public void testFindSecondLargest() {
        List<Integer> input = List.of(10, 5, 20, 8);
        int result = input.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();
        assertEquals(10, result,
                () -> "Input: " + input + " | Output (second largest): " + result);
    }

    // 27. Remove consecutive duplicates
    @Test
    public void testRemoveConsecutiveDuplicates() {
        List<String> input = List.of("a", "a", "b", "b", "c", "a", "a");
        List<String> result = IntStream.range(0, input.size())
                .filter(i -> i == 0 || !input.get(i).equals(input.get(i - 1)))
                .mapToObj(input::get)
                .toList();
        assertEquals(List.of("a", "b", "c", "a"), result,
                () -> "Input: " + input + " | Output: " + result);
    }

    // 28. Acronym generator
    @Test
    public void testConvertToAcronym() {
        List<String> input = List.of("Java", "Virtual", "Machine");
        String result = input.stream()
                .map(w -> "" + w.charAt(0))
                .collect(Collectors.joining());
        assertEquals("JVM", result,
                () -> "Input: " + input + " | Output (acronym): " + result);
    }

    // 29. Missing number
    @Test
    public void testFindMissingNumber() {
        List<Integer> input = List.of(1, 2, 4, 5);
        int result = (input.size() + 1) * (input.size() + 2) / 2 - input.stream().mapToInt(i -> i).sum();
        assertEquals(3, result,
                () -> "Input: " + input + " | Output (missing number): " + result);
    }

    // 30. Check for duplicates
    @Test
    public void testContainsDuplicates() {
        List<String> input = List.of("a", "b", "c", "a");
        boolean result = input.size() != new HashSet<>(input).size();
        assertTrue(result,
                () -> "Input: " + input + " | Output (has duplicates): " + result);
    }

    // 31. Sum map values
    @Test
    public void testSumMapValues() {
        Map<String, Integer> input = Map.of("a", 1, "b", 2, "c", 3);
        int result = input.values().stream()
                .mapToInt(i -> i)
                .sum();
        assertEquals(6, result,
                () -> "Input: " + input + " | Output (sum of values): " + result);
    }

    // 32. Reverse word order
    @Test
    public void testReverseWordOrder() {
        String input = "Java is powerful";
        String result = Stream.of(input.split(" "))
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Collections.reverse(list);
                            return String.join(" ", list);
                        }));
        assertEquals("powerful is Java", result,
                () -> "Input: \"" + input + "\" | Output: \"" + result + "\"");
    }

    // 33. Count character occurrences
    @Test
    public void testCountCharOccurrences() {
        String input = "banana";
        char target = 'a';
        long result = input.chars()
                .filter(c -> c == target)
                .count();
        assertEquals(3, result,
                () -> "Input: \"" + input + "\", Target: '" + target + "' | Output (count): " + result);
    }

    // 34. Palindrome check
    @Test
    public void testIsPalindrome() {
        String input = "radar";
        boolean result = input.equals(new StringBuilder(input).reverse().toString());
        assertTrue(result,
                () -> "Input: \"" + input + "\" | Output (is palindrome): " + result);
    }

    // 35. Merge lists with unique values
    @Test
    public void testMergeListsAndRemoveDuplicates() {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(3, 4, 5);
        List<Integer> result = Stream.concat(list1.stream(), list2.stream())
                .distinct()
                .toList();
        assertEquals(List.of(1, 2, 3, 4, 5), result,
                () -> "Input: " + list1 + " + " + list2 + " | Output (merged unique): " + result);
    }

    // 36. Count elements above threshold
    @Test
    public void testCountGreaterThanThreshold() {
        List<Integer> input = List.of(3, 7, 2, 10, 5);
        int threshold = 5;
        long result = input.stream()
                .filter(i -> i > threshold)
                .count();
        assertEquals(2, result,
                () -> "Input: " + input + ", Threshold: " + threshold + " | Output (count): " + result);
    }

    // 38. Common elements in three lists
    @Test
    public void testFindCommonElements() {
        List<Integer> l1 = List.of(1, 2, 3);
        List<Integer> l2 = List.of(2, 3, 4);
        List<Integer> l3 = List.of(3, 4, 5);
        List<Integer> result = l1.stream()
                .filter(l2::contains)
                .filter(l3::contains)
                .toList();
        assertEquals(List.of(3), result,
                () -> "Input: " + l1 + ", " + l2 + ", " + l3 + " | Output (common): " + result);
    }

    // 40. Pangram check
    @Test
    public void testIsPangram() {
        String input = "The quick brown fox jumps over the lazy dog";
        boolean result = input.toLowerCase().chars()
                .filter(Character::isLetter)
                .distinct()
                .count() == 26;
        assertTrue(result,
                () -> "Input: \"" + input + "\" | Output (is pangram): " + result);
    }

    // 41. Longest common prefix
    @Test
    public void testFindLongestCommonPrefix() {
        List<String> input = List.of("flower", "flow", "flight");
        String result = input.stream()
                .reduce((a, b) -> {
                    while (!b.startsWith(a)) a = a.substring(0, a.length() - 1);
                    return a;
                }).orElse("");
        assertEquals("fl", result,
                () -> "Input: " + input + " | Output (longest common prefix): \"" + result + "\"");
    }

    // 42. Generate string prefixes
    @Test
    public void testGenerateAllPrefixes() {
        String input = "abc";
        List<String> result = IntStream.rangeClosed(1, input.length())
                .mapToObj(i -> input.substring(0, i))
                .toList();
        assertEquals(List.of("a", "ab", "abc"), result,
                () -> "Input: \"" + input + "\" | Output (prefixes): " + result);
    }

    // 43. Filter even numbers
    @Test
    public void testRemoveOddNumbers() {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> result = input.stream()
                .filter(i -> i % 2 == 0)
                .toList();
        assertEquals(List.of(2, 4), result,
                () -> "Input: " + input + " | Output (evens only): " + result);
    }

    // 44. FizzBuzz
    @Test
    public void testFizzBuzz() {
        int n = 15;
        List<String> result = IntStream.rangeClosed(1, n)
                .mapToObj(i -> i % 15 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : String.valueOf(i))
                .toList();
        assertEquals(List.of("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"), result,
                () -> "Input: " + n + " | Output (FizzBuzz): " + result);
    }

    // 45. Binary search
    @Test
    void testBinarySearch(){
        int[] a={1,3,5,7,9};
        assertEquals(2,binarySearch(a,5),()->"Input:"+Arrays.toString(a)+" | t=5");
        assertEquals(-1,binarySearch(a,6),()->"Input:"+Arrays.toString(a)+" | t=6");
    }
    private int binarySearch(int[] a,int t){
        int l=0,r=a.length-1;
        while(l<=r){
            int m=l+(r-l)/2;
            if(a[m]==t) return m;
            if(a[m]<t) l=m+1; else r=m-1;
        }
        return -1;
    }

    // 46. Balanced brackets
    @Test
    void testBalancedBrackets(){
        assertTrue(isBalanced("({[]})"));
        assertFalse(isBalanced("([)]"));
    }
    private boolean isBalanced(String s){
        Deque<Character> st=new ArrayDeque<>();
        Map<Character,Character> m=Map.of(')','(',']','[','}','{');
        for(char c:s.toCharArray())
            if(m.containsValue(c)) st.push(c);
            else if(m.containsKey(c)&&(st.isEmpty()||st.pop()!=m.get(c))) return false;
        return st.isEmpty();
    }

    // 47. Two Sum (índices)
    @Test
    void testTwoSum(){
        int[] a1={3,2,4}, a2={2,7,11,15};
        assertArrayEquals(new int[]{1,2}, twoSum(a1,6),()->"Input:"+Arrays.toString(a1)+" | t=6");
        assertArrayEquals(new int[]{0,1}, twoSum(a2,9),()->"Input:"+Arrays.toString(a2)+" | t=9");
    }
    private int[] twoSum(int[] a,int t){
        Map<Integer,Integer> m=new HashMap<>();
        for(int i=0;i<a.length;i++){ int c=t-a[i]; if(m.containsKey(c)) return new int[]{m.get(c),i}; m.put(a[i],i); }
        return new int[]{-1,-1};
    }

    // 48. Maximum subarray sum (Kadane)
    @Test
    void testMaxSubarraySum(){
        int[] a={-2,1,-3,4,-1,2,1,-5,4}, b={-3,-2,-5};
        assertEquals(6, maxSubarraySum(a), ()->"Input:"+Arrays.toString(a)+" | Expected 6 (4,-1,2,1)");
        assertEquals(-2, maxSubarraySum(b), ()->"Input:"+Arrays.toString(b)+" | Expected -2");
    }
    private int maxSubarraySum(int[] a){
        int max=a[0], cur=a[0];
        for(int i=1;i<a.length;i++){ cur=Math.max(a[i],cur+a[i]); max=Math.max(max,cur); }
        return max;
    }

    // 49. Move zeros to end (stable)
    @Test
    void testMoveZerosToEnd() {
        int[] a = {0, 1, 0, 3, 12};
        int[] b = {1, 3, 12, 0, 0};
        int[] c = {0, 0};
        int[] d = {1, 2, 3};

        assertArrayEquals(b, moveZerosToEnd(a), () -> "Input:" + Arrays.toString(a));
        assertArrayEquals(new int[]{0, 0}, moveZerosToEnd(c), () -> "Input:" + Arrays.toString(c));
        assertArrayEquals(new int[]{1, 2, 3}, moveZerosToEnd(d), () -> "Input:" + Arrays.toString(d));
    }

    private int[] moveZerosToEnd(int[] nums) {
        int[] res = new int[nums.length];
        int i = 0;
        for (int n : nums) if (n != 0) res[i++] = n; // los ceros quedan al final por defecto
        return res;
    }

    // 50. Run-Length Encoding (RLE)
    @Test
    public void testRunLengthEncoding() {
        String in1 = "aaabbcaaaa";
        String in2 = "abcd";
        String in3 = "";
        String in4 = "a";          // edge: un solo caracter
        String in5 = "aabbaa";     // grupos múltiples

        String r1 = rle(in1);
        String r2 = rle(in2);
        String r3 = rle(in3);
        String r4 = rle(in4);
        String r5 = rle(in5);

        assertEquals("a3b2c1a4", r1, () -> "Input: " + in1 + " | Output: " + r1);
        assertEquals("a1b1c1d1", r2, () -> "Input: " + in2 + " | Output: " + r2);
        assertEquals("", r3, () -> "Input: " + in3 + " | Output: " + r3);
        assertEquals("a1", r4, () -> "Input: " + in4 + " | Output: " + r4);
        assertEquals("a2b2a2", r5, () -> "Input: " + in5 + " | Output: " + r5);
    }

    private String rle(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder out = new StringBuilder();
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) count++;
            else { out.append(s.charAt(i - 1)).append(count); count = 1; }
        }
        out.append(s.charAt(s.length() - 1)).append(count);
        return out.toString();
    }
    // 51. Majority element (> n/2) - Boyer-Moore
    @Test
    void testMajorityElement(){
        int[] a={2,2,1,2,3,2,2};
        assertEquals(2, majority(a), ()->"Input:"+Arrays.toString(a));
    }
    private int majority(int[] a){
        int c=0, cand=0; for(int x:a){ if(c==0)cand=x; c+= (x==cand)?1:-1; } return cand;
    }

    // 52. Best time to buy and sell stock (single transaction)
    @Test
    public void testMaxProfitSingleTransaction() {
        int[] input1 = {7, 1, 5, 3, 6, 4};
        int min1 = Integer.MAX_VALUE, profit1 = 0;
        for (int p : input1) { min1 = Math.min(min1, p); profit1 = Math.max(profit1, p - min1); }
        int result1 = profit1;
        assertEquals(5, result1,
                () -> "Input: " + Arrays.toString(input1) + " | Output: " + result1);

        int[] input2 = {7, 6, 4, 3, 1};
        int min2 = Integer.MAX_VALUE, profit2 = 0;
        for (int p : input2) { min2 = Math.min(min2, p); profit2 = Math.max(profit2, p - min2); }
        int result2 = profit2;
        assertEquals(0, result2,
                () -> "Input: " + Arrays.toString(input2) + " | Output: " + result2);
    }
}