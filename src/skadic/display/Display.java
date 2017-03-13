package skadic.display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by eti22 on 12.03.2017.
 */
public class Display extends JFrame{
    private Dimension dim;
    private GOLPanel panel;

    public Display(String title, int width, int height){
        this(title, new Dimension(width, height));
    }

    public Display(String title, Dimension dim) throws HeadlessException {
        super(title);
        this.dim = dim;

        panel = new GOLPanel(dim);

        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public GOLPanel getPanel() {
        return panel;
    }

    public Dimension getDimension() {
        return dim;
    }


}
