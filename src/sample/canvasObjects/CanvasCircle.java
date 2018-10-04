package sample.canvasObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasCircle extends CanvasShape {

    public CanvasCircle(double canvasXCoord, double canvasYCoord, double width, Color color) {
        super(canvasXCoord, canvasYCoord, width, color);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillOval(this.getCanvasXCoord(),this.getCanvasYCoord(),this.getWidth(),this.getWidth());
    }
}