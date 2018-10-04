package sample.canvasObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public  abstract class CanvasShape implements Drawable {

    private final double canvasXCoord;
    private final double canvasYCoord;
    private double width;
    private Color color;

    public CanvasShape(double canvasXCoord, double canvasYCoord, double width, Color color) {
        this.canvasXCoord = canvasXCoord;
        this.canvasYCoord = canvasYCoord;
        this.width = width;
        this.color = color;
    }

    public double getCanvasXCoord() {
        return canvasXCoord;
    }

    public double getCanvasYCoord() {
        return canvasYCoord;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }



}
