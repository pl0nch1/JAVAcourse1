import java.awt.*;

public class RectangleBanner extends Panel {
    public RectangleBanner(State state){
        setBackground(new Color(105,150,225));
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(new GridBagLayout());
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weightx = 100;
        constraints.weighty = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(3,5,3,5);
        add(new Label("Рисование квадрата"), constraints);

        Dimension dim = new Dimension(1000, 15);
        RectanglePainter painter = new RectanglePainter(state); // Проброс объекта состояния в рисовальщик
        painter.setMinimumSize(dim);
        painter.setPreferredSize(dim);
        constraints.weighty = 1;
        constraints.gridy = 1;
        add(painter, constraints);

        constraints.weighty = 0;
        Scrollbar bar = new Scrollbar();
        bar.setMinimumSize(dim);
        bar.setPreferredSize(dim);
        bar.setOrientation(Scrollbar.HORIZONTAL);
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
        bar.addAdjustmentListener(painter);
        constraints.insets = new Insets(5,5,10,5);
        add(list, constraints);
    }

    @Override
    public void setEnabled(boolean b) {
        Component[] components = getComponents();
        for (int i=0; i<components.length; i++){
            components[i].setEnabled(b);
        }
    }
}
