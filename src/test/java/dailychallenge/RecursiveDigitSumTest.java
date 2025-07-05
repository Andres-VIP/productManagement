package dailychallenge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecursiveDigitSumTest {

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
}