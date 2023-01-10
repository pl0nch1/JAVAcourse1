import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RectanglePainter extends Component implements AdjustmentListener, ItemListener, Runnable {
    private int r = 0;
    private int prop = 0;
    private Color color;
    private State state;

    @Override
    public void paint(Graphics g){
        g.setColor(color);
        int width = 10+(int)((getWidth()-20)*(r/90f));
        int height = getHeight()-15;
        g.drawRect(5,5, width, height);
        if (prop < 50){
            g.drawLine(5 + (int) (prop / 50.0f * width),5 ,5,5 + (int) (prop / 50.0f * height));
        } else {
            g.drawLine(5 + (int) ((prop-50) / 50.0f * width),5 + height,
                    5 + width,5 + (int) ((prop-50) / 50.0f * height));
        }
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        App.getTopLevelContainerApp(this).log(String.format("Увеличение длины прямоугольника до %f", e.getValue()/90f));
        r = e.getAdjustable().getValue();
        repaint();
    }

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


    public RectanglePainter(State state){
        super();
        this.state = state;
        new Thread(this).start();
    }


    @Override
    public void run() {
        try {
            while (true) {
                synchronized (state){
                    while (state.getNum() == 3){
                        prop = (prop + 1) % 100;
                        repaint();
                        Thread.sleep(10);
                        if (prop == 0)
                            state.next();
                    }
                    state.notifyAll();
                    while (state.getNum() != 3){
                        state.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
