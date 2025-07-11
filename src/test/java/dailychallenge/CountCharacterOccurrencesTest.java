package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountCharacterOccurrencesTest {

    @Test
    public void testCountCharacterOccurrence() {
        String input = "Hola Mundo";

        Map<String, Long> output = Arrays.stream(input.split(""))
                .filter(i -> !" ".equals(i))
                .map(i -> i.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Output: " + output);
    }
}
