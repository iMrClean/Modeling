package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static sample.common.Functions.*;


public class Controller implements Initializable {
    @FXML
    private TextField minValue;
    @FXML
    private TextField maxValue;
    @FXML
    private TextField aValue;
    @FXML
    private TextField bValue;
    @FXML
    private LineChart<Double, Double> lineChart;
    @FXML
    private ComboBox<String> myBox;
    private ObservableList<String> list = FXCollections.observableArrayList(
            "Равномерное распределение", "Экспоненциальное распределение");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        myBox.setItems(list);
    }


    @FXML
    public void secondButtonAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void firstButtonAction(ActionEvent actionEvent) {
        try {
            double min = Double.parseDouble(minValue.getText());
            double max = Double.parseDouble(maxValue.getText());
            double a = Double.parseDouble(aValue.getText());
            double b = Double.parseDouble(bValue.getText());
            try {
                switch (myBox.getValue()) {
                    //1 график
                    case "Равномерное распределение":
                        final XYChart.Series<Double, Double> function = new XYChart.Series<>();
                        function.setName("Функция распределения");
                        final XYChart.Series<Double, Double> destiny = new XYChart.Series<>();
                        destiny.setName("Плотность распределения");
                        for (double i = min; i < max; i += 0.5) {
                            function.getData().add(new XYChart.Data<>(i, uniformFunction(i, a, b)));
                            destiny.getData().add(new XYChart.Data<>(i, uniformDestiny(i, a, b)));
                        }
                        lineChart.getData().addAll(function, destiny);
                        break;
                    //2 график
                    case "Экспоненциальное распределение":
                        final XYChart.Series<Double, Double> function2 = new XYChart.Series<>();
                        function2.setName("Функция распределения");
                        final XYChart.Series<Double, Double> destiny2 = new XYChart.Series<>();
                        destiny2.setName("Плотность распределения");
                        for (double i = 0; i < max; i += 0.2) {
                            function2.getData().add(new XYChart.Data<>(i, expFunction(i, a)));
                            destiny2.getData().add(new XYChart.Data<>(i, expDestiny(i, a)));
                        }
                        lineChart.getData().addAll(function2, destiny2);
                        break;
                }
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setContentText("Не выбрано распределение");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверно заполнены поля или они пустые");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }

    @FXML
    public void clearButtonAction(ActionEvent actionEvent) {
        minValue.clear();
        maxValue.clear();
        aValue.clear();
        bValue.clear();
        lineChart.getData().clear();
    }
}
