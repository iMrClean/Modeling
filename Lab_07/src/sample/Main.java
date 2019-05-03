package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Лабораторная работа № 2
 *
 * Построить полином заданной степени апроксимирующий исходную таблицу в смысле наилучшего среднеквадратичного притяжения тоесть
 * Построить полином, который усредняет таблицу.
 * !Лучше использовать Матрицу к верхнему треугольному виду (методом Гаусса) определитель = произведение диагональных элементов.
 * Задаю таблицу в качестве исходных данных.
 * Х, У, Вес каждой точки (Изначально заданы 1)
 * Степень полинома n. 4 степень полинома точно пройдет по всем точкам
 * Пример 7 (у вас 8 неизместных) матрица 8х8 нашли коэффициенты
 * Результат который получается после апроксимации.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("VIU - 7");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
