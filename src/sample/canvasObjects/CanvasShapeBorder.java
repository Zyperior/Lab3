package sample.canvasObjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasShapeBorder extends CanvasShapeDecorator{

    private double borderSize;
    private Color borderColor;

    public CanvasShapeBorder(double borderSize, Color borderColor, CanvasShape canvasShape) {
        super(canvasShape);
        this.borderSize = canvasShape.getWidth()+borderSize;
        this.borderColor = borderColor;
    }

    @Override
    public void draw(GraphicsContext gc) {

    }

    private void drawBorder(GraphicsContext gc){

    }
}
