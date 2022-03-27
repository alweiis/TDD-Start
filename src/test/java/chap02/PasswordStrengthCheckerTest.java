package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthCheckerTest {

    private PasswordStrengthChecker checker = new PasswordStrengthChecker();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = this.checker.checker(password);
        assertEquals(expStr, result);
    }

    @Test
    void 모든_규칙을_통과하는_경우() {
        assertStrength("abc123AB", PasswordStrength.STRONG);
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
    }

    @Test
    void 길이만_8글자_미만이고_나머지_조건은_충족하는_경우() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    void 숫자를_포함하지_않고_나머지_조건은_충족하는_경우() {
        assertStrength("ABCDabcd!@", PasswordStrength.NORMAL);
    }

    @Test
    void 입력값이_없는_경우() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    void 입력값이_빈_문자열인_경우() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    void 대문자를_포함하지_않고_나머지_조건을_충족하는_경우() {
        assertStrength("abcd1234", PasswordStrength.NORMAL);
    }

    @Test
    void 길이가_8글자_이상인_조건만_충족하는_경우() {
        assertStrength("abcdefgh", PasswordStrength.WEAK);
    }

    @Test
    void 숫자_포함_조건만_충족하는_경우() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    @Test
    void 대문자_포함_조간만_충족하는_경우() {
        assertStrength("ABCDE", PasswordStrength.WEAK);
    }
}
