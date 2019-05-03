package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Table;

import java.text.DecimalFormat;

import static sample.functions.Functions.*;

public class Controller {

    private static final DecimalFormat decimalFormat = new DecimalFormat("##.###");

    private static final DecimalFormat sqrtFormat = new DecimalFormat("##");

    private final ObservableList<Table> data = FXCollections.observableArrayList(
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0"),
            new Table("0", "0")
    );

    @FXML
    private TextField xValue;
    @FXML
    private TextField polynomial;
    @FXML
    private TextField result;
    @FXML
    private TextField currentResult;
    @FXML
    private TextField sqrtFunction;
    @FXML
    private TableColumn<Table, String> x;
    @FXML
    private TableColumn<Table, String> y;
    @FXML
    private TableView<Table> table;

    @FXML
    private void initialize() {
        x.setCellValueFactory(new PropertyValueFactory<>("x"));
        y.setCellValueFactory(new PropertyValueFactory<>("y"));
        xValue.setText(String.valueOf(2.1));
        polynomial.setText(String.valueOf(4));
        table.setItems(data);
    }

    public void clearButtonAction(ActionEvent actionEvent) {
        table.getItems().clear();
        result.clear();
        currentResult.clear();
        sqrtFunction.clear();
    }

    public void calculateButtonAction(ActionEvent actionEvent) {
        int size = 21;
        double[] xArray = new double[size];
        double[] yArray = new double[size];
        double arg = 0;
        double step = 0.25;

        double _xValue = Double.parseDouble(xValue.getText());
        int _polynomial = Integer.parseInt(polynomial.getText());

        fillData(size, arg, step, xArray, yArray);
        fillTable(xArray, yArray);
        for (int i = 0; i < 21; i++) {
            yArray[i] = function(xArray[i]);
        }
        //Находим место в массиве
        int count = 0;
        double[] masX = new double[_polynomial + 1];
        for (int i = 0; i < 21; i++) {
            if (xArray[i] <= _xValue)
                count++;
            else
                break;
        }
        if (zeroPolynomial(xArray, yArray, _xValue, _polynomial, count)) {
            return;
        }
        // считаем остаток и среднюю
        int remainder = (_polynomial + 1) % 2;
        int v = _polynomial / 2;
        int l = 0;

        //перебираем все возможные варианты x и polynomial
        checkMethod(xArray, _xValue, _polynomial, count, masX, remainder, v, l);
        //считаем интерполяцию методом Ньютона
        double interpolate = interpolateNewtonPolynomial(_xValue, step, _polynomial, masX);
        //Расчетный результат
        result.setText(decimalFormat.format(interpolate));
        //Точное значение
        String format = decimalFormat.format(function(_xValue));
        currentResult.setText(format);
    }

    /**
     * Очищаем таблицу от начальных значений и заполняем ее
     */
    private void fillTable(double[] xArray, double[] yArray) {
        table.getItems().clear();
        for (int i = 0; i < 21; i++) {
            data.add(new Table(decimalFormat.format(xArray[i]), String.valueOf(decimalFormat.format(yArray[i]))));
        }
    }

    /**
     * Заполняем массивы xArray, yArray, считаем функцию
     */
    private void fillData(int size, double arg, double step, double[] xArray, double[] yArray) {
        for (int i = 0; i < size; i++) {
            xArray[i] = arg;
            yArray[i] = function(arg);
            arg += step;
        }
    }

    /**
     * Обрабатываем полином 0-й степени
     */
    private boolean zeroPolynomial(double[] xArray, double[] yArray, double _xValue, int _polynomial, int count) {
        if (_polynomial == 0) {
            if (xArray[count] - _xValue >= _xValue - xArray[count - 1]) {
                result.setText(decimalFormat.format(yArray[count - 1]));
            } else {
                result.setText(decimalFormat.format(yArray[count]));
            }
            return true;
        }
        return false;
    }

    public void rootButtonAction(ActionEvent actionEvent) {
        int size = 21;
        double step = 0.25;
        double arg = 0;
        double x = 0;
        double[] xArray = new double[size];
        double[] yArray = new double[size];
        fillData(size, arg, step, yArray, xArray);
        int n = Integer.parseInt(polynomial.getText());

        double[][] mas = new double[n + 2][n + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0)
                    mas[i][j] = yArray[j];
                else {
                    mas[i][j] = xArray[j];
                }
            }
        }
        int m = n;
        for (int i = 2; i < n + 2; i++) {
            for (int j = 0; j < m; j++) {
                mas[i][j] = mas[i - 1][j + 1] - mas[i - 1][j];
            }
            m--;
        }

        double[] dy0 = new double[n + 1];

        for (int i = 0; i < n + 1; i++) {
            dy0[i] = mas[i + 1][0];
        }

        double res = dy0[0];
        double[] xn = new double[n];
        xn[0] = x - mas[0][0];

        for (int i = 1; i < n; i++) {
            double ans = xn[i - 1] * (x - mas[0][i]);
            xn[i] = ans;
        }

        int m1 = n + 1;
        int fact = 1;
        for (int i = 1; i < m1; i++) {
            fact = fact * i;
            res = res + (dy0[i] * xn[i - 1]) / (fact * Math.pow(step, i));
        }
        sqrtFunction.setText(sqrtFormat.format(res));
    }
}
