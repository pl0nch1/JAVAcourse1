import java.awt.*;
import java.awt.event.TextListener;

public class LineBanner extends Panel{
    public LineBanner(State state){
        setBackground(new Color(105,150,225));
        Dimension dim = new Dimension(1000, 15);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(5,5,5,5);
        constraints.weightx = 100;
        constraints.gridy = 0;
        add(new Label("Рисование линии:"), constraints);
        constraints.gridy = 1;
        Label label = new Label("Введите длину проекции линии");
        label.setMinimumSize(dim);
        label.setPreferredSize(dim);
        add(label, constraints);
        constraints.gridy = 2;
        TextField projectionLength = new TextField("0");
        projectionLength.setColumns(100);
        add(projectionLength, constraints);
        constraints.weighty = 1;
        constraints.gridy = 3;
        LinePainter painter = new LinePainter(dim, state);
        painter.setPreferredSize(dim);
        painter.setMinimumSize(dim);
        projectionLength.addTextListener(painter);
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
