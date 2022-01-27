package hash;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PhoneBookTest {
    PhoneBook phoneBook = new PhoneBook();

    @DisplayName("전화번호 접두사 중복 테스트")
    @ParameterizedTest
    @MethodSource("StringArrayProvider")
    void PhoneBookTest(String[] phone_book,boolean expected) {
        assertThat(phoneBook.solution(phone_book)).isEqualTo(expected);
    }
    //문자열 배열을 매개변수로 넣고 싶을때는 MethodSource 사용
    static Stream<Arguments> StringArrayProvider(){
        return Stream.of(
            Arguments.of((Object) new String[] {"123"}, true)
        );
    }
}
