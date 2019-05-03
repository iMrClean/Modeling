package sample.functions;

import javafx.collections.ObservableList;
import sample.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Functions {

    public static int getPoisson(Random generator, int x) {
        return Math.abs(generator.nextInt() % x);
    }

    public static double getRandomCheck(ObservableList<Model> userData, int n, int max, int arrSize) {
        List<Integer> gist = new ArrayList<>();
        double currNum = 0;
        double pNumber = 1.0 / ((double) max + 1);
        double summDelta = 0.0;
        double maxSummDelta = 2 * (1 - pNumber);
        for (int i = 0; i < max + 1; i++) {
            gist.add(0);
            for (Model userDatum : userData) {
                if (n == 1) {
                    currNum = userDatum.getFirst();
                }
                if (n == 2) {
                    currNum = userDatum.getSecond();
                }
                if (n == 3) {
                    currNum = userDatum.getThird();
                }
                if (n == 4) {
                    currNum = userDatum.getFour();
                }
                if (n == 5) {
                    currNum = userDatum.getFive();
                }
                if (n == 6) {
                    currNum = userDatum.getSix();
                }
                if (currNum == i) {
                    gist.set(i, gist.get(i) + 1);
                }
            }
        }
        //суммарное отклонение по гисте
        for (int i = 0; i < max + 1; i++) {
            summDelta += Math.abs((((double) gist.get(i)) / arrSize) - pNumber);
        }
        double result = 1.0 - (summDelta / maxSummDelta);
        if (result < 0.0) {
            result = 0.0;
        }
        return (double) Math.round(result * 1000d) / 1000d;
    }
}
