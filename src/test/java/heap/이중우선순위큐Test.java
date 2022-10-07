package heap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class 이중우선순위큐Test {
    이중우선순위큐 test = new 이중우선순위큐();

    @DisplayName("이중우선순위큐 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, String[] operations, int expected) {
        assertThat(test.solution(operations)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
                Arguments.of("테스트1번",
                        (Object) new String[] {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"},
                        (Object) new int[] {0, 0}),
                Arguments.of("테스트2번",
                        (Object) new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"},
                        (Object) new int[] {333, -45})
        );

    }
}