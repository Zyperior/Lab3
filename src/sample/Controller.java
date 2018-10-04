package sample;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

    public void init(){
        model.getObservableShapeList().addListener((ListChangeListener<CanvasShape>) c -> drawShapes());
        model.shapeColorProperty().bindBidirectional(colorPicker.valueProperty());

    }

    public void canvasClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        CanvasShape shape = ShapeFactory.getShape(model.getShapeType(),x-5,y-5,10,model.getShapeColor());

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
}
