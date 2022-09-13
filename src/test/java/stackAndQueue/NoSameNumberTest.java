package stackAndQueue;

import hash.BestAlbum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NoSameNumberTest {

    NoSameNumber noSameNumber = new NoSameNumber();

    @DisplayName("같은 숫자는 싫어 문제 테스트")
    @ParameterizedTest
    @MethodSource("StringArrayProvider")
    void solutionTest(int[] arr, int[] expected) {
        assertThat(noSameNumber.solution(arr)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() { //
        return Stream.of (                          //
                Arguments.of((Object) new int[] {1, 1, 3, 3, 0, 1, 1},
                        (Object)new int[] {1, 3, 0, 1}),
                Arguments.of((Object) new int[] {4, 4, 4, 3, 3},
                        (Object)new int[] {4, 3}),
                Arguments.of((Object) new int[] {4},
                        (Object)new int[] {4})
        );

    }
}