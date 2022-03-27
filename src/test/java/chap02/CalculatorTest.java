package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    //@Test 어노테이션을 붙인 메서드는 테스트 메서드로 인식한다.
   @Test
    void plus() {
        int result = Calculator.plus(1, 2);

        //인자로 받은 두 값이 동일한지 비교한다.
        //첫 번째 인자는 기대한 값이고, 두 번째인자는 실제 값이다.
        assertEquals(3, result);
        assertEquals(5, Calculator.plus(4, 1));
   }
}
