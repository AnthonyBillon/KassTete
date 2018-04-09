package cassetete.models;

import java.awt.*;

public class MSymbol {
    private int type;
    private Point point;


    public MSymbol(int ltype, int x, int y){
        type=ltype;
        point=new Point(x,y);
    }

    public int getType() {
        return type;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
}
