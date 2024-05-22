package org.example.lab_2.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.lab_2.Utilities.PaymentCalculations;
/**
 * @author Dovydas Girskas 5gr
 */
public class SharedPaymentList {
    private static final SharedPaymentList instance = new SharedPaymentList();
    private ObservableList<Payment> payments = FXCollections.observableArrayList();
    private PaymentCalculations paymentCalculations;
    public static SharedPaymentList getInstance() {
        return instance;
    }

    public ObservableList<Payment> getPayments() {
        return payments;
    }
    public void setPaymentCalculations(PaymentCalculations paymentCalculations) {
        this.paymentCalculations = paymentCalculations;
    }
    public PaymentCalculations getPaymentCalculations() {
        return paymentCalculations;
    }
}
