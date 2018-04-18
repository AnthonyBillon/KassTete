package cassetete.vues.canvasAssets.canvasAssetsShapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

import java.awt.*;

public class CanvasAssetsShapeGenerical extends CanvasAssetsShape
{
    private static boolean first = true;

    private int nbOfEdges;
    public CanvasAssetsShapeGenerical(Color color, Canvas c,int edges) {
        super(color, c);

        nbOfEdges = edges;

    }

    public void draw(){
        super.draw();


        //désolé un peu de calcul, on fait plus objet mais ça ne tuera personne
        Point[] lp = new Point[nbOfEdges];
        for(int i = 0; i<lp.length; i++){
            int x = (int)(Math.sin(((double)i/(double)nbOfEdges)*2*Math.PI )*((SIZE-12)/2 -4));
            int y = (int)(Math.cos(((double)i/(double)nbOfEdges)*2*Math.PI )*((SIZE-12)/2 -4));
            lp[i]=new Point(x,y);
        }
        Point p2;
        for(int i = 0; i<lp.length; i++){
            Point p1 = lp[i];
            if(i==lp.length-1){
             p2 = lp[0];
            }else {
                p2 = lp[i+1];
            }

            gc.strokeLine(p1.x+50, p1.y+50, p2.x+50, p2.y+50);
        }
    }

}
