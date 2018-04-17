package cassetete.vues;

import cassetete.models.MGame;
import cassetete.vues.canvasAssets.CanvasFigure;
import cassetete.vues.canvasAssets.canvasAssetsLines.CanvasAssetsLine;
import cassetete.vues.canvasAssets.canvasAssetsLines.LineType;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShape;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeCircle;
import cassetete.vues.canvasAssets.canvasAssetsShapes.CanvasAssetsShapeRectangle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VGridManager {
    public GridPane getGridPane() {
        return gridPane;
    }

    private GridPane gridPane;
    private MGame game;


    public CanvasFigure[][] getCanvasHandlers() {
        return canvasHandlers;
    }

    private CanvasFigure[][] canvasHandlers;


    public VGridManager(MGame mGame) {
        gridPane = new GridPane();

        game = mGame;
        canvasHandlers = new CanvasFigure[mGame.getSize()][mGame.getSize()];
        for (int i = 0; i < canvasHandlers.length; i++) {
            for (int j = 0; j < canvasHandlers.length; j++) {
                if (mGame.getCells()[i][j] == 0 || mGame.getCells()[i][j] == 1) {
                    canvasHandlers[i][j] = new CanvasAssetsLine(Color.BLACK, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                } else if (mGame.getCells()[i][j] == 2) {
                    canvasHandlers[i][j] = new CanvasAssetsShapeCircle(Color.BROWN, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                    canvasHandlers[i][j].draw();
                } else if (mGame.getCells()[i][j] == 3) {
                    canvasHandlers[i][j] = new CanvasAssetsShapeRectangle(Color.GOLD, new Canvas(CanvasFigure.SIZE, CanvasFigure.SIZE));
                    canvasHandlers[i][j].draw();
                }
                gridPane.add(canvasHandlers[i][j].getCanvas(), j, i);
            }
        }

        mGame.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                game = (MGame) o;
                for (int i = 0; i < canvasHandlers.length; i++) {
                    for (int j = 0; j < canvasHandlers[i].length; j++) {
                        canvasHandlers[i][j].clear();
                        canvasHandlers[i][j].draw();
                    }
                }
                for (ArrayList<Point> line : game.getLines()) {
                    writeLine(line, false);
                }
                if (game.getCurrentLine() != null) {
                    writeLine(game.getCurrentLine(), true);
                }
                if(game.getIsWon()){
                    Stage dialog = new Stage();
                    dialog.initOwner(gridPane.getScene().getWindow());
                    GridPane p = new GridPane();
                    p.setAlignment(Pos.CENTER);
                    Label l = new Label("Vous avez gagné");
                    l.setFont(new Font("Sans", 30));
                    p.add(l, 0 , 0);
                    p.setPrefSize(500, 200);
                    Scene ms = new Scene(p);
                    dialog.setScene(ms);
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.showAndWait();

                }
            }

        });


    }

    private void writeLine(ArrayList<Point> line, boolean isTheCurrentLine) {
        if (line.size() <= 1) return;
        for (int i = 0; i < line.size(); i++) {
            Point cur = line.get(i);
            if (i == 0) {

                if (cur.x == line.get(i + 1).x + 1) {
                    CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                    cv.fromLeft();
                } else if (cur.x == line.get(i + 1).x - 1) {
                    CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                    cv.fromRight();
                } else if (cur.y == line.get(i + 1).y + 1) {
                    CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                    cv.fromTop();

                } else if (cur.y == line.get(i + 1).y - 1) {
                    CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                    cv.fromBottom();


                }

            } else if (i == line.size() - 1) {
                if (!isTheCurrentLine) {

                    if (cur.x == line.get(i - 1).x + 1) {
                        CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                        cv.fromLeft();
                    } else if (cur.x == line.get(i - 1).x - 1) {
                        CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                        cv.fromRight();
                    } else if (cur.y == line.get(i - 1).y + 1) {
                        CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                        cv.fromTop();
                    } else if (cur.y == line.get(i - 1).y - 1) {
                        CanvasAssetsShape cv = (CanvasAssetsShape) canvasHandlers[cur.y][cur.x];
                        cv.fromBottom();
                    }
                } else {

                    if (cur.x == line.get(i - 1).x + 1) {
                        CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                        cv.setType(LineType.LEFTMID);
                        cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                        cv.draw();
                    } else if (cur.x == line.get(i - 1).x - 1) {
                        CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                        cv.setType(LineType.RIGHTMID);
                        cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                        cv.draw();
                    } else if (cur.y == line.get(i - 1).y + 1) {
                        CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                        cv.setType(LineType.TOPMID);
                        cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                        cv.draw();
                    } else if (cur.y == line.get(i - 1).x - 1) {
                        CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                        cv.setType(LineType.BOTMID);
                        cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                        cv.draw();
                        ;
                    }

                }
            } else {
                Point src = line.get(i - 1);
                Point dst = line.get(i + 1);
                if (src.x == dst.x) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];

                    cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                    cv.setType(LineType.TOPBOT);
                    cv.draw();
                } else if (src.y == dst.y) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                    cv.setType(LineType.LEFTRIGHT);
                    cv.draw();
                } else if (cur.x == dst.x + 1 && cur.y == src.y + 1 || cur.x == src.x + 1 && cur.y == dst.y + 1) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                    cv.setType(LineType.LEFTTOP);
                    cv.draw();
                } else if (cur.x == dst.x - 1 && cur.y == src.y - 1 || cur.x == src.x - 1 && cur.y == dst.y - 1) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                    cv.setType(LineType.RIGHTBOT);
                    cv.draw();
                } else if (cur.x == dst.x + 1 && cur.y == src.y - 1 || cur.x == src.x + 1 && cur.y == dst.y - 1) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                    cv.setType(LineType.LEFTBOT);
                    cv.draw();
                } else if (cur.x == dst.x - 1 && cur.y == src.y + 1 || cur.x == src.x - 1 && cur.y == dst.y + 1) {
                    CanvasAssetsLine cv = (CanvasAssetsLine) canvasHandlers[line.get(i).y][line.get(i).x];
                    cv.setColor(canvasHandlers[line.get(0).y][line.get(0).x].getColor());
                    cv.setType(LineType.RIGHTTOP);
                    cv.draw();
                }

            }
        }
    }

}
