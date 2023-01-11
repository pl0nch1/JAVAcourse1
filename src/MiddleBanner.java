import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MiddleBanner extends Panel implements ItemListener {
    OvalBanner oval;
    LineBanner line;
    public MiddleBanner() {
        setLayout(new GridLayout(2,1));
        oval = new OvalBanner(); // Создание баннера овала
        line = new LineBanner(); // СОздание баннера линии
        add(oval);
        add(line);
        oval.setEnabled(false);
        line.setEnabled(false);
    }

    // Обработка события выбора радио-кнопки рисования
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
