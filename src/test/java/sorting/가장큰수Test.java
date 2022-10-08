package sorting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class 가장큰수Test {

    가장큰수 test = new 가장큰수();

    @DisplayName("가장 큰수 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int[] numbers, String expected) {
        Assertions.assertThat(test.solution(numbers)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of(
                Arguments.of("테스트1번", (Object) new int[] {6, 10, 2}, "6210"),
                Arguments.of("테스트2번", (Object) new int[] {3, 30, 34, 5, 9}, "9534330"),
                Arguments.of("테스트3번", (Object) new int[] {1, 10, 100, 1000}, "1101001000"),
                Arguments.of("테스트4번", (Object) new int[] {232,23}, "23232"),
                Arguments.of("테스트5번", (Object) new int[] {212,21}, "21221"),
                Arguments.of("테스트6번", (Object) new int[] {70,0,0,0,0}, "700000"),
                Arguments.of("테스트7번", (Object) new int[] {0,0,0,0}, "0"),
                Arguments.of("테스트8번", (Object) new int[] {979, 97, 978, 81, 818, 817}, "9799797881881817"),
                Arguments.of("테스트8번", (Object) new int[] {9, 998}, "9998")
        );
    }
}