package org.example.lab_2.Model;

import java.io.Serializable;
import java.time.LocalDate;
/**
 * @author Dovydas Girskas 5gr
 */
public class Payment implements Serializable {
    private double howMuchLeftToPay;
    private LocalDate paymentDate;
    private double interestAmount;
    private double principalAmount;
    private double total;
    private String formattedHowMuchLeftToPay;
    private String formattedPaymentDate;
    private String formattedInterestAmount;
    private String formattedPrincipalAmount;
    private String formattedTotal;
    public Payment(double howMuchLeftToPay, LocalDate paymentDate, double interestAmount, double principalAmount, double total) {
        setHowMuchLeftToPay(howMuchLeftToPay);
        setPaymentDate(paymentDate);
        setInterestAmount(interestAmount);
        setPrincipalAmount(principalAmount);
        setTotal(total);
    }

    public double getHowMuchLeftToPay() {
        return howMuchLeftToPay;
    }
    public void setHowMuchLeftToPay(double howMuchLeftToPay) {
        this.howMuchLeftToPay = howMuchLeftToPay;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFormattedHowMuchLeftToPay() {
        return formattedHowMuchLeftToPay;
    }

    public void setFormattedHowMuchLeftToPay(String formattedHowMuchLeftToPay) {
        this.formattedHowMuchLeftToPay = formattedHowMuchLeftToPay;
    }

    public String getFormattedInterestAmount() {
        return formattedInterestAmount;
    }

    public void setFormattedInterestAmount(String formattedInterestAmount) {
        this.formattedInterestAmount = formattedInterestAmount;
    }

    public String getFormattedPaymentDate() {
        return formattedPaymentDate;
    }

    public void setFormattedPaymentDate(String formattedPaymentDate) {
        this.formattedPaymentDate = formattedPaymentDate;
    }

    public String getFormattedPrincipalAmount() {
        return formattedPrincipalAmount;
    }

    public void setFormattedPrincipalAmount(String formattedPrincipalAmount) {
        this.formattedPrincipalAmount = formattedPrincipalAmount;
    }

    public String getFormattedTotal() {
        return formattedTotal;
    }

    public void setFormattedTotal(String formattedTotal) {
        this.formattedTotal = formattedTotal;
    }

    @Override
    public String toString() {
        return "Payment{ howMuchLeftToPay=" + formattedHowMuchLeftToPay + ", date=" + formattedPaymentDate +
                ", interestAmount=" + formattedInterestAmount + ", principalAmount=" + formattedPrincipalAmount + ", total=" + formattedTotal + " }\n";
    }
}
