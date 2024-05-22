package org.example.lab_2.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.lab_2.Model.DateInterval;
import org.example.lab_2.Model.GraphData;
import org.example.lab_2.Model.Payment;
import org.example.lab_2.Model.SharedPaymentList;
import org.example.lab_2.Utilities.PaymentCalculations;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author Dovydas Girskas 5gr
 */

public class OutputController {
    private List<Payment> payments;
    private SceneController sceneController;
    private PaymentCalculations paymentCalculations;
    private ObservableList<XYChart.Series<String, Double>> graphDataList;

    @FXML
    private AreaChart<String, Double> graph;

    @FXML
    private TableView<Payment> data_table;

    @FXML
    private DatePicker delayEnd_date;

    @FXML
    private DatePicker delayStart_date;

    @FXML
    private DatePicker tableFilterEnd_date;

    @FXML
    private DatePicker tableFilterStart_date;

    @FXML
    private TableColumn<Payment, String> total_column;

    @FXML
    private TableColumn<Payment, String> date_column;

    @FXML
    private TableColumn<Payment, String> principal_column;

    @FXML
    private TableColumn<Payment, String> howMuchMoneyLeft_column;

    @FXML
    private TableColumn<Payment, String> interest_column;

    @FXML
    private void initialize() {
        sceneController = new SceneController();
        payments = SharedPaymentList.getInstance().getPayments();
        paymentCalculations = SharedPaymentList.getInstance().getPaymentCalculations();
        graphDataList = FXCollections.observableArrayList();

        // table setup
        howMuchMoneyLeft_column.setCellValueFactory(new PropertyValueFactory<>("formattedHowMuchLeftToPay"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("formattedPaymentDate"));
        interest_column.setCellValueFactory(new PropertyValueFactory<>("formattedInterestAmount"));
        principal_column.setCellValueFactory(new PropertyValueFactory<>("formattedPrincipalAmount"));
        total_column.setCellValueFactory(new PropertyValueFactory<>("formattedTotal"));

        data_table.setItems(FXCollections.observableArrayList(payments));

        // graph setup
        updateGraphDataList();
        graph.setData(graphDataList);
    }

    @FXML
    void applyDelayToTable(ActionEvent event) {
        payments = paymentCalculations.addDelayToList(new DateInterval(delayStart_date.getValue(), delayEnd_date.getValue()));
        updateTableAndGraph();
    }

    @FXML
    void cancelDelayToTable(ActionEvent event) {
        payments = paymentCalculations.getPayments();
        updateTableAndGraph();
    }

    @FXML
    void applyFilterTable(ActionEvent event) {
        payments = paymentCalculations.filterList(new DateInterval(tableFilterStart_date.getValue(), tableFilterEnd_date.getValue()));
        updateTableAndGraph();
    }

    @FXML
    void cancelFilterTable(ActionEvent event) {
        payments = paymentCalculations.getPayments();
        updateTableAndGraph();
    }

    @FXML
    void goBackToInputView(ActionEvent event) throws IOException {
        sceneController.switchToInputScene(event);
    }

    @FXML
    void saveOutput(ActionEvent event) {
        payments = paymentCalculations.getPayments();

        try {
            FileWriter outpFile = new FileWriter("output.txt");

            for (Payment payment : payments)
                outpFile.write(payment.toString());
            outpFile.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "successfully saved!");
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "something went wrong!");
            alert.show();
        }

    }

    private void updateGraphDataList() {
        graphDataList.clear();
        for (Payment payment : payments) {
            //System.out.println(payment.toString());           // grafikas buginasi???
            XYChart.Series<String, Double> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>(payment.getPaymentDate().toString(), payment.getTotal()));
            graphDataList.add(series);
        }
    }

    private void updateTableAndGraph() {
        data_table.setItems(FXCollections.observableArrayList(payments));
        updateGraphDataList();
        graph.setData(graphDataList);
    }
}
