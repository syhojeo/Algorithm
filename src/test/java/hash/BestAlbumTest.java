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

    static Stream<Arguments> StringArrayProvider() { //d b a c
        return Stream.of (                          //0  1   2   3   4    5    6    7    8   9  10  11  12
                Arguments.of((Object) new String[] {"c","a","b","a","a", "b", "b", "b", "b","c","c","c","d"},
                        //                0   1  2   3    4    5   6   7  8 9 10 11  12
                        (Object)new int[] {1,500,9, 600, 501, 800,500,300,2,2,1,2,100000},
                        (Object) new int[] {12,5,6,3,4,9,11} )

        );

    }
}