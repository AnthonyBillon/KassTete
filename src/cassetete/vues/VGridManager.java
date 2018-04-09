package cassetete.vues;

import cassetete.models.MGame;
import cassetete.vues.canvasAssets.CanvasFigure;
import cassetete.vues.canvasAssets.canvasAssetsLines.CanvasAssetsLine;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeCircle;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeRectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Observable;
import java.util.Observer;

public class VGridManager implements Observer {
    public GridPane getGridPane() {
        return gridPane;
    }

    GridPane gridPane;
    MGame game;

    public CanvasFigure[][] getCanvasHandlers() {
        return canvasHandlers;
    }

    CanvasFigure[][] canvasHandlers;


    public VGridManager(MGame mGame){
        gridPane = new GridPane();

        game=mGame;
        canvasHandlers = new CanvasFigure[mGame.getSize()][mGame.getSize()];
        for(int i = 0; i<canvasHandlers.length; i++){
            for (int j = 0; j<canvasHandlers.length; j++){
                if(mGame.getCells()[i][j] == 0 || mGame.getCells()[i][j]== 1){
                    canvasHandlers[i][j]= new CanvasAssetsLine(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                }
                else if(mGame.getCells()[i][j] == 2 ){
                    canvasHandlers[i][j]= new CanvasAssetsShapeCircle(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                    canvasHandlers[i][j].draw();
                }
                else if(mGame.getCells()[i][j] == 3 ){
                    canvasHandlers[i][j]= new CanvasAssetsShapeRectangle(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                    canvasHandlers[i][j].draw();
                }
                gridPane.add(canvasHandlers[i][j].getCanvas(), i, j);
            }
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        if(o.equals(game)){

        }
    }
}
