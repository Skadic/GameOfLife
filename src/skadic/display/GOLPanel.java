package skadic.display;

import skadic.gamecomponents.Playfield;
import skadic.input.GOLKeyAdapter;
import skadic.input.GOLMouseAdapter;
import skadic.main.GameOfLife;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eti22 on 12.03.2017.
 */
public class GOLPanel extends JPanel {

    private Dimension dim;

    public GOLPanel(int width, int height){
        this(new Dimension(width, height));
    }

    public GOLPanel(Dimension dim) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.DARK_GRAY);
        this.dim = dim;

        addMouseListener(new GOLMouseAdapter());
        addKeyListener(new GOLKeyAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameOfLife.getInstance().render(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return dim;
    }
}
