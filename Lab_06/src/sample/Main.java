package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Лабораторная работа № 1 (Градов)
 * Написать интерполяции функции 1-ой переменной заданной таблично
 * Задано:
 * 1. Таблица
 * 2. x
 * 3. h - Степень полинома
 * Найти y(x)
 * Результат интерполяции
 * Точный результат
 * Дополнительно:
 * Найти корень функции (обратная интерполяция)
 * 1. Функция должна быть возвщрашающая, потому что аргумент должен увеличиваться.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("VUI - 7");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
