import java.awt.*;

public class TriangleBanner extends Panel {
    public TriangleBanner(){
        setBackground(new Color(148,180,217));
        Dimension dim = new Dimension(1000, 20);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // Создание параметров ячейки GridBagLayout' a
        // Ниже идет создание и добавление с параметрами составляющих частей баннера
        constraints.fill = GridBagConstraints.VERTICAL; // Вертикальная ориентация компоновщика
        constraints.insets = new Insets(0,5,5,5);
        constraints.weightx = 100;
        constraints.gridy = 0;
        add(new Label("Рисование треугольника:"), constraints);
        constraints.gridy = 1;
        Button minButton = new Button("Минимум");
        minButton.setMinimumSize(dim);
        minButton.setPreferredSize(dim);
        add(minButton, constraints);
        constraints.gridy = 2;
        Button maxButton = new Button("Максимум");
        maxButton.setMinimumSize(dim);
        maxButton.setPreferredSize(dim);
        add(maxButton, constraints);
        constraints.weighty = 1;
        constraints.gridy = 3;
        TrianglePainter painter = new TrianglePainter(); // Создание рисовальщика треугольника
        painter.setMinimumSize(dim);
        painter.setPreferredSize(dim);
        maxButton.addActionListener(painter); // Добавление обработчика события кнопки
        minButton.addActionListener(painter); // Добавление обработчика события кнопки
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
