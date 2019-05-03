package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Table {

    private final SimpleStringProperty x;

    private final SimpleStringProperty y;

    private final SimpleStringProperty ves;

    public Table(String x, String y, String ves) {
        this.x = new SimpleStringProperty(x);
        this.y = new SimpleStringProperty(y);
        this.ves = new SimpleStringProperty(ves);
    }

    public String getX() {
        return x.get();
    }

    public void setX(String x) {
        this.x.set(x);
    }

    public String getY() {
        return y.get();
    }

    public void setY(String y) {
        this.y.set(y);
    }

    public String getVes() {
        return ves.get();
    }

    public void setVes(String ves) {
        this.ves.set(ves);
    }

}
