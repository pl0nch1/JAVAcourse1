import java.awt.*;

public class RectangleBanner extends Panel {
    public RectangleBanner(){
        setBackground(new Color(105,150,225));
        GridBagConstraints constraints = new GridBagConstraints(); // Создание параметров ячейки GridBagLayout' a
        setLayout(new GridBagLayout()); // Назначение менеджера компоновки
        // Ниже идет создание и добавление с параметрами составляющих частей баннера
        constraints.fill = GridBagConstraints.VERTICAL; // Задание вертикальной ориентации компоновщика
        constraints.weightx = 100; // Задание горизонатльного веса элемента
        constraints.weighty = 0; // Задание вертикального веса элемента
        constraints.gridy = 0; // Задание вертикальной позиции элемента
        constraints.insets = new Insets(3,5,3,5); // Задание внутренний отступов
        add(new Label("Рисование квадрата"), constraints);

        Dimension dim = new Dimension(1000, 15);
        RectanglePainter painter = new RectanglePainter(); // Создание рисовальщика прямоугольника
        painter.setMinimumSize(dim);
        painter.setPreferredSize(dim);
        constraints.weighty = 1;
        constraints.gridy = 1;
        add(painter, constraints);

        constraints.weighty = 0;
        Scrollbar bar = new Scrollbar(); // Создание полосы прокрутки
        bar.setMinimumSize(dim);
        bar.setPreferredSize(dim);
        bar.setOrientation(Scrollbar.HORIZONTAL); // Задание горизонатльной ориентации полосы прокрутки
        constraints.gridy = 2;
        add(bar,constraints);
        constraints.gridy = 3;
        Label label = new Label("Выбор цвета контура:");
        label.setMinimumSize(dim);
        label.setPreferredSize(dim);
        add(label, constraints);

        constraints.gridy = 4;
        List list = new List();
        list.add("Чёрный");
        list.add("Синий");
        list.add("Тёмно-серый");
        list.add("Жёлтый");
        list.setMinimumSize(dim);
        list.setPreferredSize(dim);
        list.addItemListener(painter);
        bar.addAdjustmentListener(painter); // Назначение обаботчика события перемещения полосы прокрутки
        constraints.insets = new Insets(5,5,10,5); // внутренние отступы
        add(list, constraints);
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
