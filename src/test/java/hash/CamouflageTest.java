package hash;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CamouflageTest {

    Camouflage camouflage = new Camouflage();

    @DisplayName("옷입는 경우의 수")
    @ParameterizedTest
    @MethodSource("StringArrayProvider")
    void camouflageTest(String[][] clothes, int expected) {
        assertThat(camouflage.solution(clothes)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
            Arguments.of((Object) new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}, 5),
            Arguments.of((Object) new String[][]{{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}, 3)
        );
    }
}