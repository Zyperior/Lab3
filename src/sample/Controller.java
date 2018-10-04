package sample;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


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

        draw();
    }

    public void SquareButtonAction(ActionEvent actionEvent) {

    }

    public void init(){
        model.getObservableList().addListener( (ListChangeListener<Point2D>)c -> draw());

    }

    public void canvasClicked(MouseEvent mouseEvent) {
        Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());

        if(mouseEvent.getButton() == MouseButton.PRIMARY){
            model.getObservableList().add(point);
        }
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
            model.getObservableList().remove(model.getObservableList().size()-1);
        }






    }

    public void draw(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0, canvas.getWidth(),canvas.getHeight());

        Paint p = Color.RED;
        gc.setFill(p);

        for (Point2D point: model.getObservableList()) {

            gc.fillOval(point.getX()-25,point.getY()-25,50,50);
        }


    }
}
