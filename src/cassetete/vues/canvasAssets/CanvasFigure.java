package cassetete.vues.canvasAssets;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public abstract class CanvasFigure {
    public static final int SIZE = 100      ;

    protected Canvas canvas;
    protected Color color;
    protected GraphicsContext gc;

    protected CanvasFigure(Color color, Canvas c){
        canvas = c;
        this.color = color;
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(color);
        gc.setLineWidth(1);
        gc.strokeRect(0,0, SIZE, SIZE);
        gc.setLineWidth(5);
    }

    public void draw(){
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(0,0, SIZE, SIZE);
        gc.setLineWidth(10);
        gc.setStroke(color);
    };

    public void clear(){
        gc.clearRect(0,0, SIZE, SIZE);
    }
    public Canvas getCanvas(){
        return canvas;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color c) {
        color=c;
    }
}
