package sample;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
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

    Model model = new Model();

    public Controller(){}

    public void CircleButtonAction(ActionEvent actionEvent) {
        model.setShapeType(ShapeType.CIRCLE);
    }

    public void SquareButtonAction(ActionEvent actionEvent) {
        model.setShapeType(ShapeType.SQUARE);
    }

    public void SelectToggleAction(ActionEvent actionEvent) {
    }

    public void init(){
        model.getObservableShapeList().addListener((ListChangeListener<CanvasShape>) c -> drawShapes());
        model.shapeColorProperty().bindBidirectional(colorPicker.valueProperty());

        textFieldWidth.addEventFilter(KeyEvent.KEY_TYPED, numericValidation(2));
        textFieldWidth.textProperty().bindBidirectional(model.shapeWidthProperty(), new NumberStringConverter());

        clearCanvas();

    }

    public void canvasClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        CanvasShape shape = ShapeFactory
                .getShape(model.getShapeType(),
                        x-(model.getShapeWidth()/2),
                        y-(model.getShapeWidth()/2),
                        model.getShapeWidth(),
                        model.getShapeColor());

        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            model.getObservableShapeList().add(shape);

        }
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            model.getObservableShapeList().remove(model.getObservableShapeList().size()-1);
        }






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

    public EventHandler<KeyEvent> numericValidation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh)
                    e.consume();

                if(!e.getCharacter().matches("[0-9]"))
                    e.consume();

            }
        };
    }

}
