package org.example.lab_2.Utilities;
/**
 * @author Dovydas Girskas 5gr
 */
public class FieldInputChecker {
    public static Boolean isDoubleInputGood(String input) {
        try {
            input = input.replace(",", ".");
            return Double.parseDouble(input) != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static Boolean isNumberInputGood(String input) {
        try {
            return Integer.parseInt(input) != 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Boolean isMonthOrYearInputGood(String input) {
        try {
            return Integer.parseInt(input) >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
