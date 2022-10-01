package stackAndQueue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TruckPassBridgeTest {

    TruckPassBridge truckPassBridge = new TruckPassBridge();

    @DisplayName("다리를 지나는 트럭 문제 테스트")
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("StringArrayProvider")
    void solutionTest(String message, int bridge_length, int weight, int[] truck_weight, int expected) {
        assertThat(truckPassBridge.solution(bridge_length, weight, truck_weight)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
                Arguments.of("테스트1번", 2, 10, (Object) new int[] {7,4,5,6}, 8),
                Arguments.of("테스트2번", 100, 100, (Object) new int[] {10}, 101),
                Arguments.of("테스트3번", 100, 100, (Object) new int[] {10,10,10,10,10,10,10,10,10,10}, 110),
                Arguments.of("테스트4번", 2, 6, (Object) new int[] {5}, 3),
                Arguments.of("테스트5번", 2, 6, (Object) new int[] {5,4}, 5)
        );

    }
}