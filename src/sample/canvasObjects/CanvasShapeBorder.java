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

    /**
     *
     * @param canvasShape Constructor with only this parameter sets border default to negative and color to black.
     */

    public CanvasShapeBorder(CanvasShape canvasShape){
        this(-2, Color.BLACK,canvasShape);
    }

    @Override
    public void draw(GraphicsContext gc) {
        drawBorder(gc);
        decoratedCanvasShape.draw(gc);
    }

    private void drawBorder(GraphicsContext gc){
        CanvasShape borderShape = ShapeFactory.getShape(
                decoratedCanvasShape.getShapeType(),
                decoratedCanvasShape.getPoint().getX(),
                decoratedCanvasShape.getPoint().getY(),
                borderSize,
                borderColor);

        borderShape.draw(gc);
    }
}
