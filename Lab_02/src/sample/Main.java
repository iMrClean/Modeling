package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Лабораторная работа № 2
 * Надо написать программу, которая будет генерировать 6 массивов псевдослучайных чисел, разделённых на 2 группы.
 * В первой группе (3 массива) числа генерируются табличным способом, во второй группе - алгоритмически.
 * В каждой группе один массив состоит из чисел, содержащих только одну десятичную цифру, второй - две цифры, третий три цифры.
 * На экран из этих массивов выводятся первые 100 чисел в табличку.
 * Также рядом с этой табличкой можно в ещё один столбик вести 10 чисел "от руки".
 * Программа должна для каждого массива чисел (в т.ч. введённого от руки) применять некий самостоятельно придуманный критерий случайности для этих последовательностей
 * (0 - вообще не случайная последовательность, 1 - идеально случайная последовательность).
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("VIU7 Stepnin K.V");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
