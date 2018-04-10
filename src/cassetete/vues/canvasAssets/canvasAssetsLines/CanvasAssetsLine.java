package cassetete.vues.canvasAssets.canvasAssetsLines;

import cassetete.vues.canvasAssets.CanvasFigure;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CanvasAssetsLine extends CanvasFigure {
    protected LineType type;

    public CanvasAssetsLine(Color color, Canvas c) {
        super(color, c);
    }

    @Override
    public void draw() {
        switch (type){
            case LEFTRIGHT:
                gc.strokeLine(0, SIZE/2, SIZE, SIZE/2);
                break;
            case TOPBOT:
                gc.strokeLine(SIZE/2, 0, SIZE/2, SIZE);
                break;
            case LEFTTOP:
                gc.strokeLine(0, SIZE/2, SIZE/2, SIZE/2);
                gc.strokeLine(SIZE/2, SIZE/2, SIZE/2, 0);
                break;
            case LEFTBOT:
                gc.strokeLine(0, SIZE/2, SIZE/2, SIZE/2);
                gc.strokeLine(SIZE/2, SIZE/2, SIZE/2, SIZE);
                break;
            case RIGHTTOP:
                gc.strokeLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
                gc.strokeLine(SIZE/2, SIZE/2, SIZE/2, 0);
                break;
            case RIGHTBOT:
                gc.strokeLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
                gc.strokeLine(SIZE/2, SIZE/2, SIZE/2, SIZE);
                break;
            case LEFTMID:
                gc.strokeLine(0, SIZE/2, SIZE/2, SIZE/2);
                break;
            case RIGHTMID:
                gc.strokeLine(SIZE, SIZE/2, SIZE/2, SIZE/2);
                break;
            case TOPMID:
                gc.strokeLine(SIZE/2, SIZE/2, SIZE/2, 0);
                break;
            case BOTMID:
                gc.strokeLine(SIZE/2, SIZE/2, SIZE/2, SIZE);
                break;
        }
    }

    public void setType(LineType type){
        this.type = type;
    }


}
