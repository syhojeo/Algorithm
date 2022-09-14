package stackAndQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FunctionDevelopmentTest {

    FunctionDevelopment functionDevelopment = new FunctionDevelopment();

    @DisplayName("기능 개발 문제 테스트")
    @ParameterizedTest
    @MethodSource("StringArrayProvider")
    void solutionTest(int[] progresses, int[]speeds, int[] expected) {
        assertThat(functionDevelopment.solution(progresses, speeds)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (                          
                Arguments.of((Object) new int[] {93, 30, 55},
                        (Object)new int[] {1, 30, 5},
                        (Object) new int[] {2, 1}),
                Arguments.of((Object) new int[] {95, 90, 99, 99, 80, 99},
                        (Object)new int[] {1, 1, 1, 1, 1, 1},
                        (Object) new int[] {1, 3, 2})
        );

    }

}