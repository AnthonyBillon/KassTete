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
        lines = new ArrayList<>();
        for (MSymbol m : symbols) {
            cells[m.getPoint().y][m.getPoint().x] = m.getType();
        }

        printGrid();
    }

    public boolean writeLinePart(Point p) {
        if(currentLine==null) return false;
        if (cells[p.y][p.x] == 0) {
            currentLine.add(p);
            cells[p.y][p.x] = 1;
            System.out.println("ça passe");
            printGrid();
            setChanged();
            notifyObservers();
        }
        else if(cells[p.y][p.x] == cells[currentLine.get(0).y][currentLine.get(0).x]){
            System.out.println("connected");
        }
        else {
            for(Point point : currentLine){
                cells[point.y][point.x]=0;
                System.out.println("remise à Z");
                printGrid();
            }
            currentLine = null;
            setChanged();
            notifyObservers();
        }
        return false;
    }

    public void beginLine(Point p) {
        if (cells[p.y][p.x] >= 2) {
            ArrayList<Point> l = new ArrayList<>();
            currentLine = l;
            l.add(p);
            setChanged();
            notifyObservers();

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

    private void printGrid(){
        for(int i = 0; i<cells.length; i++){
            for(int j=0; j<cells.length; j++){
                System.out.print(" "+cells[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
