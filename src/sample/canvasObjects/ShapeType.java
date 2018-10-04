package sample.canvasObjects;

public enum ShapeType {

    CIRCLE("Circle"),SQUARE("Square");

    String name;

    ShapeType(String name){
        this.name = name;
    }

    public String getName(){return name;}
}
