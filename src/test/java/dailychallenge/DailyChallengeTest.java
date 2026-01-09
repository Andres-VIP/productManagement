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
        List<List<Integer>> triangle = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            List<Integer> current = new ArrayList<>(row + 1);
            for (int col = 0; col <= row; col++) {
                if (col == 0 || col == row) current.add(1);
                else current.add(triangle.get(row - 1).get(col - 1) + triangle.get(row - 1).get(col));
            }
            triangle.add(current);
        }
        return triangle;
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

    // 37. Count specific character occurrences
    @Test
    public void testCountSpecificCharOccurrences() {
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

    // 36. Common elements in three lists
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

    // 38. Pangram check
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

    // 40. Longest common prefix
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

    // 41. Generate string prefixes
    @Test
    public void testGenerateAllPrefixes() {
        String input = "abc";
        List<String> result = IntStream.rangeClosed(1, input.length())
                .mapToObj(i -> input.substring(0, i))
                .toList();
        assertEquals(List.of("a", "ab", "abc"), result,
                () -> "Input: \"" + input + "\" | Output (prefixes): " + result);
    }

    // 42. Filter even numbers
    @Test
    public void testRemoveOddNumbers() {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        List<Integer> result = input.stream()
                .filter(i -> i % 2 == 0)
                .toList();
        assertEquals(List.of(2, 4), result,
                () -> "Input: " + input + " | Output (evens only): " + result);
    }

    // 43. FizzBuzz
    @Test
    public void testFizzBuzz() {
        int n = 15;
        List<String> result = IntStream.rangeClosed(1, n)
                .mapToObj(i -> i % 15 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : String.valueOf(i))
                .toList();
        assertEquals(List.of("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"), result,
                () -> "Input: " + n + " | Output (FizzBuzz): " + result);
    }

    // 44. Binary search
    @Test
    public void testBinarySearch(){
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

    // 45. Balanced brackets
    @Test
    public void testBalancedBrackets(){
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

    // 46. Two Sum (índices)
    @Test
    public void testTwoSum(){
        int[] a1={3,2,4}, a2={2,7,11,15};
        assertArrayEquals(new int[]{1,2}, twoSum(a1,6),()->"Input:"+Arrays.toString(a1)+" | t=6");
        assertArrayEquals(new int[]{0,1}, twoSum(a2,9),()->"Input:"+Arrays.toString(a2)+" | t=9");
    }
    private int[] twoSum(int[] a,int t){
        Map<Integer,Integer> m=new HashMap<>();
        for(int i=0;i<a.length;i++){ int c=t-a[i]; if(m.containsKey(c)) return new int[]{m.get(c),i}; m.put(a[i],i); }
        return new int[]{-1,-1};
    }

    // 47. Maximum subarray sum (Kadane)
    @Test
    public void testMaxSubarraySum(){
        int[] a={-2,1,-3,4,-1,2,1,-5,4}, b={-3,-2,-5};
        assertEquals(6, maxSubarraySum(a), ()->"Input:"+Arrays.toString(a)+" | Expected 6 (4,-1,2,1)");
        assertEquals(-2, maxSubarraySum(b), ()->"Input:"+Arrays.toString(b)+" | Expected -2");
    }
    private int maxSubarraySum(int[] a){
        int max=a[0], cur=a[0];
        for(int i=1;i<a.length;i++){ cur=Math.max(a[i],cur+a[i]); max=Math.max(max,cur); }
        return max;
    }

    // 48. Move zeros to end (stable)
    @Test
    public void testMoveZerosToEnd() {
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

    // 49. Run-Length Encoding (RLE)
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
    // 50. Majority element (> n/2) - Boyer-Moore
    @Test
    public void testMajorityElement(){
        int[] a={2,2,1,2,3,2,2};
        assertEquals(2, majority(a), ()->"Input:"+Arrays.toString(a));
    }
    private int majority(int[] a){
        int c=0, cand=0; for(int x:a){ if(c==0)cand=x; c+= (x==cand)?1:-1; } return cand;
    }

    // 51. Best time to buy and sell stock (single transaction)
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

    // 52. Isogram check (no repeated letters)
    @Test
    public void testIsIsogram() {
        String input1 = "lumberjacks";
        boolean result1 = input1.toLowerCase().chars().distinct().count() == input1.length();
        assertTrue(result1,
                () -> "Input: \"" + input1 + "\" | Output (is isogram): " + result1);

        String input2 = "programming";
        boolean result2 = input2.toLowerCase().chars().distinct().count() == input2.length();
        assertFalse(result2,
                () -> "Input: \"" + input2 + "\" | Output (is isogram): " + result2);
    }

    // 53. Monotonic array check
    @Test
    public void testIsMonotonic(){
        int[] a={1,2,2,3}, b={6,5,4,4}, c={1,3,2};
        assertTrue(isMonotonic(a), ()->"Input:"+Arrays.toString(a));
        assertTrue(isMonotonic(b), ()->"Input:"+Arrays.toString(b));
        assertFalse(isMonotonic(c), ()->"Input:"+Arrays.toString(c));
    }
    private boolean isMonotonic(int[] a){
        boolean nonDec=IntStream.range(0,a.length-1).allMatch(i->a[i]<=a[i+1]);
        boolean nonInc=IntStream.range(0,a.length-1).allMatch(i->a[i]>=a[i+1]);
        return nonDec||nonInc;
    }

    // 54. Phone letter combinations (T9 mapping)
    @Test
    public void testGeneratePhoneLetterCombinations() {
        String input = "23";
        List<String> result = generateCombinations(input);
        List<String> expected = List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");

        assertEquals(expected, result,
                () -> "Input: " + input + " | Output: " + result);
    }

    private List<String> generateCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return List.of();

        Map<Character, String> map = Map.of(
                '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
                '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
        );

        List<String> result = new ArrayList<>();
        backtrack(digits, 0, new StringBuilder(), map, result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current,
                           Map<Character, String> map, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map.get(digits.charAt(index));
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(digits, index + 1, current, map, result);
            current.deleteCharAt(current.length() - 1);
        }
    }

    // 55. Can construct word from letters
    @Test
    public void testCanConstructWordFromLetters() {
        List<Character> letters = List.of('a', 'b', 'c', 'd', 'e', 'f', 'g');

        assertTrue(canConstruct("cage", letters),
                "Word: 'cage' | Letters: " + letters + " | Output: true");

        assertFalse(canConstruct("feed", letters),
                "Word: 'feed' | Letters: " + letters + " | Output: false");

        assertTrue(canConstruct("bed", letters),
                "Word: 'bed' | Letters: " + letters + " | Output: true");
    }

    private boolean canConstruct(String word, List<Character> letters) {
        if (word == null || letters == null) return false;

        Map<Character, Long> available = new HashMap<>();
        for (char c : letters) {
            available.put(c, available.getOrDefault(c, 0L) + 1);
        }

        for (char c : word.toCharArray()) {
            if (!available.containsKey(c) || available.get(c) == 0) return false;
            available.put(c, available.get(c) - 1);
        }

        return true;
    }

    // 56. Count sum combinations with repetition
    @Test
    public void testCountSumCombinations() {
        int[] nums = {1, 2, 3};

        assertEquals(4, count(nums, 4));
        assertEquals(5, count(nums, 5));
        assertEquals(1, count(nums, 1));
        assertEquals(1, count(nums, 0));
        assertEquals(0, count(new int[]{}, 3));
    }

    private int count(int[] nums, int t) {
        if (nums == null || nums.length == 0) return 0;
        if (t < 0) return 0;
        if (t == 0) return 1;
        
        int[] dp = new int[t + 1];
        dp[0] = 1;
        for (int n : nums) {
            if (n <= 0) continue;
            for (int s = n; s <= t; s++) dp[s] += dp[s - n];
        }
        return dp[t];
    }

    // 57. Can form word from multiple source words
    @Test
    public void testCanFormTargetFromWords() {
        List<String> source1 = List.of("java", "code");
        String target1 = "evade";
        assertTrue(canForm(source1, target1),
                () -> "Source: " + source1 + " | Target: \"" + target1 + "\" | Output: true");

        List<String> source2 = List.of("hello", "world");
        String target2 = "hold";
        assertTrue(canForm(source2, target2),
                () -> "Source: " + source2 + " | Target: \"" + target2 + "\" | Output: true");

        List<String> source3 = List.of("java");
        String target3 = "javascript";
        assertFalse(canForm(source3, target3),
                () -> "Source: " + source3 + " | Target: \"" + target3 + "\" | Output: false");
    }

    private boolean canForm(List<String> words, String target) {
        Map<Character, Long> available = words.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (char c : target.toCharArray()) {
            if (!available.containsKey(c) || available.get(c) == 0) return false;
            available.put(c, available.get(c) - 1);
        }

        return true;
    }

    // 58. Unique paths in grid (m x n)
    @Test
    public void testCountUniquePathsInGrid() {
        assertEquals(6, uniquePaths(3, 3), "Grid 3x3 → Output: 6");
        assertEquals(28, uniquePaths(3, 7), "Grid 3x7 → Output: 28");
        assertEquals(1, uniquePaths(1, 1), "Grid 1x1 → Output: 1");
    }

    private int uniquePaths(int m, int n) {
        Map<String, Integer> memo = new HashMap<>();
        return countPaths(0, 0, m, n, memo);
    }

    private int countPaths(int i, int j, int m, int n, Map<String, Integer> memo) {
        if (i >= m || j >= n) return 0;
        if (i == m - 1 && j == n - 1) return 1;
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);
        int paths = countPaths(i + 1, j, m, n, memo) + countPaths(i, j + 1, m, n, memo);
        memo.put(key, paths);
        return paths;
    }

    // 59. Generate all subsets (power set)
    @Test
    public void testGenerateAllSubsets() {
        int[] input = {1, 2};
        List<List<Integer>> result = new ArrayList<>();
        backtrack(input, 0, new ArrayList<>(), result);

        List<List<Integer>> expected = List.of(
                List.of(), List.of(1), List.of(2), List.of(1, 2)
        );

        assertTrue(result.containsAll(expected) && expected.containsAll(result),
                () -> "Input: " + Arrays.toString(input) + " | Output: " + result);
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> res) {
        res.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, res);
            current.remove(current.size() - 1);
        }
    }

    // 60. Check if all characters in a string are unique
    @Test
    public void testAllUniqueCharacters() {
        String input1 = "abcdefg";
        boolean result1 = input1.chars().distinct().count() == input1.length();
        assertTrue(result1, () -> "Input: \"" + input1 + "\" | Output (all unique): " + result1);


        String input2 = "hello";
        boolean result2 = input2.chars().distinct().count() == input2.length();
        assertFalse(result2, () -> "Input: \"" + input2 + "\" | Output (all unique): " + result2);
    }

    // 39. Contains duplicate letters in word
    @Test
    public void testHasDuplicateLettersInWord() {
        String input1 = "ChatGPT rocks!";
        boolean result1 = input1.toLowerCase().chars()
                .filter(Character::isLetter)
                .distinct()
                .count() < input1.chars().filter(Character::isLetter).count();
        assertTrue(result1, () -> "Input: \"" + input1 + "\" | Output (has duplicate letters): " + result1);

        String input2 = "Bot";
        boolean result2 = input2.toLowerCase().chars()
                .filter(Character::isLetter)
                .distinct()
                .count() < input2.chars().filter(Character::isLetter).count();
        assertFalse(result2, () -> "Input: \"" + input2 + "\" | Output (has duplicate letters): " + result2);
    }

    // 61. Check if a matrix is symmetric
    @Test
    public void testIsMatrixSymmetric() {
        List<List<Integer>> matrix1 = List.of(
                List.of(1, 2, 3),
                List.of(2, 4, 5),
                List.of(3, 5, 6)
        );
        boolean result1 = IntStream.range(0, matrix1.size())
                .allMatch(i -> IntStream.range(0, matrix1.size())
                        .allMatch(j -> matrix1.get(i).get(j).equals(matrix1.get(j).get(i))));
        assertTrue(result1, () -> "Input: " + matrix1 + " | Output (symmetric): " + result1);

        List<List<Integer>> matrix2 = List.of(
                List.of(1, 0, 3),
                List.of(2, 4, 5),
                List.of(3, 5, 6)
        );
        boolean result2 = IntStream.range(0, matrix2.size())
                .allMatch(i -> IntStream.range(0, matrix2.size())
                        .allMatch(j -> matrix2.get(i).get(j).equals(matrix2.get(j).get(i))));
        assertFalse(result2, () -> "Input: " + matrix2 + " | Output (symmetric): " + result2);
    }

    // 62. Check if string contains only alphabetic letters
    @Test
    public void testIsOnlyAlphabetic() {
        String input1 = "JavaRocks";
        boolean result1 = input1.chars().allMatch(Character::isLetter);
        assertTrue(result1, () -> "Input: \"" + input1 + "\" | Output (only letters): " + result1);

        String input2 = "Code123!";
        boolean result2 = input2.chars().allMatch(Character::isLetter);
        assertFalse(result2, () -> "Input: \"" + input2 + "\" | Output (only letters): " + result2);
    }

    // 63. Check if number is semiprime
    @Test
    public void testIsSemiprime() {
        int input1 = 9; // 3 * 3
        boolean result1 = countPrimeFactors(input1) == 2;
        assertTrue(result1, () -> "Input: " + input1 + " | Output (is semiprime): " + result1);

        int input2 = 8; // 2 * 2 * 2
        boolean result2 = countPrimeFactors(input2) == 2;
        assertFalse(result2, () -> "Input: " + input2 + " | Output (is semiprime): " + result2);
    }

    private int countPrimeFactors(int n) {
        int count = 0;
        for (int i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                count++;
                n /= i;
            }
        }
        if (n > 1) count++; // cuenta el último primo si queda
        return count;
    }

    // 64. Check if string is a valid basic email
    @Test
    public void testIsValidBasicEmail() {
        String input1 = "user@example.com";
        boolean result1 = isValidBasicEmail(input1);
        assertTrue(result1, () -> "Input: \"" + input1 + "\" | Output (valid email): " + result1);

        String input2 = "invalidemail@.com";
        boolean result2 = isValidBasicEmail(input2);
        assertFalse(result2, () -> "Input: \"" + input2 + "\" | Output (valid email): " + result2);
    }

    private boolean isValidBasicEmail(String s) {
        if (s == null) return false;
        int at = s.indexOf('@');
        if (at <= 0) return false;
        if (at != s.lastIndexOf('@')) return false;
        if (at + 1 >= s.length() - 1) return false;
        if (s.charAt(at + 1) == '.') return false;
        int dot = s.indexOf('.', at + 1);
        if (dot <= at + 1) return false;
        if (dot == s.length() - 1) return false;
        return true;
    }

    // 65. Count unique words in a sentence (case-insensitive, ignore punctuation)
    @Test
    public void testCountUniqueWords() {
        String input = "Hello, hello! How are you?";
        long result = Arrays.stream(input.toLowerCase().split("\\W+"))
                .filter(s -> !s.isBlank())
                .distinct()
                .count();
        assertEquals(4, result,
                () -> "Input: \"" + input + "\" | Output (unique word count): " + result);
    }

     // 66. Count vowels in a string
     @Test
     public void testCountVowels() {
         String input1 = "Hello World";
         assertEquals(3, countVowels(input1), () -> "Input: \"" + input1 + "\" | Vowels: " + countVowels(input1));

         String input2 = "BCDFG";
         assertEquals(0, countVowels(input2), () -> "Input: \"" + input2 + "\" | Vowels: " + countVowels(input2));
     }

     private long countVowels(String s) {
         if (s == null) return 0;
         return s.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
     }

     // 67. Check if string is numeric
     @Test
     public void testIsNumeric() {
         assertTrue(isNumeric("123"), () -> "Input: \"123\" | Output: true");
         assertFalse(isNumeric("abc"), () -> "Input: \"abc\" | Output: false");
         assertTrue(isNumeric("0"), () -> "Input: \"0\" | Output: true");
         assertFalse(isNumeric("12a3"), () -> "Input: \"12a3\" | Output: false");
     }

     private boolean isNumeric(String s) {
         return s.chars().allMatch(Character::isDigit);
     }

    // 68. First N Fibonacci numbers
    @Test
    public void testFibonacciLoop() {
        int n = 7;
        List<Integer> expected = List.of(0, 1, 1, 2, 3, 5, 8);
        assertEquals(expected, fibLoop(n));
    }

    private List<Integer> fibLoop(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0, a = 0, b = 1; i < n; i++) { res.add(a); int c = a + b; a = b; b = c; }
        return res;
    }
}