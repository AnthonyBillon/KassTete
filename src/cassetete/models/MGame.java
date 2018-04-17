package cassetete.models;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Observable;

public class MGame extends Observable implements Serializable {
    public ArrayList<ArrayList<Point>> getLines() {
        return lines;
    }

    public ArrayList<Point> getCurrentLine() {
        return currentLine;
    }

    private ArrayList<ArrayList<Point>> lines;

    private ArrayList<Integer> alreadyConnected;

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

    private MTimer mTimer;

    private boolean isWon;


    public MGame(int size, ArrayList<MSymbol> symbs) {
        this.size = size;
        cells = new int[size][size];
        ArrayList<MSymbol> symbols = symbs;
        lines = new ArrayList<>();
        alreadyConnected = new ArrayList<>();
        for (MSymbol m : symbols) {
            cells[m.getPoint().y][m.getPoint().x] = m.getType();
        }

        printGrid();
        isWon = false;

        mTimer=new MTimer();

        save();
    }

    public boolean writeLinePart(Point p) {
        if (currentLine == null) return false;
        if (!isNear(p)){
            razLine();
            return false;
        }

        if (cells[p.y][p.x] == 0) {
            currentLine.add(p);
            cells[p.y][p.x] = 1;
            printGrid();
            previousPoint = p;
            setChanged();
            notifyObservers();
        } else if (cells[p.y][p.x] == cells[currentLine.get(0).y][currentLine.get(0).x]) {
            currentLine.add(p);
            lines.add(currentLine);
            alreadyConnected.add(cells[currentLine.get(0).y][currentLine.get(0).x]);
            currentLine = null;
            if(checkIfVictory()) {
                stop();
                isWon=true;
            }
            setChanged();
            notifyObservers();

        } else {
            razLine();
            currentLine = null;
            setChanged();
            notifyObservers();
        }
        return false;
    }



    public void beginLine(Point p) {
        if(currentLine != null) razLine();
        for (Integer i : alreadyConnected) {
            if (cells[p.y][p.x] == i) return;
        }
        if (cells[p.y][p.x] >= 2) {
            ArrayList<Point> l = new ArrayList<>();
            currentLine = l;
            l.add(p);
            previousPoint = p;
            setChanged();
            notifyObservers();
        }

    }

    public void endLine(){
        razLine();
        currentLine=null;
        setChanged();
        notifyObservers();
    }

    private boolean checkIfVictory() {
        for (int[] cell : cells) {
            for (int j = 0; j < cells.length; j++) {
                if (cell[j] == 0) return false;
            }
        }
        return true;
    }

    private void printGrid() {
        for (int[] cell : cells) {
            for (int j = 0; j < cells.length; j++) {
                System.out.print(" " + cell[j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private boolean isNear(Point p) {
        return (p.x == previousPoint.x && p.y == previousPoint.y - 1) ||
                (p.x == previousPoint.x && p.y == previousPoint.y + 1) ||
                (p.x == previousPoint.x - 1 && p.y == previousPoint.y) ||
                (p.x == previousPoint.x + 1 && p.y == previousPoint.y);
    }

    private void razLine(){
        if(currentLine != null) {
            for (int i = 1; i < currentLine.size(); i++) {
                cells[currentLine.get(i).y][currentLine.get(i).x] = 0;
                printGrid();
            }
        }
    }

    public void back(){
        if(lines.size()>0){
            razLine();
            alreadyConnected.remove(alreadyConnected.size()-1);
            ArrayList<Point> p = lines.get(lines.size()-1);
            for (int i = 1; i<p.size()-1; i++)cells[p.get(i).y][p.get(i).x] = 0;

            lines.remove(lines.get(lines.size()-1));
            setChanged();
            notifyObservers();
        }
    }

    public void save(){
        ObjectOutputStream oos = null;
        try {


            FileOutputStream fos = new FileOutputStream("save.ser");
            System.out.println("Saved");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
        }
        catch (IOException e){
            e.printStackTrace();

        } finally {
          if(oos != null) {
              try {
                  oos.flush();
                  oos.close();
              } catch (IOException e) {
                  //e.printStackTrace();
              }

          }
        }
    }

    public void start(){
        mTimer.start();
    }

    public void stop(){
        mTimer.stop();
    }

    public MTimer getmTimer(){
        return mTimer;
    }

    public Boolean getIsWon(){
        return isWon;
    }


}
