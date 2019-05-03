package sample.model;

import javafx.beans.property.SimpleStringProperty;

public class Table {

    private final SimpleStringProperty x;

    private final SimpleStringProperty y;

    public Table(String x, String y) {
        this.x = new SimpleStringProperty(x);
        this.y = new SimpleStringProperty(y);
    }

    public String getX() {
        return x.get();
    }

    public void setX(String fName) {
        x.set(fName);
    }

    public String getY() {
        return y.get();
    }

    public void setY(String fName) {
        y.set(fName);
    }

}
