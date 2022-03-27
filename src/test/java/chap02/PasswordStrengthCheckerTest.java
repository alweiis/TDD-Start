package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthCheckerTest {

    @Test
    void 모든_규칙을_통과하는_경우() {
        PasswordStrengthChecker checker = new PasswordStrengthChecker();
        PasswordStrength result = checker.checker("ab12!@AB");
        assertEquals(PasswordStrength.STRONG, result);

        PasswordStrength result2 = checker.checker("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }

    @Test
    void 길이만_8글자_미만이고_나머지_조건은_충족하는_경우() {
        PasswordStrengthChecker checker = new PasswordStrengthChecker();
        PasswordStrength result = checker.checker("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);

        PasswordStrength result2 = checker.checker("Ab12!c");
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @Test
    void 숫자를_포함하지_않고_나머지_조건은_충족하는_경우() {
        PasswordStrengthChecker checker = new PasswordStrengthChecker();
        PasswordStrength result = checker.checker("ab!@ABqwer");
        assertEquals(PasswordStrength.NORMAL, result);
    }
}
