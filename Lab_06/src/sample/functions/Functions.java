package sample.functions;

public class Functions {
    /**
     * Функция, при желании можно изменить (в идеале вынести в интерфейс для изменения пользователем)
     */
    public static double function(double func) {
        return Math.atan(func);
    }

    /**
     * Многочлен Ньютона
     */
    protected double newtonPolynomial(double[] x, double[] y, double xk, int degree) {
        if (degree < 1) {
            return 0;
        }
        double P = 0;
        for (int i = 1; i <= degree; i++) {
            double t = 1;
            for (int k = 0; k < i; k++) {
                t *= xk - x[k];
            }
            P += dividedDifferences(x, y, 0, i) * t;
        }
        return y[0] + P;
    }

    /**
     * Разделённые разности
     */
    private static double dividedDifferences(double[] x, double[] y, int xstart, int xend) {
        double dn = x[xend] - x[xstart];
        if (dn == 0) {
            return 0;
        }
        if (xend - xstart == 1) {
            return (y[xend] - y[xstart]) / dn;
        } else if (xend - xstart > 1) {
            return (dividedDifferences(x, y, xstart + 1, xend) - dividedDifferences(x, y, xstart, xend - 1)) / dn;
        } else {
            return 0;
        }
    }

    /**
     * Заполняем массив maxX нужными значениями
     */
    public static void checkMethod(double[] xArray, double _xValue, int _polynomial, int count, double[] masX, int remainder, int v, int l) {
        if (count - v >= 0 && count + v + 1 <= 21) {
            for (int i = count - v; i < (count + v + remainder); i++, l++) {
                masX[l] = xArray[i];
            }
        } else if (count - v < 0 && _xValue >= xArray[0]) {
            for (int i = 0; i < _polynomial + 1; i++, l++) {
                masX[l] = xArray[i];
            }
        } else if (_xValue < xArray[0]) {
            for (int i = 0; i < _polynomial + 1; i++, l++) {
                masX[l] = xArray[i];
            }
        } else if (count + v + 1 > 21 && _xValue <= xArray[20]) {
            for (int i = 21 - 2 * v - remainder; i < 21; i++, l++) {
                masX[l] = xArray[i];
            }
        } else if (_xValue > xArray[20]) {
            for (int i = 21 - 2 * v - remainder; i < 21; i++, l++) {
                masX[l] = xArray[i];
            }
        }
    }

    /**
     * Интерполяция полинома Ньютона.
     */
    public static double interpolateNewtonPolynomial(double x, double step, int polynomial, double[] masX) {
        double[] MasY = new double[polynomial + 1];
        for (int i = 0; i < polynomial + 1; i++) {
            MasY[i] = function(masX[i]);
        }

        double[][] mas = new double[polynomial + 2][polynomial + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < polynomial + 1; j++) {
                if (i == 0) {
                    mas[i][j] = masX[j];
                } else {
                    mas[i][j] = MasY[j];
                }
            }
        }

        int m = polynomial;
        for (int i = 2; i < polynomial + 2; i++) {
            for (int j = 0; j < m; j++) {
                mas[i][j] = mas[i - 1][j + 1] - mas[i - 1][j];
            }
            m--;
        }

        double[] dy0 = new double[polynomial + 1];
        for (int i = 0; i < polynomial + 1; i++) {
            dy0[i] = mas[i + 1][0];
        }

        double res = dy0[0];
        double[] xn = new double[polynomial];
        xn[0] = x - mas[0][0];
        for (int i = 1; i < polynomial; i++) {
            double ans = xn[i - 1] * (x - mas[0][i]);
            xn[i] = ans;
        }

        int m1 = polynomial + 1;
        int fact = 1;
        for (int i = 1; i < m1; i++) {
            fact = fact * i;
            res = res + (dy0[i] * xn[i - 1]) / (fact * Math.pow(step, i));
        }

        return res;
    }

    /**
     * Интерполяция полинома Лагранжа, не пригодится в лабораторной, но оставлю реализацию тут, работает проверено
     */
    private static double interpolateLagrangePolynomial(double[] xValues, double[] yValues, double x, int size) {
        double lagrangePol = 0;

        for (int i = 0; i < size; i++) {
            double basicsPol = 1;
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    basicsPol *= (x - xValues[j]) / (xValues[i] - xValues[j]);
                }
            }
            lagrangePol += basicsPol * yValues[i];
        }

        return lagrangePol;
    }
}
