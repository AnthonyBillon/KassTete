package cassetete.vues.canvasAssets.canvasAssetsShapes;

import cassetete.vues.canvasAssets.CanvasFigure;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class CanvasAssetsShape extends CanvasFigure {


    protected CanvasAssetsShape(Color color, Canvas c) {
        super(color, c);
    }

    public void fromTop(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.setStroke(color);
        gc.strokeLine(SIZE/2, 0, SIZE/2, SIZE/2);
    }

    public void fromBottom(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.setStroke(color);
        gc.strokeLine(SIZE/2, SIZE, SIZE/2, SIZE/2);
    }

    public void fromLeft(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.setStroke(color);
        gc.strokeLine(SIZE/2, SIZE, SIZE/2, SIZE/2);
    }

    public void fromRight(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.setStroke(color);
        gc.strokeLine(SIZE/2, SIZE, SIZE/2, SIZE/2);
    }
}
