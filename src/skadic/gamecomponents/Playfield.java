package skadic.gamecomponents;

import skadic.main.GameOfLife;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by eti22 on 12.03.2017.
 */
public class Playfield {

    private volatile Cell[][] cells;
    private int fieldSize, cellSize;
    private boolean cycling;
    private int interval;

    public Playfield(int fieldSize, int cellSize){
        cells = new Cell[fieldSize][fieldSize];
        this.fieldSize = cells.length;
        this.cellSize = cellSize;
        this.cycling = false;
        interval = 50;

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells.length; x++) {
                cells[x][y] = new Cell(this, x, y, new Random().nextBoolean(), cellSize);
            }
        }
    }

    public void render(Graphics g){
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells.length; x++) {
                Cell currentCell = getCell(x, y);
                currentCell.render(g, x * currentCell.getSize(), y * currentCell.getSize());
            }
        }
    }

    public void startCycle() {
        Executors.newSingleThreadExecutor().submit(() -> {
                    if (!cycling) {
                        cycling = true;
                        while (cycling) {
                            try {
                                cycle();
                                TimeUnit.MILLISECONDS.sleep(interval);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }

    public void stopCycle(){
        if(cycling){
            cycling = false;
        }
    }

    public void cycle() {
        Playfield newPlayfield = new Playfield(fieldSize, cellSize);

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells.length; x++) {
                newPlayfield.getCell(x, y).setAlive(getNextState(x, y));
            }
        }
        cells = newPlayfield.getCells();
        GameOfLife.getInstance().update();
    }

    public boolean isCycling() {
        return cycling;
    }

    public boolean getNextState(int xPos, int yPos){
        byte aliveNeighbors = getNeighbors(xPos, yPos);

        if(!getCell(xPos, yPos).isAlive() && aliveNeighbors == 3) return true;
        else if(getCell(xPos, yPos).isAlive() && aliveNeighbors >= 2 && aliveNeighbors <= 3) return true;
        return false;
    }

    public byte getNeighbors(int xPos, int yPos){
        byte aliveNeighbors = 0;
        try {
            for (int y = Math.max(0, yPos - 1); y < Math.min(fieldSize, yPos + 2); y++) {
                for(int x = Math.max(0, xPos - 1); x < Math.min(fieldSize, xPos + 2); x++)
                if (getCell(x, y).isAlive() && !getCell(x, y).equals(getCell(xPos, yPos))) aliveNeighbors++;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return aliveNeighbors;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public Cell[][] getCells() {
        return cells.clone();
    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }


    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = Math.max(10, interval);
        if(isCycling()){
            stopCycle();
            startCycle();
        }
    }

    public void incrInterval(int interval) {
        this.interval = Math.max(10, this.interval + interval);
        if(isCycling()){
            stopCycle();
            startCycle();
        }
    }

    public void clear(){
        for (Cell[] cellArray : cells) {
            for (Cell cell : cellArray) {
                cell.setAlive(false);
            }
        }
    }

    public void fillRandom(){
        for (Cell[] cellArray : cells) {
            for (Cell cell : cellArray) {
                cell.setAlive(new Random().nextBoolean());
            }
        }
    }

    public long[] countCellStates(){
        long[] cellStates = {0, 0};
        for (Cell[] cellArray : cells) {
            for (Cell cell : cellArray) {
                if(cell.isAlive())
                    cellStates[0]++;
                else
                    cellStates[1]++;
            }
        }
        return cellStates;
    }

}
