import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class LinePainter extends Component implements TextListener, Runnable {
    private int x = 0;
    private float yStart;
    private boolean direction;
    private State state;
    @Override
    public void paint(Graphics g){
        Image buffer = createImage(getWidth(), getHeight());
        Graphics gc = buffer.getGraphics();
        gc.drawLine(0 , (int) (yStart * getHeight()) - 10, x,0);
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void textValueChanged(TextEvent e) {
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

    public LinePainter(Dimension dim, State state){
        setPreferredSize(dim);
        setMinimumSize(dim);
        yStart = 1;
        direction = true;
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
                synchronized (state) {
                    // Если состояние 1
                    while ((state.getNum() == 1) && (!state.getStopped())) {
                        if (direction){
                            // Поднятие линии
                            yStart -= 0.01;
                            if (yStart <= 0){
                                yStart = 0;
                                direction = false;
                            }
                        } else {
                            // Спад линии
                            yStart += 0.01;
                        }
                        repaint();
                        Thread.sleep(10);
                        if (yStart >= 1){
                            // Смена состояния
                            state.next();
                            direction = true;
                            yStart=1;
                        }
                    }
                    // Оповещение всех ожидающих state потоков
                    state.notifyAll();
                    while (state.getNum() != 1) {
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
