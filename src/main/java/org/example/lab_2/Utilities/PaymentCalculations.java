package org.example.lab_2.Utilities;

import org.example.lab_2.Model.DateInterval;
import org.example.lab_2.Model.Payment;
import org.example.lab_2.Model.UserInput;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author Dovydas Girskas 5gr
 */
public class PaymentCalculations {
    protected List<Payment> payments;
    private List<Payment> tempPayments;
    private UserInput userInput;
    public PaymentCalculations(UserInput userInput) {
        this.userInput = userInput;
        payments = new ArrayList<Payment>();
        tempPayments = new ArrayList<Payment>();
    }
    public List<Payment> fillList(UserInput input) {
        return payments;
    }
    public List<Payment> filterList(DateInterval filterInterval) {
        tempPayments.clear();

        for (Payment payment : payments) {
            if (payment.getPaymentDate().compareTo(filterInterval.getDateBegin()) >= 0 && payment.getPaymentDate().compareTo(filterInterval.getDateEnd()) <= 0)
                tempPayments.add(payment);
        }
        return tempPayments;
    }
    public List<Payment> addDelayToList(DateInterval delayInterval) {
        // i sita delayju pritaiko ir returnina
        tempPayments.clear();
        tempPayments.addAll(payments);

        // temp
        Payment payment;
        double howMuchLeftToPay = 0;

        // nuo kurio payment prasideda delay, kiek menesiu truks delay, kiek menesiu truks paskolos grazinimas
        int delayStartingPaymentIndex = 0,
                delayEndingPaymentIndex = 0,
                morgageMonths = userInput.getYears() * 12 + userInput.getMonths();

        // gaunam nuo kurio men prasideda delay ir nuo kurio baigiasi
        for (int i = 0; i < morgageMonths; ++i) {
            if (payments.get(i).getPaymentDate().isBefore(delayInterval.getDateBegin()) ||
                payments.get(i).getPaymentDate().isEqual(delayInterval.getDateBegin())) {
                delayStartingPaymentIndex = i + 1;
            }
            else if (payments.get(i).getPaymentDate().isAfter(delayInterval.getDateEnd())) {
                delayEndingPaymentIndex = i + 1;
                break;
            }
        }

        // jei bloga ivede tiesiog grazinam kaip buvo
        if (delayEndingPaymentIndex == 0 || delayStartingPaymentIndex == 0)
            return payments;

        // issisaugom kad pratestume
        howMuchLeftToPay = payments.get(delayStartingPaymentIndex).getHowMuchLeftToPay();

        // nuo kurio prasideda tuos pakeisim
        for (int i = delayStartingPaymentIndex; i < delayEndingPaymentIndex; ++i) {
            payment = payments.get(i);
            payment = formatPayment(howMuchLeftToPay, payment.getPaymentDate(), payment.getInterestAmount(),
                    0, payment.getInterestAmount());
            tempPayments.set(i, payment);
        }

        // kurie eina po to kai pasibaigia atostogos irgi pakeisim
        for (int i = delayEndingPaymentIndex; i < morgageMonths; ++i) {
            payment = payments.get(i);
            howMuchLeftToPay = payment.getHowMuchLeftToPay();
            payment.setPrincipalAmount(payment.getPrincipalAmount() + payment.getPrincipalAmount() * (Math.pow(1 + (userInput.getYearlyInterestRate() / 12 / 100), delayEndingPaymentIndex - delayStartingPaymentIndex)));
            payment.setTotal(payment.getInterestAmount() + payment.getPrincipalAmount());
            payment = formatPayment(howMuchLeftToPay, payment.getPaymentDate(), payment.getInterestAmount(),
                    payment.getPrincipalAmount(), payment.getTotal());
            tempPayments.set(i, payment);
        }
        return tempPayments;
    }
    public Payment formatPayment(double moneySum, LocalDate date, double interestAmount, double principalAmount, double total) {
        Payment payment = new Payment(moneySum, date, interestAmount, principalAmount, total);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        payment.setFormattedPaymentDate(date.format(dateFormat));

        payment.setFormattedHowMuchLeftToPay(String.format("%.2f", moneySum));
        payment.setFormattedInterestAmount(String.format("%.2f", interestAmount));
        payment.setFormattedPrincipalAmount(String.format("%.2f", principalAmount));
        payment.setFormattedTotal(String.format("%.2f", total));

        return payment;
    }
    public List<Payment> getPayments() {
        return payments;
    }
}
