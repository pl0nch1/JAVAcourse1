import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class TrianglePainter extends Component implements ActionListener {
    private boolean up = false;

    @Override
    public void paint(Graphics g){
        float multiplier = 1f;
        if (!up){
            multiplier /= 2;
        }
        g.drawLine(0 , getHeight(), getWidth()/2, (int) (getHeight() * (1-multiplier)));
        g.drawLine(getWidth() , getHeight(), getWidth()/2, (int) (getHeight() * (1-multiplier)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        String text = button.getLabel();
        up = text.equals("Максимум");
        if (up){
            App.getTopLevelContainerApp(this).log("Максимизация треугольника");
        }
        else{
            App.getTopLevelContainerApp(this).log("Минимизация треугольника");
        }
        repaint();
    }
}
