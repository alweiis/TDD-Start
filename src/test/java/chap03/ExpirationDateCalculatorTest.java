package chap03;

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
        assertExpirationDate(PayData.builder()
                                .billingDate(LocalDate.of(2019, 3, 1))
                                .payAmount(10_000)
                                .build(),
                                LocalDate.of(2019, 4, 1));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpirationDate(PayData.builder()
                                .billingDate(LocalDate.of(2019, 1, 31))
                                .payAmount(10_000)
                                .build(),
                                LocalDate.of(2019, 2, 28));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                            .firstBillingDate(LocalDate.of(2019, 1, 31))
                            .billingDate(LocalDate.of(2019, 2, 28))
                            .payAmount(10_000)
                            .build();

        assertExpirationDate(payData, LocalDate.of(2019, 3, 31));

        PayData payData2 = PayData.builder()
                            .firstBillingDate(LocalDate.of(2019, 1, 30))
                            .billingDate(LocalDate.of(2019, 2, 28))
                            .payAmount(10_000)
                            .build();

        assertExpirationDate(payData2, LocalDate.of(2019, 3, 30));

        PayData payData3 = PayData.builder()
                            .firstBillingDate(LocalDate.of(2019, 5, 31))
                            .billingDate(LocalDate.of(2019, 6, 30))
                            .payAmount(10_000)
                            .build();

        assertExpirationDate(payData3, LocalDate.of(2019, 7, 31));

    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpirationDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1)
        );

        assertExpirationDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpirationDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30)
        );

        assertExpirationDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 3, 31))
                        .billingDate(LocalDate.of(2019, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2019, 7, 31)
        );
    }

    private void assertExpirationDate(PayData payData, LocalDate expectedExpirationDate) {
        ExpirationDateCalculator cal = new ExpirationDateCalculator();
        LocalDate realExpirationDate = cal.calculateExpirationDate(payData);
        assertEquals(expectedExpirationDate, realExpirationDate);
    }
}
