package skadic.input;

import skadic.gamecomponents.Playfield;
import skadic.main.GameOfLife;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by eti22 on 13.03.2017.
 */
public class GOLKeyAdapter extends KeyAdapter{

    private Playfield field;

    public GOLKeyAdapter() {
        this.field = GameOfLife.getInstance().getPlayfield();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                field.incrInterval(10);
                break;

            case KeyEvent.VK_DOWN:
                field.incrInterval(-10);
                break;
        }
        GameOfLife.getInstance().update();
    }
}
