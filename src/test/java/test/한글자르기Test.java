package test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class 한글자르기Test {

    한글자르기 test = new 한글자르기();

    @Test
    public void testMethod() {

        String inputString = "가나다";
        Assertions.assertThat(test.test(inputString)).isEqualTo("가나");
    }

}