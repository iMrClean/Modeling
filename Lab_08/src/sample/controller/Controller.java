package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Controller {

    public TextField minField, maxField, countField, alphaField, limitField, resultField;

    public void sqrtButton(ActionEvent event) {
        double min = Double.parseDouble(minField.getText());
        double max = Double.parseDouble(maxField.getText());
        int n = Integer.parseInt(countField.getText());
        double alpha = Double.parseDouble(alphaField.getText());
        double predel = Double.parseDouble(limitField.getText());

        double[] roots = new double[n - 1];
        double[] matrix;
        double[][] aa;

        double j = min;
        double k = min + 0.1;
        int l = 0;
        for (int i = 0; i < 20; i++) {
            System.out.println(legandre(j, n));
            if ((legandre(j, n) * legandre(k, n)) > 0) {
                j += 0.1;
                k += 0.1;
                continue;
            }
            roots[l] = bisection(j, k, n);
            j += 0.1;
            k += 0.1;
            l++;
        }
        aa = makeSystem(roots, n);
        matrix = gauss(aa, n - 1, n);
        double result = rootLaplace(predel, alpha, roots, matrix, n);
        resultField.setText(String.valueOf(result));
    }

    private double[] gauss(double[][] matrix, int rowCount, int colCount) {
        int i;
        int[] mask = new int[colCount - 1];
        for (i = 0; i < colCount - 1; i++) {
            mask[i] = i;
        }
        if (GaussDirectPass(matrix, mask, colCount, rowCount)) {
            return GaussReversePass(matrix, mask, colCount, rowCount);
        } else {
            return null;
        }
    }

    private double[] GaussReversePass(double[][] matrix, int[] mask, int colCount, int rowCount) {
        int i, j, k;
        for (i = rowCount - 1; i >= 0; i--)
            for (j = i - 1; j >= 0; j--) {
                double tempMn = matrix[j][i];
                for (k = 0; k < colCount; k++)
                    matrix[j][k] -= matrix[i][k] * tempMn;
            }
        double[] answer = new double[rowCount];
        for (i = 0; i < rowCount; i++) answer[mask[i]] = matrix[i][colCount - 1];
        return answer;
    }

    private boolean GaussDirectPass(double[][] matrix, int[] mask, int colCount, int rowCount) {
        int i, j, k, maxId, tmpInt;
        double maxVal, tempDouble;
        for (i = 0; i < rowCount; i++) {
            maxId = i;
            maxVal = matrix[i][i];
            for (j = i + 1; j < colCount - 1; j++)
                if (Math.abs(maxVal) < Math.abs(matrix[i][j])) {
                    maxVal = matrix[i][j];
                    maxId = j;
                }
            if (maxVal == 0) {
                return false;
            }
            if (i != maxId) {
                for (j = 0; j < rowCount; j++) {
                    tempDouble = matrix[j][i];
                    matrix[j][i] = matrix[j][maxId];
                    matrix[j][maxId] = tempDouble;
                }
                tmpInt = mask[i];
                mask[i] = mask[maxId];
                mask[maxId] = tmpInt;
            }
            for (j = 0; j < colCount; j++) {
                matrix[i][j] /= maxVal;
            }
            for (j = i + 1; j < rowCount; j++) {
                double tempMn = matrix[j][i];
                for (k = 0; k < colCount; k++)
                    matrix[j][k] -= matrix[i][k] * tempMn;
            }
        }
        return true;
    }

    private double[][] makeSystem(double[] roots, int n) {
        n = n - 1;
        double[][] matrix = new double[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
        double k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Math.pow(roots[j], i);
            }
            if (k % 2 == 0)
                matrix[i][n] = 2 / (k + 1);
            else
                matrix[i][n] = 0;
            k++;
        }
        return matrix;
    }

    private double legandre(double x, int n) {
        if (n == 1)
            return 1;
        else if (n == 2)
            return x;
        else if (n == 3)
            return 0.5 * (3 * Math.pow(x, 2) - 1);
        else if (n == 4)
            return 5 * Math.pow(x, 3) / 2 - 3 * x / 2;
        else if (n == 5)
            return (35 * Math.pow(x, 4) - 30 * Math.pow(x, 2) + 3) / 8;
        else if (n == 6)
            return (63 * Math.pow(x, 5) - 70 * Math.pow(x, 3) + 15 * x) / 8;
        else if (n == 7)
            return (231 * Math.pow(x, 6) - 315 * Math.pow(x, 4) + 105 * Math.pow(x, 2) - 5) / 16;
        else return 0;
    }

    private double bisection(double a, double b, int n) {
        double eps = 0.000000000000000000001;
        double c = (a + b) / 2;
        if (func(a, n) * func(c, n) < 0)
            b = c;
        else
            a = c;
        if (Math.abs(b - a) > eps && func(c, n) != 0)
            return bisection(a, b, n);
        else
            return c;
    }

    private static double func(double x, double n) {
        return Math.exp(-Math.pow(x, 2) / 2) / (Math.sqrt(2 * Math.PI));
    }

    private double rootLaplace(double predel, double alfa, double[] roots, double[] A, int n) {
        double a = 0;
        double b = predel;
        // Точность.
        double accuracy = 1e-10;
        // Длина интервала.
        double length = b - a;
        // Начальная ошибка.
        double err = length;
        // Корень.
        double x = 0;
        while (Math.abs(b - a) > accuracy && integral(x, roots, A, n) != alfa) {
            // Вычисляем середину интервала.
            x = (a + b) / 2;
            // Найдём новый интервал, в котором функция меняет знак.
            if (integral(x, roots, A, n) < alfa) {
                a = x;
            } else if (integral(x, roots, A, n) > alfa) {
                b = x;
            }
            // Вычисляем новую ошибку.
            err = (b - a) / 2;
        }
        return x;
    }

    private double integral(double predel, double[] roots, double[] A, int n) {
        double result = 0;
        double[] Xi = new double[n - 1];
        // составляем массив xi
        for (int i = 0; i < n - 1; i++) {
            Xi[i] = (predel + predel * roots[i]) / 2;
            //textBox4.Text += Xi[i].ToString() + "\r\n";
        }
        for (int i = 0; i < n - 1; i++) {
            result += A[i] * laplace(Xi[i]);
        }
        result = predel * result / 2;
        return result;
    }

    private double laplace(double x) {
        return Math.exp(-Math.pow(x, 2) / 2) / (Math.sqrt(2 * Math.PI));
    }
}
