package exhaustiveSearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class 소수찾기Test {

    소수찾기 test = new 소수찾기();

    @Test
    void solution() {
    }

    @Test
    @ParameterizedTest
    @CsvSource(value = {"1, false", "2, true", "3, true", "4, false", "25, false"})
    void isPrimeNumberTest(int number, boolean expected) {
        assertThat(test.isPrimeNumber(number)).isEqualTo(expected);
    }

    @Test
    @ParameterizedTest
    @CsvSource(value = {"123"})
    void combineNumberTest(String numbers) {
        List expected = List.of("1", "2", "3", "12", "13", "21", "23", "31", "32");
        assertThat(test.combineNumber(numbers)).isEqualTo(expected);
    }
}