import java.awt.*;
import java.awt.event.*;

public class OvalPainter extends Component implements AdjustmentListener, ItemListener {
    private int r = 0;
    private Color color;

    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.drawRect(0,0,getWidth()-1,getHeight()-1);
        g.setColor(color);
        g.drawOval(0,0, 10 + (int) ((getWidth()-10)*r/90.0f), 10 + (int) ((getHeight()-10)*r/90.0f));
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
        if (e.getItem() == "Розовый"){
            color = Color.PINK;
        } else if (e.getItem() == "Чёрный") {
            color = Color.BLACK;
        }
        repaint();
    }
}
