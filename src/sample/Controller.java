package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;


public class Controller {
    @FXML
    Button shapeButtonCircle;
    @FXML
    Button shapeButtonSquare;
    @FXML
    Canvas canvas;

    Model model = new Model();

    public Controller(){};

    public void CircleButtonAction(ActionEvent actionEvent) {
        model.setText("Circle");
        model.setDisabled(!model.isDisabled());
    }

    public void SquareButtonAction(ActionEvent actionEvent) {

    }

    public void init(){

    }
}
