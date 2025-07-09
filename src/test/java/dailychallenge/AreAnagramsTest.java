package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AreAnagramsTest {

    public boolean areAnagrams(String str1, String str2) {
        String s1 = str1.replaceAll("\\s+", "").toLowerCase();
        String s2 = str2.replaceAll("\\s+", "").toLowerCase();

        if (s1.length() != s2.length()) {
            return false;
        }

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
}
