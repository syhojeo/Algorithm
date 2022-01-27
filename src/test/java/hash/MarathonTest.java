package hash;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


class MarathonTest {
    Marathon marathon = new Marathon();

    @DisplayName("마라톤 참가자 명단과 완주자 명단을 비교하여 완주 실패자 찾는 알고리즘 테스트")
    @ParameterizedTest
    @MethodSource("stringArrayProvider")
    void MarathonTest(String[] participant, String[] completion, String expected) {
        assertThat(marathon.solution(participant, completion)).isEqualTo(expected);
    }
    //문자열 배열을 매개변수로 넣고 싶을때는 MethodSource 사용
    static Stream<Arguments> stringArrayProvider(){
        return Stream.of(
            Arguments.of((Object) new String[]{"leo", "eden", "kiki"}, (Object) new String[]{"eden", "kiki"}, "leo")
        );
    }
}