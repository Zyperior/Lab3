package sample.filesaving;


import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveToBmp {

    private double imageWidth;
    private double imageHeight;
    private WritableImage writableImage;
    private File file;
    private Stage stage;

    public SaveToBmp(double imageWidth, double imageHeight, WritableImage writableImage, File file, Stage stage) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.writableImage = writableImage;
        this.file = file;
        this.stage = stage;
    }
}
