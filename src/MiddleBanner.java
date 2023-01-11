import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MiddleBanner extends Panel implements ItemListener {
    OvalBanner oval;
    LineBanner line;
    State state;
    public MiddleBanner(State state) {
        setLayout(new GridLayout(2,1));
        this.state = state;
        oval = new OvalBanner(state); // Проброс объекта состояния в баннеры
        line = new LineBanner(state); // Проброс объекта состояния в баннеры
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
