package sample.canvasObjects;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;

public class CanvasShape implements Drawable {

    private final ShapeType shapeType;
    private final Point2D point;
    private double width;
    private Color color;
    private Color strokeColor;
    private boolean selected;

    public CanvasShape(ShapeType shapeType, double canvasXCoord, double canvasYCoord, double width, Color color) {
        this.shapeType = shapeType;
        this.point = new Point2D(canvasXCoord,canvasYCoord);
        this.width = width;
        this.color = color;
        this.selected = false;

        if(color.getRed()<0.5){
            strokeColor = color.GREENYELLOW;
        }
        else{
            strokeColor = color.GREEN;
        }
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public Point2D getPoint() {
        return point;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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


    @Override
    public void draw(GraphicsContext gc) {

        gc.setFill(color);

        switch(shapeType){
            case CIRCLE:
                gc.fillOval(point.getX(),point.getY(),width,width);
                break;

            case SQUARE:
                gc.fillRect(point.getX(),point.getY(),width,width);

        }

        drawSelectionMode(gc);

    }

    private void drawSelectionMode(GraphicsContext gc){

        if(selected){
            gc.setStroke(strokeColor);
            gc.setLineWidth(3);
            switch(shapeType){
                case CIRCLE:
                    gc.strokeOval(point.getX(),point.getY(),width,width);
                    break;

                case SQUARE:
                    gc.strokeRect(point.getX(),point.getY(),width,width);

            }
        }




    }
}
