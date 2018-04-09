package cassetete.models;


import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class MGame extends Observable {
    private ArrayList<MSymbol> symbols;
    private ArrayList<ArrayList<Point>> lines;

    public int[][] getCells() {
        return cells;
    }

    private int[][] cells;

    public int getSize() {
        return size;
    }

    private int size;

    private Point previousPoint;

    private ArrayList<Point> currentLine;


    public MGame(int size, ArrayList<MSymbol> symbs) {
        this.size = size;
        cells = new int[size][size];
        symbols = symbs;
        for (MSymbol m : symbols) {
            cells[m.getPoint().x][m.getPoint().y] = m.getType();
        }
    }

    public boolean writeLinePart(Point p) {
        if (cells[p.x][p.y] == 0) {
            currentLine.add(p);
            cells[p.x][p.y] = 1;
            notify();
            return true;
        } else if (p.equals(currentLine.get(0))) {
            currentLine.add(p);
            lines.add(currentLine);
            currentLine = null;

            return checkIfVictory();

        } else {
            currentLine = null;
        }
        return false;
    }

    public void beginLine(Point p) {
        if (cells[p.x][p.y] >= 2) {
            ArrayList<Point> l = new ArrayList<>();
            currentLine = l;
            l.add(p);
        }
    }

    private boolean checkIfVictory() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                if (cells[i][j] == 0) return false;
            }
        }
        return true;
    }


}
