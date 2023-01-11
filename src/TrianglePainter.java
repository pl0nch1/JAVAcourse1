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
        Image buffer = createImage(getWidth(), getHeight());
        Graphics gc = buffer.getGraphics();
        // Отрисовка ребер треугольника на основании анимируемого параметра
        float multiplier = 1f;
        if (!up){
            multiplier /= 2;
        }
        gc.drawLine((int) (getWidth()*prop/100.0f) , getHeight(), getWidth()/2, (int) (getHeight() * (1-multiplier)));
        gc.drawLine((int)(getWidth()*(1 - prop/100.0f)), getHeight(), getWidth()/2, (int) (getHeight() * (1-multiplier)));
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        String text = button.getLabel();
        up = text.equals("Максимум");
        repaint();
    }


    public TrianglePainter(State state){
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
                synchronized (state) {
                    // Если состояние 0
                    while (state.getNum() == 0 && !state.getStopped()) {
                        // Изменение анимируемого параметра
                        prop = (prop + 1) % 100;
                        repaint();
                        Thread.sleep(10);
                        if (prop == 0){
                            // Смена состояния
                            state.next();
                        }
                    }
                    // Оповещение всех ожидающих state потоков
                    state.notifyAll();
                    while (state.getNum() != 0) {
                        // Введение потока в состояние ожидания
                        state.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
