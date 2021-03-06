package sample;

import javafx.beans.binding.When;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;
import sample.canvasObjects.CanvasShape;
import sample.canvasObjects.ShapeFactory;
import sample.canvasObjects.ShapeType;
import sample.filesaving.SaveToBmp;

import java.sql.SQLOutput;
import java.util.HashMap;


public class Controller {
    @FXML
    Button shapeButtonCircle;
    @FXML
    Button shapeButtonSquare;
    @FXML
    Canvas canvas;
    @FXML
    ColorPicker colorPicker;
    @FXML
    TextField textFieldWidth;
    @FXML
    ToggleButton toggleButtonSelect;
    @FXML
    Button buttonMakeChange;
    @FXML
    MenuItem saveToBmp;

    Stage stage;

    HashMap<Integer, CanvasShape> selectedShape = new HashMap<>();

    Model model = new Model();

    public Controller(){}

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void init(){
        model.getObservableShapeList().addListener((ListChangeListener<CanvasShape>) c -> drawShapes());

        model.shapeColorProperty().bindBidirectional(colorPicker.valueProperty());
        textFieldWidth.textProperty().bindBidirectional(model.shapeWidthProperty(), new NumberStringConverter());
        shapeButtonCircle.disableProperty().bind(toggleButtonSelect.selectedProperty());
        shapeButtonSquare.disableProperty().bind(toggleButtonSelect.selectedProperty());
        buttonMakeChange.disableProperty().bind(model.selectModeEnabledProperty().not());
        model.selectModeEnabledProperty().bind(toggleButtonSelect.selectedProperty());
        toggleButtonSelect.textProperty().bind(new When
                (toggleButtonSelect.selectedProperty())
                .then("Edit-Mode")
                .otherwise("Select-Mode"));

        textFieldWidth.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(3));


        clearCanvas();

    }

    public void circleButtonAction(ActionEvent actionEvent) {
        model.setShapeType(ShapeType.CIRCLE);
    }

    public void squareButtonAction(ActionEvent actionEvent) {
        model.setShapeType(ShapeType.SQUARE);
    }

    public void onSelectionToggle(ActionEvent actionEvent) {
        if(!model.isSelectModeEnabled()){
            clearSelection();
            clearCanvas();
            drawShapes();
        }
    }

    public void makeChange(ActionEvent actionEvent) {
        changeSelectedShape();
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        if(!model.isSelectModeEnabled()){

            CanvasShape shape = createShape(x,y);

            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                model.getObservableShapeList().add(shape);

            }
            if(mouseEvent.getButton() == MouseButton.SECONDARY){
                model.getObservableShapeList().remove(model.getObservableShapeList().size()-1);
            }


        }
        else{
            setSelectedShape(x,y);

        }

    }

    public void setSelectedShape(double x, double y){

        selectedShape = new HashMap<>();

        for (int i = 0; i < model.getObservableShapeList().size(); i++) {
            CanvasShape shape = model.getObservableShapeList().get(i);
            double xMax = shape.getPoint().getX()+shape.getWidth();
            double yMax = shape.getPoint().getY()+shape.getWidth();
            boolean selected = (x >= shape.getPoint().getX() && x <= xMax) &&
                    (y >= shape.getPoint().getY() && y <= yMax);

            if(shape.isSelected() && selected){
                shape.setSelected(false);
            }
            else if(selected){
                shape.setSelected(selected);
                selectedShape.put(i, shape);
            }
            else{
                shape.setSelected(selected);
            }
        }
        clearCanvas();
        drawShapes();
    }

    public void changeSelectedShape(){

        if(!selectedShape.isEmpty()){
            for (CanvasShape shape : selectedShape.values()) {
                double diffWidth = shape.getWidth()-model.getShapeWidth();
                shape.setColor(model.getShapeColor());
                shape.setWidth(model.getShapeWidth());
                shape.setPoint(new Point2D(shape.getPoint().getX()+(diffWidth/2),(shape.getPoint().getY()+(diffWidth/2))));
                model.getObservableShapeList().remove((int)selectedShape.keySet().toArray()[0]);
                clearCanvas();
                model.getObservableShapeList().add((int)selectedShape.keySet().toArray()[0],shape);
            }
        }

    }

    public CanvasShape createShape(double x, double y){
        return ShapeFactory.getShape(model.getShapeType(),
                        x-(model.getShapeWidth()/2),
                        y-(model.getShapeWidth()/2),
                        model.getShapeWidth(),
                        model.getShapeColor());
    }

    public void drawShapes(){

        for (CanvasShape shape: model.getObservableShapeList()) {

            shape.draw(canvas.getGraphicsContext2D());

        }

    }

    public void clearSelection(){

        for (CanvasShape shape: model.getObservableShapeList()) {

            shape.setSelected(false);

        }

    }

    public void clearCanvas(){
        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        canvas.getGraphicsContext2D().fillRect(-100,-100,1000,1000);
    }

    public EventHandler<KeyEvent> numericValidation(final Integer maxLength) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= maxLength)
                    e.consume();

                if(!e.getCharacter().matches("[0-9]"))
                    e.consume();

            }
        };
    }

    public void saveToBmpAction(ActionEvent actionEvent) {

        SaveToBmp save = new SaveToBmp((int)canvas.getWidth(),(int)canvas.getHeight(),canvas,stage);
        save.saveToFile();
    }
}
