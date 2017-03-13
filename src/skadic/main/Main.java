package skadic.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameOfLife(70, 15));
    }
}
