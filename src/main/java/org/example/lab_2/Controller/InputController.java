package org.example.lab_2.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.example.lab_2.Model.GraphType;
import org.example.lab_2.Model.Payment;
import org.example.lab_2.Model.SharedPaymentList;
import org.example.lab_2.Model.UserInput;
import org.example.lab_2.Utilities.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Dovydas Girskas 5gr
 */

public class InputController {
    private SceneController sceneController;
    private UserInput userInput;
    private PaymentCalculations paymentCalculations;
    private List<Payment> payments;

    @FXML
    private TextField annualInterest_input;
    @FXML
    private ChoiceBox<String> graph_selection;

    @FXML
    private TextField money_input;

    @FXML
    private TextField month_input;

    @FXML
    private TextField year_input;

    @FXML
    private void initialize() {
        sceneController = new SceneController();
        //        for (GraphType value : GraphType.values()) {      // enum values ne visai graziai suformuotos://
        //            graph_selection.getItems().add(value.toString());
        //        }
        ObservableList<String> options = FXCollections.observableArrayList(
                "Anuiteto",
                "Linijinis"
        );
        graph_selection.setItems(options);
    }

    @FXML
    void loadOutputView(ActionEvent event) throws IOException {
        // ar geras input
        if(!checkIfUserInputIsCorrect()) {
            return;
        }

        // idedam i UserInput modeli
        formatUserInput();

        // nustatom ka naudosim
        if (userInput.getGraphType() == GraphType.Anuiteto)
            paymentCalculations = new AnnuityPaymentCalculations(userInput);
        else
            paymentCalculations = new LinnearPaymentCalculations(userInput);

        // apskaiciuojam payment list
        payments = paymentCalculations.fillList(userInput);

        // persiunciam i output screen
        // Add payments to data model
        SharedPaymentList.getInstance().setPaymentCalculations(paymentCalculations);
        SharedPaymentList.getInstance().getPayments().setAll(payments);
        sceneController.switchToOutputScene(event);

    }

    private boolean checkIfUserInputIsCorrect() {
        boolean allFieldsFilled =
                !money_input.getText().isEmpty() &&
                        !month_input.getText().isEmpty() &&
                        !year_input.getText().isEmpty() &&
                        !annualInterest_input.getText().isEmpty() &&
                                graph_selection.getValue() != null;
        if (!allFieldsFilled)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Not all fields are filled!");
            alert.show();
            return false;
        }
        else if (!FieldInputChecker.isDoubleInputGood(money_input.getText()) ||
                !FieldInputChecker.isDoubleInputGood(annualInterest_input.getText()) ||
                !FieldInputChecker.isMonthOrYearInputGood(year_input.getText()) ||
                !FieldInputChecker.isMonthOrYearInputGood(month_input.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please write correct input!");
            alert.show();
            return false;
        }
        return true;
    }
    private void formatUserInput() {
        GraphType type;
        switch (graph_selection.getValue()) {

            case "Linijinis":
                type = GraphType.Linijinis;
                break;
            case "Anuiteto":
                type = GraphType.Anuiteto;
                break;
            default:
                System.out.println(graph_selection.getValue());
                throw new RuntimeException();
        }

        String moneyInp = money_input.getText().replace(",", ".");
        String annualInter = annualInterest_input.getText().replace(",", ".");

        userInput = new UserInput(Double.parseDouble(moneyInp), Double.parseDouble(annualInter),
                Integer.parseInt(year_input.getText()),
                Integer.parseInt(month_input.getText()), type);
    }
}
