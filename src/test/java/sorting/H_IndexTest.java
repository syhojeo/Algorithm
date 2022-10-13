package sorting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class H_IndexTest {

    H_Index test = new H_Index();

    @DisplayName("H_Index 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int[] citations, int expected) {
        Assertions.assertThat(test.solution(citations)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of(
                // n - index >= h
                Arguments.of("테스트1번", (Object) new int[] {3, 0, 6, 1, 5}, 3),
                Arguments.of("테스트2번", (Object) new int[] {0}, 0),
                Arguments.of("테스트3번", (Object) new int[] {1}, 1),
                Arguments.of("테스트4번", (Object) new int[] {10000}, 1),
                Arguments.of("테스트5번", (Object) new int[] {0, 1, 2, 3, 4}, 2),
                Arguments.of("테스트6번", (Object) new int[] {10000, 9999, 9998, 9997, 9996}, 5)
        );
    }
}