package chap03;

import java.time.LocalDate;

public class ExpirationDateCalculator {

    public LocalDate calculateExpirationDate(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
