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

    private boolean matchesColor(ItemEvent e, String s){
        return ((List) e.getItemSelectable()).getSelectedItem().intern().equals(s);
    }

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
        r = e.getAdjustable().getValue();
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (matchesColor(e, "Чёрный")) {
            color = Color.BLACK;
        } else if(matchesColor(e, "Синий")) {
            color = Color.BLUE;
        } else if (matchesColor(e, "Тёмно-серый")) {
            color = Color.DARK_GRAY;
        } else if (matchesColor(e, "Жёлтый")) {
            color = Color.YELLOW;
        }
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
