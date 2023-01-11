import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

public class LinePainter extends Component implements TextListener, Runnable {
    private int x = 0;
    private float yStart;
    private State state;
    @Override
    public void paint(Graphics g){
        g.drawLine(0 , (int) (yStart * getHeight()) - 10, x,0);
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
                    if (state.getNum() == 1) {
                        // Поднятие линии
                        while (yStart > 0) {
                            yStart -= 0.01;
                            repaint();
                            Thread.sleep(10);
                        }
                        // Спад линии
                        while (yStart < 1) {
                            yStart += 0.01;
                            repaint();
                            Thread.sleep(10);
                        }
                        // Смена состояния
                        state.next();
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
