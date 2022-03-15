package hash;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReportUserTest {
    ReportUser reportUser = new ReportUser();

    @DisplayName("신고된 유저 정지 결과 받기")
    @ParameterizedTest
    @MethodSource("StringArrayProvider")
    void ReportUserTest(String[] id_list, String[] report, int k, int[] expected) {
        assertThat(reportUser.solution(id_list, report, k)).isEqualTo(expected);
    }

    static Stream<Arguments> StringArrayProvider() {
        return Stream.of(
           Arguments.of((Object) new String[] {"muzi", "frodo", "apeach", "neo"},
               (Object) new String[] {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
               2, new int[] {2, 1, 1, 0}),
            Arguments.of((Object) new String[] {"con", "ryan"},
                (Object) new String[] {"ryan con", "ryan con", "ryan con", "ryan con"},
                3, new int[] {0,0})
        );

    }
}

/*
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

        int[] answer = solution.solution(id_list, report, k);
        for (int a : answer) {
            System.out.println(a + "\t"); //  2 1 1 0
        }
    }
 */