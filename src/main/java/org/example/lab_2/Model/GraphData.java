package org.example.lab_2.Model;

import java.time.LocalDate;
/**
 * @author Dovydas Girskas 5gr
 */
public class GraphData {
    private double money;
    private LocalDate date;
    public GraphData(double money, LocalDate date) {
        setMoney(money);
        setDate(date);
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
