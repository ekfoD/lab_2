package org.example.lab_2.Utilities;

import org.example.lab_2.Model.Payment;
import org.example.lab_2.Model.UserInput;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author Dovydas Girskas 5gr
 */
public class AnnuityPaymentCalculations extends PaymentCalculations {
    public AnnuityPaymentCalculations(UserInput userInput) {
        super(userInput);
    }
    @Override
    public List<Payment> fillList(UserInput input) {
        Payment payment;                                            // sukuriam payment
        int months = input.getMonths() + (input.getYears() * 12);   // gaunam kiek menesiu
        LocalDate localDate;                                        // gaunam siandienos data
        double monthlyRate = input.getYearlyInterestRate() / 12 / 100;
        double moneySum = input.getMoneySum();
        double principalAmount;
        double interestAmount;
        double total;
        double monthlyPayment = moneySum * monthlyRate / (1 - Math.pow(1 + monthlyRate, -months));


        for (int i = 1; i <= months; i++) {
            interestAmount = moneySum * monthlyRate;
            principalAmount = monthlyPayment - interestAmount;
            total = interestAmount + principalAmount;
            localDate = LocalDate.now().plusMonths(i);

            payment = super.formatPayment(moneySum, localDate, interestAmount, principalAmount, total);
            super.payments.add(payment);

            moneySum -= principalAmount;
        }

        return super.payments;
    }
}
