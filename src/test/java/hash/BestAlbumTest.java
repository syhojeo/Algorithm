package hash;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BestAlbumTest {
    BestAlbum bestAlbum = new BestAlbum();
    @DisplayName("베스트 엘범 만들기 문제 테스트")
    @ParameterizedTest
    @MethodSource("StringArrayProvider")
    void solutionTest(String[] genres, int[] plays, int[] expected) {
        assertThat(bestAlbum.solution(genres, plays)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of (
                Arguments.of((Object) new String[] {"classic", "pop", "classic", "classic", "pop"},
                        (Object)new int[] {500, 600, 150, 800, 2500},
                        (Object) new int[] {4,1,3,0} )

        );

    }
}