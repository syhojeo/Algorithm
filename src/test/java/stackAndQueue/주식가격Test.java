package stackAndQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class 주식가격Test {

    주식가격 stock = new 주식가격();

    @DisplayName("주식 가격 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int[] prices, int[] expected) {
        assertThat(stock.solution(prices)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
                Arguments.of("테스트1번", (Object) new int[] {1, 2, 3, 2, 3}, (Object) new int[] {4, 3, 1, 1, 0}),
                Arguments.of("테스트2번", (Object) new int[] {1, 2, 3, 1, 1, 1}, (Object) new int[] {5, 2, 1, 2, 1, 0})
        );

    }
}