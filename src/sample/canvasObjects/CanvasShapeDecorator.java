package sample.canvasObjects;


public abstract class CanvasShapeDecorator implements Drawable {
    protected CanvasShape decoratedCanvasShape;

    public CanvasShapeDecorator(CanvasShape canvasShape){
        this.decoratedCanvasShape = canvasShape;
    }

}
