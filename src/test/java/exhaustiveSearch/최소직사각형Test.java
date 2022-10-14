package exhaustiveSearch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class 최소직사각형Test {
    최소직사각형 test = new 최소직사각형();

    @DisplayName("최소직사각형 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArraysProvider")
    void solutionTest(String message, int[][] sizes, int expected) {
        Assertions.assertThat(test.solution(sizes)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArraysProvider() {
        return Stream.of(
                Arguments.of("테스트 1번", new int[][] {{60, 50}, {30, 70}, {60, 30}, {80, 40}}, 4000),
                Arguments.of("테스트 2번", new int[][] {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}}, 120),
                Arguments.of("테스트 3번", new int[][] {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}}, 133)
        );
    }
}