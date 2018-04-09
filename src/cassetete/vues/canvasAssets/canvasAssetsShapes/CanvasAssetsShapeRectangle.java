package cassetete.vues.canvasAssets.canvasAssetsShapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class CanvasAssetsShapeRectangle extends CanvasAssetsShape {


    public CanvasAssetsShapeRectangle(Color color, Canvas c) {
        super(color, c);
    }

    @Override
    public void draw() {
        gc.strokeRect(10, 10, SIZE-20, SIZE-20);
    }
}
