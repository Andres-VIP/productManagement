package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicatesFromListTest {

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
}
