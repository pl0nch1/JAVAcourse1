import java.awt.*;
import java.awt.event.TextListener;

public class LineBanner extends Panel{
    public LineBanner(){
        setBackground(new Color(105,150,225));
        Dimension dim = new Dimension(1000, 15);
        setLayout(new GridBagLayout());// Назначение менеджера компоновки
        GridBagConstraints constraints = new GridBagConstraints();
        // Ниже идет создание и добавление с параметрами составляющих частей баннера
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(5,5,5,5);
        constraints.weightx = 100;// Задание горизонатльного веса элемента
        constraints.gridy = 0;// Задание вертикального веса элемента
        add(new Label("Рисование линии:"), constraints);
        constraints.gridy = 1;
        Label label = new Label("Введите длину проекции линии");
        label.setMinimumSize(dim);
        label.setPreferredSize(dim);
        add(label, constraints); // Добавление лэйбла на панель
        constraints.gridy = 2;
        TextField projectionLength = new TextField("0");
        projectionLength.setColumns(100);
        add(projectionLength, constraints);
        constraints.weighty = 1;
        constraints.gridy = 3;
        LinePainter painter = new LinePainter(); // Создание рисовальщика линии
        painter.setPreferredSize(dim);
        painter.setMinimumSize(dim);
        // Добавление обработчика события изменения текста
        projectionLength.addTextListener(painter);
        add(painter, constraints);
    }

    // Метод правильного циклического отключения баннера
    @Override
    public void setEnabled(boolean b) {
        Component[] components = getComponents();
        for (int i=0; i<components.length; i++){
            components[i].setEnabled(b);
        }
    }
}
