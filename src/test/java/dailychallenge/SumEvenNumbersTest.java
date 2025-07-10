package dailychallenge;

import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

public class SumEvenNumbersTest {

    @Test
    public void printSumOfEvenNumbers() {
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(i -> i % 2 == 0)
                .reduce(Integer::sum)
                .ifPresent(System.out::println); // Esto imprimirá: 30
    }
}
