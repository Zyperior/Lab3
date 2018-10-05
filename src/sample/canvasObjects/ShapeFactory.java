package sample.canvasObjects;

import javafx.scene.paint.Color;

public class ShapeFactory {

    public static CanvasShape getShape(ShapeType type, double xCoord, double yCoord, double width, Color color){

        return new CanvasShape(type, xCoord, yCoord, width, color);

    }
}
