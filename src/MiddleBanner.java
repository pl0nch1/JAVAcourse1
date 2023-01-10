import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MiddleBanner extends Panel implements ItemListener {
    OvalBanner oval;
    LineBanner line;
    public MiddleBanner(State state) {
        setLayout(new GridLayout(2,1));
        oval = new OvalBanner(state);
        line = new LineBanner(state);
        add(oval);
        add(line);
        oval.setEnabled(false);
        line.setEnabled(false);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        oval.setEnabled(false);
        line.setEnabled(false);

        if (e.getItem().equals("Овал")){
            oval.setEnabled(true);
        } else if(e.getItem().equals("Линия")){
            line.setEnabled(true);
        }
    }
}
