import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RectanglePainter extends Component implements AdjustmentListener, ItemListener {
    private int r = 0;
    private Color color;

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        g.drawRect(5,5,10+(int)((getWidth()-20)*(r/90f)),getHeight()-15);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        App.getTopLevelContainerApp(this).log(e.toString());
        r = e.getAdjustable().getValue();
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        App.getTopLevelContainerApp(this).log(e.toString());
        if (e.getItem().equals(0)) {
            color = Color.BLACK;
        } else if(e.getItem().equals(1)) {
            color = Color.BLUE;
        } else if (e.getItem().equals(2)) {
            color = Color.DARK_GRAY;
        } else if (e.getItem().equals(3)) {
            color = Color.YELLOW;
        }
        repaint();
    }
}
