package sample;

import javafx.beans.binding.When;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;
import sample.canvasObjects.CanvasShape;
import sample.canvasObjects.ShapeFactory;
import sample.canvasObjects.ShapeType;


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

    Model model = new Model();

    public Controller(){}

    public void init(){
        model.getObservableShapeList().addListener((ListChangeListener<CanvasShape>) c -> drawShapes());

        model.shapeColorProperty().bindBidirectional(colorPicker.valueProperty());
        textFieldWidth.textProperty().bindBidirectional(model.shapeWidthProperty(), new NumberStringConverter());
        shapeButtonCircle.disableProperty().bind(toggleButtonSelect.selectedProperty());
        shapeButtonSquare.disableProperty().bind(toggleButtonSelect.selectedProperty());
        model.selectModeEnabledProperty().bind(toggleButtonSelect.selectedProperty());
        toggleButtonSelect.textProperty().bind(new When
                (toggleButtonSelect.selectedProperty())
                .then("Edit-Mode")
                .otherwise("Select-Mode"));

        textFieldWidth.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(2));


        clearCanvas();

    }

    public void circleButtonAction(ActionEvent actionEvent) {
        model.setShapeType(ShapeType.CIRCLE);
    }

    public void squareButtonAction(ActionEvent actionEvent) {
        model.setShapeType(ShapeType.SQUARE);
    }

    public void selectToggleAction(ActionEvent actionEvent) {
    }

    public void canvasClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        CanvasShape shape = createShape(x,y);

        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            model.getObservableShapeList().add(shape);

        }
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            model.getObservableShapeList().remove(model.getObservableShapeList().size()-1);
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



}
