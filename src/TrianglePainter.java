import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class TrianglePainter extends Component implements ActionListener, Runnable {
    private boolean up = false;
    private int prop = 0;
    private State state;

    @Override
    public void paint(Graphics g){
        float multiplier = 1f;
        if (!up){
            multiplier /= 2;
        }
        g.drawLine((int) (getWidth()*prop/100.0f) , getHeight(), getWidth()/2, (int) (getHeight() * (1-multiplier)));
        g.drawLine((int)(getWidth()*(1 - prop/100.0f)), getHeight(), getWidth()/2, (int) (getHeight() * (1-multiplier)));
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


    public TrianglePainter(State state){
        super();
        this.state = state;
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (state) {
                    while (state.getNum() == 0) {
                        prop = (prop + 1) % 100;
                        repaint();
                        Thread.sleep(10);
                        if (prop == 0){
                            state.next();
                        }
                    }
                    state.notifyAll();
                    while (state.getNum() != 0) {
                        state.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
