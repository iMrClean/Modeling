package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Лабораторная работа №3
 * Дано:
 * n -
 * Корни полинома лежандра
 * Системное уравнение находятся коэффициенты
 * Функция Лапласса вычисляется по формуле
 * f(x) = 2/sqrt(2 * Math.PI) (интеграл 0->x) (e^(-t^2/2)) * dt = альфа, где альфа < 1
 * Вычислить интеграл методом Гаусса
 *
 * Сколько на отрезке график пересекает ось оХ
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("VIU - 7");
        primaryStage.setScene(new Scene(root, 800, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
