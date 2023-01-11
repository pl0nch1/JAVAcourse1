import java.awt.*;

public class TriangleBanner extends Panel {
    public TriangleBanner(State state){
        setBackground(new Color(148,180,217));
        Dimension dim = new Dimension(1000, 20);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
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
        TrianglePainter painter = new TrianglePainter(state); // Проброс объекта состояния в рисовальщик
        painter.setMinimumSize(dim);
        painter.setPreferredSize(dim);
        maxButton.addActionListener(painter);
        minButton.addActionListener(painter);
        add(painter, constraints);
    }

    @Override
    public void setEnabled(boolean b) {
        Component[] components = getComponents();
        for (int i=0; i<components.length; i++){
            components[i].setEnabled(b);
        }
    }
}
