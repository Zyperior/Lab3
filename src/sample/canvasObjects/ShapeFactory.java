package sample.canvasObjects;

import javafx.scene.paint.Color;

public class ShapeFactory {

    public CanvasShape getShape(ShapeType type, double xCoord, double yCoord, double width, Color color){
        switch(type){
            case CIRCLE:
                return new Circle(xCoord,yCoord,width,color);
            case SQUARE:
                return new Square(xCoord,yCoord,width,color);

        }

        return null;
    }
}
