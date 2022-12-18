import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class LinePainter extends Component implements TextListener {
    private int x = 0;

    @Override
    public void paint(Graphics g){
        g.drawLine(0 , getHeight()-10, x,0);
    }

    @Override
    public void textValueChanged(TextEvent e) {
        App.getTopLevelContainerApp(this).log(e.toString());
        if (e.getID() != TextEvent.TEXT_VALUE_CHANGED)
            return;

        try {
            x = Integer.parseInt(((TextField) e.getSource()).getText());
        }
        catch (NumberFormatException exc){
            x = 0;
        }
        finally {
            repaint();
        }
    }
}
