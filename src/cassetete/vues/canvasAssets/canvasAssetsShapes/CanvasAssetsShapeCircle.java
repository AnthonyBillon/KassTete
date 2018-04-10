package cassetete.vues.canvasAssets.canvasAssetsShapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.awt.*;

public class CanvasAssetsShapeCircle extends CanvasAssetsShape {


    public CanvasAssetsShapeCircle(Color color, Canvas c) {
        super(color, c);
    }

    @Override
    public void draw() {
        super.draw();
        gc.strokeOval(10, 10, SIZE-20, SIZE-20);
    }
}
