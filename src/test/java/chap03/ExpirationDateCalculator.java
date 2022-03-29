package chap03;

import java.time.LocalDate;

public class ExpirationDateCalculator {

    public LocalDate calculateExpirationDate(LocalDate billingDate, int payAmount) {
        return billingDate.plusMonths(1);
    }
}
