package sample;


import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import sample.canvasObjects.CanvasShape;
import sample.canvasObjects.ShapeType;

import java.util.ArrayList;
import java.util.List;


public class Model {

    private ShapeType shapeType = ShapeType.CIRCLE;
    private DoubleProperty shapeWidth;
    private ObjectProperty<Color> shapeColor;

    private BooleanProperty selectModeEnabled;

    private List<CanvasShape> canvasShapes = new ArrayList<>();
    private ObservableList<CanvasShape> observableShapeList = FXCollections.observableList(canvasShapes);


    public Model(){
        shapeColor = new SimpleObjectProperty<>();
        shapeWidth = new SimpleDoubleProperty(10);
        selectModeEnabled = new SimpleBooleanProperty(false);
    }


    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public double getShapeWidth() {
        return shapeWidth.get();
    }

    public DoubleProperty shapeWidthProperty() {
        return shapeWidth;
    }

    public void setShapeWidth(double shapeWidth) {
        this.shapeWidth.set(shapeWidth);
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

    public boolean isSelectModeEnabled() {
        return selectModeEnabled.get();
    }

    public BooleanProperty selectModeEnabledProperty() {
        return selectModeEnabled;
    }

    public void setSelectModeEnabled(boolean selectModeEnabled) {
        this.selectModeEnabled.set(selectModeEnabled);
    }

    public ObservableList<CanvasShape> getObservableShapeList() {
        return observableShapeList;
    }

    public void setObservableShapeList(ObservableList<CanvasShape> observableShapeList) {
        this.observableShapeList = observableShapeList;
    }
}
