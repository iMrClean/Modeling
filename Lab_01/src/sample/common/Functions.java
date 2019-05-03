package sample.common;

public class Functions {

    public static double uniformFunction(double i, double a, double b) {
        if (i <= a) {
            return 0;
        } else if (i <= b && i >= a) {
            return (i - a) / (b - a);
        } else {
            return 1;
        }
    }

    public static double uniformDestiny(double i, double a, double b) {
        if (i <= b && i >= a) {
            return 1 / (b - a);
        } else
            return 0;
    }

    public static double expFunction(double i, double lambda) {
        if (i < 0) {
            return 0;
        } else
            return 1 - Math.pow(Math.E, (lambda) * (-1) * i);
    }

    public static double expDestiny(double i, double lambda) {
        if (i < 0) {
            return 0;
        } else {
            return lambda * Math.pow(Math.E, (lambda) * (-1) * i);
        }
    }
}
