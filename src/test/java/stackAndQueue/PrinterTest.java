package stackAndQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    Printer printer = new Printer();

    @DisplayName("옳바른 괄호 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int[] priorities, int location, int expected) {
        assertThat(printer.solution(priorities, location)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
                Arguments.of("테스트1번", (Object) new int[] {2, 1, 3, 2}, 2, 1),
                Arguments.of("테스트2번", (Object) new int[] {1, 1, 9, 1, 1, 1}, 0, 5)
        );

    }
}