import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RightBanner extends Panel implements ItemListener {
    RectangleBanner rect;
    TriangleBanner tri;
    public RightBanner() {
        setLayout(new GridLayout(2,1));// Назначение менеджера компоновки
        rect = new RectangleBanner(); // Создание баннера прямоугольника
        tri = new TriangleBanner(); // Создание баннера треугольника
        add(rect);
        add(tri);
        rect.setEnabled(false);
    }

    // Обработка события выбора радио-кнопки рисования
    @Override
    public void itemStateChanged(ItemEvent e) {
        rect.setEnabled(false);
        tri.setEnabled(false);

        if (e.getItem().equals("Прямоугольник")){
            rect.setEnabled(true);
        } else if(e.getItem().equals("Треугольник")){
            tri.setEnabled(true);
        }
    }
}
