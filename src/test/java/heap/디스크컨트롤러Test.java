package heap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class 디스크컨트롤러Test {

    디스크컨트롤러 test = new 디스크컨트롤러();

    @DisplayName("디스크컨트롤러 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int[][] jobs, int expected) {
        assertThat(test.solution(jobs)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
                Arguments.of("테스트1번",  (Object) new int[][] {{0, 3}, {1, 9}, {2, 6}}, 9)
        );

    }
}