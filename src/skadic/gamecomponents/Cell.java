package skadic.gamecomponents;

import skadic.main.GameOfLife;

import java.awt.*;
import java.util.DoubleSummaryStatistics;
import java.util.Random;

/**
 * Created by eti22 on 12.03.2017.
 */
public class Cell {

    private boolean alive;
    private int size, x, y;
    private Playfield field;
    private Color cellColor;

    public Cell(Playfield field, int x, int y, boolean alive, int size) {
        this.alive = alive;
        this.size = size;
        this.field = field;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g, int x, int y){
        int displaySize = GameOfLife.getInstance().getDisplay().getWidth();

        Color cellColor = new Color(
                (int)(1D * x / displaySize * 255),
                (int)(1D * (x - displaySize) * (y - displaySize) / Math.pow(displaySize, 2) * 255),
                (int)(1D * y / displaySize * 255));

        g.setColor(isAlive() ? cellColor : Color.BLACK);
        g.fillRect(x, y, size, size);

        if(size > 10) {
            g.setColor(Color.DARK_GRAY);
            g.drawRect(x, y, size, size);
            g.setColor(!isAlive() ? cellColor : Color.BLACK);
            if (field.getNextState(x / size, y / size) != this.alive)
                g.fillRect((int) Math.round(x + size / 4D), (int) Math.round(y + size / 4D), (int) Math.round(size / 2D), (int) Math.round(size / 2D));
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void switchState(){
        alive = !alive;
    }

    public int getSize() {
        return size;
    }
}
