package cassetete.vues;

import cassetete.models.MGame;
import cassetete.vues.canvasAssets.CanvasFigure;
import cassetete.vues.canvasAssets.canvasAssetsLines.CanvasAssetsLine;
import cassetete.vues.canvasAssets.canvasAssetsLines.LineType;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeCircle;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeRectangle;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VGridManager {
    public GridPane getGridPane() {
        return gridPane;
    }

    GridPane gridPane;
    MGame game;

    public CanvasFigure[][] getCanvasHandlers() {
        return canvasHandlers;
    }

    CanvasFigure[][] canvasHandlers;


    public VGridManager(MGame mGame) {
        gridPane = new GridPane();

        game = mGame;
        canvasHandlers = new CanvasFigure[mGame.getSize()][mGame.getSize()];
        for (int i = 0; i < canvasHandlers.length; i++) {
            for (int j = 0; j < canvasHandlers.length; j++) {
                if (mGame.getCells()[i][j] == 0 || mGame.getCells()[i][j] == 1) {
                    canvasHandlers[i][j] = new CanvasAssetsLine(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                } else if (mGame.getCells()[i][j] == 2) {
                    canvasHandlers[i][j] = new CanvasAssetsShapeCircle(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                    canvasHandlers[i][j].draw();
                } else if (mGame.getCells()[i][j] == 3) {
                    canvasHandlers[i][j] = new CanvasAssetsShapeRectangle(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                    canvasHandlers[i][j].draw();
                }
                gridPane.add(canvasHandlers[i][j].getCanvas(), j, i);
            }
        }

        mGame.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                game = (MGame) o;
                for (ArrayList<Point> line : game.getLines()) {
                    writeLine(line);
                }


            }
        });

    }

    private void writeLine(ArrayList<Point> line) {
        if (line.size() <= 1) return;
        System.out.println("paasss");
        for (int i = 0; i < line.size(); i++) {
            if (i == 0) {

            } else if (i == line.size() - 1) {

            } else {
                if (line.get(i + 1).x == line.get(i - 1).x) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setType(LineType.TOPBOT);
                    cv.draw();
                } else if (line.get(i + 1).y == line.get(i - 1).y) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setType(LineType.LEFTRIGHT);
                    cv.draw();
                }
                else if(line.get(i).x == line.get(i-1).x && line.get(i).y == line.get(i+1).y || line.get(i).y == line.get(i-1).y){
                    System.out.println("oui");
                    //if(line.get(i).x == line.get(i+1).x){
                        CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                        cv.setType(LineType.LEFTBOT);
                        cv.draw();
                    //}
                }

            }
        }
    }

}
