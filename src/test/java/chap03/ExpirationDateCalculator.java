package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpirationDateCalculator {

    public LocalDate calculateExpirationDate(PayData payData) {
        int addedMonths = payData.getPayAmount() == 100_000 ? 12: payData.getPayAmount()/ 10_000;
        if (payData.getFirstBillingDate() != null) {
            return expirationDateUsingFirstBillingDate(payData, addedMonths);
        } else {
            return payData.getBillingDate().plusMonths(addedMonths);
        }

    }

    private LocalDate expirationDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        if (isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayLenOfCandiMon = lastDayOfMonth(candidateExp);                      // 후보 만료일이 속한 월의 마지막 일자 계산
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();    // 첫 납부일의 일자 계산
            if (dayLenOfCandiMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        } else {
            return candidateExp;
        }
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() != date2.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
