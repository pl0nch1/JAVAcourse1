import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RectanglePainter extends Component implements AdjustmentListener, ItemListener {
    private int r = 0;
    private Color color;

    // Рисование прямоугольника
    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.drawRect(5,5,10+(int)((getWidth()-20)*(r/90f)),getHeight()-15);
    }

    // Обработчик изменения значения полосы прокрутки
    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        App.getTopLevelContainerApp(this).log(String.format("Увеличение длины прямоугольника до %f", e.getValue()/90f));
        r = e.getAdjustable().getValue();
        repaint();
    }

    // Обработчик изменения выбранного элемента списка
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem().equals(0)) {
            color = Color.BLACK;
        } else if(e.getItem().equals(1)) {
            color = Color.BLUE;
        } else if (e.getItem().equals(2)) {
            color = Color.DARK_GRAY;
        } else if (e.getItem().equals(3)) {
            color = Color.YELLOW;
        }
        App.getTopLevelContainerApp(this).log(String.format("Установка цвета прямоугольника %s", e.getItemSelectable().getSelectedObjects()[0].toString()));
        repaint();
    }
}
