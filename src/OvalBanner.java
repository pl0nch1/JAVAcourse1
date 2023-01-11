import java.awt.*;

public class OvalBanner extends Panel{
    public OvalBanner(){
        setLayout(new GridBagLayout()); // Назначение менеджера компоновки
        setBackground(new Color(148,180,217));
        GridBagConstraints constraints = new GridBagConstraints(); // Создание параметров ячейки GridBagLayout' a
        // Ниже идет создание и добавление с параметрами составляющих частей баннера
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(5,5,5,5);
        constraints.weightx = 100;// Задание горизонатльного веса элемента
        constraints.weighty = 0;// Задание вертикального веса элемента
        constraints.gridy = 0;// Задание вертикальной позиции элемента в сетке
        add(new Label("Рисование овала"), constraints);

        Dimension dim = new Dimension(1000, 20);
        constraints.gridy = 1;
        constraints.weighty = 1;
        OvalPainter painter = new OvalPainter(); // Создание рисовальщика овала
        painter.setMinimumSize(dim);
        painter.setPreferredSize(dim);
        add(painter, constraints);

        constraints.weightx = 0; // Горизонтальный вес
        constraints.weighty = 0;// Задание вертикального веса элемента
        Scrollbar bar = new Scrollbar(); // Создание полосы прокрутки
        bar.addAdjustmentListener(painter); // Назначение обработчика полосы прокрутки
        constraints.gridx = 1;
        constraints.insets = new Insets(0,0,0,0); // Внутренние отступы
        add(bar, constraints);

        constraints.insets = new Insets(5,5,5,5); // Внутренние отступы
        constraints.weightx = 100; // Горизонтальный вес
        constraints.gridx = 0;
        constraints.gridy = 2;// Задание вертикального веса элемента
        Choice choice = new Choice();
        choice.setMinimumSize(dim);
        choice.setPreferredSize(dim);
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0,0,5,0); // Внутренние отступы
        choice.add("Чёрный");
        choice.add("Розовый");
        choice.addItemListener(painter);
        add(choice, constraints);
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
