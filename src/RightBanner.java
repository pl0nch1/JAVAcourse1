import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RightBanner extends Panel implements ItemListener {
    RectangleBanner rect;
    TriangleBanner tri;
    public RightBanner(State state) {
        setLayout(new GridLayout(2,1));
        rect = new RectangleBanner(state);
        tri = new TriangleBanner(state);
        add(rect);
        add(tri);
        rect.setEnabled(false);
    }

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
