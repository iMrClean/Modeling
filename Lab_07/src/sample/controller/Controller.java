package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Table;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final ObservableList<Table> data = FXCollections.observableArrayList(
            new Table("-4", "5", "1"),
            new Table("-3", "3.5", "1"),
            new Table("-2", "-7.4", "1"),
            new Table("-1", "-7.3", "1"),
            new Table("0.2", "4", "1"),
            new Table("1", "7.6", "1"),
            new Table("2", "8.1", "1"),
            new Table("3", "-3.9", "1"),
            new Table("4", "-5", "1"),
            new Table("5", "-7", "1")
    );

    public TextField polynomialTextField;

    public TableColumn<Table, String> x;

    public TableColumn<Table, String> y;

    public TableColumn<Table, String> ves;

    public TableView<Table> table;

    @FXML
    private LineChart<Double, Double> lineChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        x.setCellValueFactory(new PropertyValueFactory<>("x"));
        y.setCellValueFactory(new PropertyValueFactory<>("y"));
        ves.setCellValueFactory(new PropertyValueFactory<>("ves"));
        table.setItems(data);
        lineChart.setCreateSymbols(false);
    }

    public void drawButton(ActionEvent event) {
        int size = 10;
        double[] xArray = new double[size];
        double[] yArray = new double[size];
        double[] vesArray = new double[size];
        //Заполняем массивы данными из таблицы
        fillData(xArray, yArray, vesArray, size);

        lineChart.getData().clear();

        XYChart.Series<Double, Double> function = new XYChart.Series<>();
        function.setName("Среднеквардратичное распределение");

        for (int i = 0; i < size; i++) {
            function.getData().add(new XYChart.Data<>(xArray[i], yArray[i]));
        }
        lineChart.getData().add(function);
    }

    private void fillData(double[] xArray, double[] yArray, double[] vesArray, int size) {
        for (int i = 0; i < size; i++) {
            xArray[i] = Double.parseDouble(x.getTableView().getItems().get(i).getX());
            yArray[i] = Double.parseDouble(y.getTableView().getItems().get(i).getY());
            vesArray[i] = Double.parseDouble(ves.getTableView().getItems().get(i).getVes());
        }
    }

    public void clearButton(ActionEvent event) {
        lineChart.getData().clear();
    }

}
