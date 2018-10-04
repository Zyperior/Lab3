package sample;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import sample.canvasObjects.CanvasShape;
import sample.canvasObjects.ShapeType;

import java.util.ArrayList;
import java.util.List;


public class Model {

    private ShapeType shapeType = ShapeType.CIRCLE;

    private ObjectProperty<Color> shapeColor;
    private StringProperty text;
    private BooleanProperty disabled;
    private List<CanvasShape> canvasShapes = new ArrayList<>();
    private ObservableList<CanvasShape> observableList = FXCollections.observableList(canvasShapes);



    public Model(){
        text = new SimpleStringProperty();
        disabled = new SimpleBooleanProperty();
        shapeColor = new SimpleObjectProperty<>();
    }

    public Color getShapeColor() {
        return shapeColor.get();
    }

    public ObjectProperty<Color> shapeColorProperty() {
        return shapeColor;
    }

    public void setShapeColor(Color shapeColor) {
        this.shapeColor.set(shapeColor);
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
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

    public ObservableList<CanvasShape> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<CanvasShape> observableList) {
        this.observableList = observableList;
    }
}
