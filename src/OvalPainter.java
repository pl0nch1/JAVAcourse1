import java.awt.*;
import java.awt.event.*;

public class OvalPainter extends Component implements AdjustmentListener, ItemListener, Runnable {
    private int r = 0;
    private float x = 0;
    private float y = 0;
    private Color color;
    private State state;

    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.drawRect(0,0,getWidth()-1,getHeight()-1);
        g.setColor(color);
        float p = r/90.0f;
        int width = 10 + (int) ((getWidth()-10)*p);
        int height = 10 + (int) ((getHeight()-10)*p);
        g.fillOval((int)((getWidth()-width) * x) + 1, (int)((getHeight()-height) * y) + 1,width-2, height-2);
    }

    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        r = e.getValue();
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getItem() == "Розовый"){
            color = Color.PINK;
        } else if (e.getItem() == "Чёрный") {
            color = Color.BLACK;
        }
        repaint();
    }

    public OvalPainter(State state){
        super();
        this.state = state;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (state){
                    if (state.getNum() == 2){
                        while (x < 1) {
                            x += 0.01;
                            repaint();
                            Thread.sleep(10);
                        }
                        while (y < 1) {
                            y += 0.01;
                            repaint();
                            Thread.sleep(10);
                        }
                        while (x > 0) {
                            x -= 0.01;
                            repaint();
                            Thread.sleep(10);
                        }
                        while (y > 0) {
                            y -= 0.01;
                            repaint();
                            Thread.sleep(10);
                        }
                        state.next();
                    }
                    state.notifyAll();
                    while (state.getNum() != 2)
                        state.wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
