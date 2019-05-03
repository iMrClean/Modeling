package sample.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Model;

import java.util.Random;

import static sample.functions.Functions.getPoisson;
import static sample.functions.Functions.getRandomCheck;


public class Controller {

    public TextField first;

    public TextField second;

    public TextField third;

    public TextField four;

    public TextField five;

    public TextField six;

    public TextField seven;

    public TextField eight;

    public TextField nine;

    public TextField ten;

    public TableColumn<Model, Integer> firstResultCategory;

    public TableColumn<Model, Integer> secondResultCategory;

    public TableColumn<Model, Integer> thirdResultCategory;

    public TableColumn<Model, Integer> fourResultCategory;

    public TableColumn<Model, Integer> fiveResultCategory;

    public TableColumn<Model, Integer> sixResultCategory;

    public Label textWindow;


    @FXML
    private TableView<Model> tableUsers;
    @FXML
    private TableView<Model> resultTable;
    @FXML
    private TableColumn<Model, Integer> firstCategory;
    @FXML
    private TableColumn<Model, Integer> secondCategory;
    @FXML
    private TableColumn<Model, Integer> thirdCategory;
    @FXML
    private TableColumn<Model, Integer> fourCategory;
    @FXML
    private TableColumn<Model, Integer> fiveCategory;
    @FXML
    private TableColumn<Model, Integer> sixCategory;

    private ObservableList<Model> usersData = FXCollections.observableArrayList();

    private ObservableList<Model> correctionData = FXCollections.observableArrayList();

    private ObservableList<Model> userNumbersData = FXCollections.observableArrayList();

    private ObservableList<Model> resultData = FXCollections.observableArrayList();

    // инициализируем форму данными
    @FXML
    private void initialize() {
        // устанавливаем тип и значение которое должно хранится в колонке
        firstCategory.setCellValueFactory(new PropertyValueFactory<>("first"));
        secondCategory.setCellValueFactory(new PropertyValueFactory<>("second"));
        thirdCategory.setCellValueFactory(new PropertyValueFactory<>("third"));
        fourCategory.setCellValueFactory(new PropertyValueFactory<>("four"));
        fiveCategory.setCellValueFactory(new PropertyValueFactory<>("five"));
        sixCategory.setCellValueFactory(new PropertyValueFactory<>("six"));

        firstResultCategory.setCellValueFactory(new PropertyValueFactory<>("first"));
        secondResultCategory.setCellValueFactory(new PropertyValueFactory<>("second"));
        thirdResultCategory.setCellValueFactory(new PropertyValueFactory<>("third"));
        fourResultCategory.setCellValueFactory(new PropertyValueFactory<>("four"));
        fiveResultCategory.setCellValueFactory(new PropertyValueFactory<>("five"));
        sixResultCategory.setCellValueFactory(new PropertyValueFactory<>("six"));

        // заполняем таблицу данными
        tableUsers.setItems(correctionData);
        resultTable.setItems(resultData);
        textWindow.setText("0.0");
    }

    public void firstButtonAction(ActionEvent actionEvent) {
        Random generator = new Random(System.nanoTime());
        usersData.clear();
        for (int i = 0; i < 1000; i++) {
            usersData.add(new Model(
                    getPoisson(generator, 10),
                    getPoisson(generator, 100),
                    getPoisson(generator, 1000),
                    getPoisson(generator, 10),
                    getPoisson(generator, 100),
                    getPoisson(generator, 1000)));
        }
        correctionData.clear();
        for (int i = 0; i < 10; i++) {
            correctionData.add(usersData.get(i));
        }
        resultData.clear();
        resultData.add(new Model(getRandomCheck(usersData, 1, 9, 1000),
                getRandomCheck(usersData, 2, 99, 1000),
                getRandomCheck(usersData, 3, 999, 1000),
                getRandomCheck(usersData, 4, 9, 1000),
                getRandomCheck(usersData, 5, 99, 1000),
                getRandomCheck(usersData, 6, 999, 1000)));
    }

    public void clearButtonAction(ActionEvent actionEvent) {
        tableUsers.getItems().clear();
        resultTable.getItems().clear();
    }

    public void exitButtonAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void userButton(ActionEvent actionEvent) {
        try {
            userNumbersData.clear();
            int _first = Integer.parseInt(first.getText());
            int _second = Integer.parseInt(second.getText());
            int _third = Integer.parseInt(third.getText());
            int _four = Integer.parseInt(four.getText());
            int _five = Integer.parseInt(five.getText());
            int _six = Integer.parseInt(six.getText());
            int _seven = Integer.parseInt(seven.getText());
            int _eight = Integer.parseInt(eight.getText());
            int _nine = Integer.parseInt(nine.getText());
            int _ten = Integer.parseInt(ten.getText());
            userNumbersData.add(new Model(_first, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_second, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_third, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_four, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_five, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_six, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_seven, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_eight, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_nine, 0, 0, 0, 0, 0));
            userNumbersData.add(new Model(_ten, 0, 0, 0, 0, 0));
            textWindow.setText(String.format("%.3f", getRandomCheck(userNumbersData, 1, 9, 10)));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText("Неверно заполнены поля или они пустые");
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}
