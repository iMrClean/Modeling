package sample.functions;

public class Functions {

    public static double[][] makeSystem(double[][] xyTable, int basis, double[] ves) {
        double[][] matrix = new double[basis][basis + 1];
        for (int i = 0; i < basis; i++) {
            for (int j = 0; j < basis; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int i = 0; i < basis; i++) {
            for (int j = 0; j < basis; j++) {
                double sumA = 0, sumB = 0;
                for (int k = 0; k < 10; k++) {
                    sumA += Math.pow(xyTable[0][k], i) * Math.pow(xyTable[0][k], j) * ves[k];
                    sumB += xyTable[1][k] * Math.pow(xyTable[0][k], i) * ves[k];
                }
                matrix[i][j] = sumA;
                matrix[i][basis] = sumB;
            }
        }
        return matrix;
    }

    //TODO 3 знака в раунд
    public static double parabola(double x, double[] result, int basis) {
        double y = 0;
        for (int i = 0; i < basis; i++) {
            if (result[i] != 0)
                y += result[i] * Math.pow(x, i);
        }
        return y;
    }

    public static double[] gauss(double[][] matrix, int rowCount, int colCount) {
        int i;
        int[] mask = new int[colCount - 1];
        for (i = 0; i < colCount - 1; i++) mask[i] = i;
        if (gaussDirectPass(matrix, mask, colCount, rowCount)) {
            return gaussReversePass(matrix, mask, colCount, rowCount);
        } else {
            return null;
        }
    }

    private static boolean gaussDirectPass(double[][] matrix, int[] mask, int colCount, int rowCount) {
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
            for (j = 0; j < colCount; j++) matrix[i][j] /= maxVal;
            for (j = i + 1; j < rowCount; j++) {
                double tempMn = matrix[j][i];
                for (k = 0; k < colCount; k++)
                    matrix[j][k] -= matrix[i][k] * tempMn;
            }
        }
        return true;
    }

    private static double[] gaussReversePass(double[][] matrix, int[] mask, int colCount, int rowCount) {
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
}
