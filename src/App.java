import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.out;

public class App extends Frame{
    private State state = new State(); //
    public static void main(String[] args) {
        App singleApp = new App();
    }

    private App(){
        super();
        setSize(600,600);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weighty = 1;
        constraints.weightx = 1;

        Dimension dim = new Dimension(1000,2000);
        MiddleBanner middle = new MiddleBanner(state);
        middle.setPreferredSize(dim);
        middle.setMinimumSize(dim);
        RightBanner right = new RightBanner(state);
        right.setPreferredSize(dim);
        right.setMinimumSize(dim);
        SelectionBanner banner = new SelectionBanner(middle, right);
        banner.setPreferredSize(dim);
        banner.setMinimumSize(dim);
        add(banner, constraints);
        constraints.gridx = 1;
        add(middle, constraints);
        constraints.gridx = 2;
        add(right, constraints);
        setVisible(true);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
