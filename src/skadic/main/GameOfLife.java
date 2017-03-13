package skadic.main;

import skadic.display.Display;
import skadic.gamecomponents.Playfield;
import skadic.utils.Utils;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by eti22 on 12.03.2017.
 */
public class GameOfLife {

    private Display display;
    private Playfield field;

    public static GameOfLife instance;

    public GameOfLife(int fieldSize, int cellSize) {
        instance = this;
        field = new Playfield(fieldSize, cellSize);
        display = new Display("Game Of Life", new Dimension(fieldSize * cellSize, fieldSize * cellSize));
    }

    public static GameOfLife getInstance() {
        return instance;
    }

    public void render(Graphics g){
        field.render(g);
        drawHUD(g);
    }

    public void update(){
        getDisplay().getPanel().updateUI();
    }

    public Playfield getPlayfield() {
        return field;
    }

    private void drawHUD(Graphics g){
        Playfield field = GameOfLife.getInstance().getPlayfield();

        g.setFont(Font.getFont("Arial"));
        g.setColor(Color.WHITE);
        g.drawString("Interval: " + String.valueOf(field.getInterval()), 10, 10);
        g.drawString("Is Cycling: " + String.valueOf(field.isCycling()), 10, 25);

        long[] cellStates = field.countCellStates();
        g.drawString("Live Cells: " + String.valueOf(cellStates[0]), 10, 40);
        g.drawString("Dead Cells: " + String.valueOf(cellStates[1]), 10, 55);
        g.drawString("Death / Life Ratio: " + String.valueOf(Utils.round(1D * cellStates[1] / (cellStates[0] + 0.0001), 2)), 10, 70);
    }

    public Display getDisplay() {
        return display;
    }

}
