package sample.canvasObjects;

public enum ShapeType {

    CIRCLE("CanvasCircle"),SQUARE("CanvasSquare");

    String name;

    ShapeType(String name){
        this.name = name;
    }

    public String getName(){return name;}
}
