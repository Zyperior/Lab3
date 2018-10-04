package sample;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class Model {

    private ObjectProperty<Color> shapeColor;
    private StringProperty text;
    private BooleanProperty disabled;
    private List<Point2D> points = new ArrayList<>();
    private ObservableList<Point2D> observableList = FXCollections.observableList(points);



    public Model(){
        text = new SimpleStringProperty();
        disabled = new SimpleBooleanProperty();
    }

    public final String getText(){
        return text.get();
    }

    public void setText(String s){text.setValue(s);}

    public StringProperty textProperty(){
        return text;
    }

    public boolean isDisabled() {
        return disabled.get();
    }

    public BooleanProperty disabledProperty() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled.set(disabled);
    }

    public ObservableList<Point2D> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<Point2D> observableList) {
        this.observableList = observableList;
    }
}
