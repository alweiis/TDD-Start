package chap03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 서비스 만료일 계산기 TDD
 * - 구현 요구사항
 * 1. 서비스를 사용하려면 매달 1만 원을 선불로 납부한다. 납부일 기준으로 한 달 뒤가 서비스 만료일이 된다.
 * 2. 2개월 이상 요금을 납부할 수 있다.
 * 3. 10만 원을 납부하면 서비스를 1년 제공한다.
 */
public class ExpirationDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        LocalDate billingDate = LocalDate.of(2022, 3, 1);
        int payAmount = 10_000;

        ExpirationDateCalculator cal = new ExpirationDateCalculator();
        LocalDate expirationDate = cal.calculateExpirationDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2022, 4, 1), expirationDate);

        LocalDate billingDate2 = LocalDate.of(2022, 5, 5);
        int payAmount2 = 10_000;

        ExpirationDateCalculator cal2 = new ExpirationDateCalculator();
        LocalDate expirationDate2 = cal.calculateExpirationDate(billingDate2, payAmount2);

        assertEquals(LocalDate.of(2022, 6, 5), expirationDate2);
    }
}
