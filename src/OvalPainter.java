import java.awt.*;
import java.awt.event.*;

public class OvalPainter extends Component implements AdjustmentListener, ItemListener, Runnable {
    private int r = 0;
    private double x = 0;
    private double y = 0;
    private Color color;
    private State state;
    private int direction;

    @Override
    public void paint(Graphics g){
        Image buffer = createImage(getWidth(), getHeight());
        Graphics gc = buffer.getGraphics();
        // Отрисовка перемещаемого овала на основании анимируемого параметра
        gc.setColor(Color.black);
        gc.drawRect(0,0,getWidth()-1,getHeight()-1);
        gc.setColor(color);
        float p = r/90.0f;
        int width = 10 + (int) ((getWidth()-10)*p);
        int height = 10 + (int) ((getHeight()-10)*p);
        gc.fillOval((int)((getWidth()-width) * x) + 1, (int)((getHeight()-height) * y) + 1,width-2, height-2);
        g.drawImage(buffer, 0,0,this);
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
        // Создание и запуск потока анимации
        new Thread(this).start();
    }

    // Реализация интерфейса runnable
    @Override
    public void run() {
        try {
            while (true) {
                // Блокировка объекта state
                synchronized (state){
                    // Если состояние 2
                    while ((state.getNum() == 2)  && (!state.getStopped())){
                        if (direction == 0) {
                            x += 0.01;
                            if (x >= 1)
                                direction++;
                        }
                        if (direction == 1) {
                            y += 0.01;
                            if (y >= 1)
                                direction++;
                        }
                        if (direction == 2) {
                            x -= 0.01;
                            if (x <= 0)
                                direction++;
                        }
                        if (direction == 3) {
                            y -= 0.01;
                            if (y <= 0)
                                direction=0;
                        }
                        repaint();
                        Thread.sleep(15);
                        // Смена состояния
                        if (((int)(x*100)==0) && ((int)(y*100)==0)){
                            x=0;
                            y=0;
                            state.next();
                        }
                    }
                    // Оповещение всех ожидающих state потоков
                    state.notifyAll();
                    while (state.getNum() != 2)
                        // Введение потока в состояние ожидания
                        state.wait();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
