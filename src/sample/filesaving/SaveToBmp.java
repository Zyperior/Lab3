package sample.filesaving;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.io.*;
import java.nio.file.Files;

public class SaveToBmp {

    private WritableImage writableImage;
    private File file;
    private Window window;
    private Canvas canvas;

    public SaveToBmp(int imageWidth, int imageHeight,Canvas canvas, Window window) {
        this.writableImage = new WritableImage(imageWidth,imageHeight);
        this.canvas = canvas;
        this.window = window;
    }

    public WritableImage getWritableImage() {
        return writableImage;
    }
    public void setWritableImage(WritableImage writableImage) {
        this.writableImage = writableImage;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public Window getStage() {
        return window;
    }
    public void setStage(Window window) {
        this.window = window;
    }

    public void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save image as Bitmap");
        fileChooser.setInitialFileName("newPicture.png");
        file = fileChooser.showSaveDialog(window);
        writableImage = canvas.snapshot(null,writableImage);

        try (DataOutputStream outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)))){

            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), ".png", outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
