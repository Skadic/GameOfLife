package skadic.input;

import skadic.gamecomponents.Cell;
import skadic.gamecomponents.Playfield;
import skadic.main.GameOfLife;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

/**
 * Created by eti22 on 12.03.2017.
 */
public class GOLMouseAdapter extends MouseAdapter{

    private final Playfield field;

    public GOLMouseAdapter() {
        this.field = GameOfLife.getInstance().getPlayfield();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        //Shift Ctrl
        if (e.isShiftDown() && !e.isAltDown() && e.isControlDown()) {
            field.stopCycle();
            field.fillRandom();

            //Control
        }else if (!e.isShiftDown() && !e.isAltDown() && e.isControlDown()) {
            field.stopCycle();
            field.clear();

            //Alt
        }else if (!e.isShiftDown() && e.isAltDown() && !e.isControlDown()) {
            if (field.isCycling()) {
                field.stopCycle();
            } else {
                field.startCycle();
            }

            //Shift
        } else if (e.isShiftDown() && !e.isAltDown() && !e.isControlDown()) {
            field.cycle();
        } else {
            Cell clickedCell = getCellByCoordinate(e.getX(), e.getY());
            if (clickedCell != null)
                clickedCell.switchState();
        }

        GameOfLife.getInstance().update();
    }



    private Cell getCellByCoordinate(int x, int y){
        if(
                x >= 0 &&
                x <= field.getCellSize() * field.getFieldSize() &&
                y >= 0 &&
                y <= field.getCellSize() * field.getFieldSize()){

            int cellX = x / field.getCellSize();
            int cellY = y / field.getCellSize();

            System.out.println("cellX = " + cellX);
            System.out.println("cellY = " + cellY);
            System.out.println("field.getCell(cellX, cellY).isAlive() = " + field.getCell(cellX, cellY).isAlive() + '\n');

            return field.getCell(cellX, cellY);
        }
        return null;
    }
}
