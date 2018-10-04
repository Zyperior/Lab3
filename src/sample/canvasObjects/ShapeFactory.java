package sample.canvasObjects;

import javafx.scene.paint.Color;

public class ShapeFactory {

    public static CanvasShape getShape(ShapeType type, double xCoord, double yCoord, double width, Color color){

        switch(type){
            case CIRCLE:
                return new CanvasCircle(xCoord,yCoord,width,color);
            case SQUARE:
                return new CanvasSquare(xCoord,yCoord,width,color);

        }

        return null;
    }
}
