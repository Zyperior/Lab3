package sample.canvasObjects;


public abstract class CanvasShapeDecorator implements Drawable {
    private CanvasShape decoratedCanvasShape;

    public CanvasShapeDecorator(CanvasShape canvasShape){
        this.decoratedCanvasShape = canvasShape;
    }

}
