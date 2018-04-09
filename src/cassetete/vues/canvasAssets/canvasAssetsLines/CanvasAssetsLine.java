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
                break;
            case LEFTTOP:
                break;
            case LEFTBOT:
                break;
            case RIGHTTOP:
                break;
            case RIGHTBOT:
                break;
        }
    }

    public void setType(LineType type){
        this.type = type;
    }


}
