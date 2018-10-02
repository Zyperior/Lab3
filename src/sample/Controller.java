package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class Controller {

    public void buttonAction(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).setText("Clicked");
    }
}
