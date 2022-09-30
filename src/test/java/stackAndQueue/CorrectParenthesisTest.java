package stackAndQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CorrectParenthesisTest {

    CorrectParenthesis correctParenthesis = new CorrectParenthesis();

    @DisplayName("옳바른 괄호 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, String s, boolean expected) {
        assertThat(correctParenthesis.solution(s)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        boolean b = false;
        return Stream.of (
                Arguments.of("테스트1번", "()()", true),
                Arguments.of("테스트2번", "(())()", true),
                Arguments.of("테스트3번", ")()(", false),
                Arguments.of("테스트4번", "(()(", false)
        );

    }
}