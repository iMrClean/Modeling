package sample.model;

public class Model {

    private double first;

    private double second;

    private double third;

    private double four;

    private double five;

    private double six;

    public Model(double first, double second, double third, double four, double five, double six) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.four = four;
        this.five = five;
        this.six = six;
    }

    public double getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public double getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public double getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    public double getFour() {
        return four;
    }

    public void setFour(int four) {
        this.four = four;
    }

    public double getFive() {
        return five;
    }

    public void setFive(int five) {
        this.five = five;
    }

    public double getSix() {
        return six;
    }

    public void setSix(int six) {
        this.six = six;
    }
}
