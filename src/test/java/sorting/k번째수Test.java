package sorting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class k번째수Test {

    k번째수 test = new k번째수();

    @DisplayName("k번째수 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int[] array, int[][] commands, int[] expected) {
        Assertions.assertThat(test.solution(array, commands)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of(
                Arguments.of("테스트1번", (Object) new int[] {1, 5, 2, 6, 3, 7, 4},
                        new int[][] {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}, new int[] {5, 6, 3})
        );
    }
}