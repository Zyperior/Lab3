package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {
    @FXML
    Button shapeButtonCircle;
    @FXML
    Button shapeButtonSquare;
    @FXML
    TextField textField1;

    Model model = new Model();

    public Controller(){};

    public void CircleButtonAction(ActionEvent actionEvent) {
        model.setText("Circle");
        model.setDisabled(!model.isDisabled());
    }

    public void SquareButtonAction(ActionEvent actionEvent) {

    }

    public void init(){
        model.textProperty().bindBidirectional(textField1.textProperty());
        shapeButtonSquare.disableProperty().bind(model.disabledProperty());
    }
}
