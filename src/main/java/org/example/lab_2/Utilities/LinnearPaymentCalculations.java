package org.example.lab_2.Utilities;

import org.example.lab_2.Model.Payment;
import org.example.lab_2.Model.UserInput;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Dovydas Girskas 5gr
 */
public class LinnearPaymentCalculations extends PaymentCalculations {
    public LinnearPaymentCalculations(UserInput userInput) {
        super(userInput);
    }
    @Override
    public List<Payment> fillList(UserInput input) {
        Payment payment;                                            // sukuriam payment
        int months = input.getMonths() + (input.getYears() * 12);   // gaunam kiek menesiu
        LocalDate localDate = LocalDate.now();                      // gaunam siandienos data

        double moneySum = input.getMoneySum();
        double total;
        double interestAmount;
        double principalAmount = moneySum / months;
        double monthlyInterestRate = input.getYearlyInterestRate() / 12.0 / 100.0;

        for (int i = 1; i <= months; ++i) {
            localDate = LocalDate.now().plusMonths(i);
            interestAmount = (float)moneySum * monthlyInterestRate;
            total = interestAmount + principalAmount;

            // parsing from LocalDate to Date
            payment = super.formatPayment(moneySum, localDate, interestAmount, principalAmount, total);
            super.payments.add(payment);

            moneySum -= principalAmount;
        }

        return super.payments;
    }
}
