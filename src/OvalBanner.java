import java.awt.*;

public class OvalBanner extends Panel{
    public OvalBanner(State state){
        setLayout(new GridBagLayout());
        setBackground(new Color(148,180,217));
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.insets = new Insets(5,5,5,5);
        constraints.weightx = 100;
        constraints.weighty = 0;
        constraints.gridy = 0;
        add(new Label("Рисование овала"), constraints);

        Dimension dim = new Dimension(1000, 20);
        constraints.gridy = 1;
        constraints.weighty = 1;
        OvalPainter painter = new OvalPainter(state);
        painter.setMinimumSize(dim);
        painter.setPreferredSize(dim);
        add(painter, constraints);

        constraints.weightx = 0;
        constraints.weighty = 0;
        Scrollbar bar = new Scrollbar();
        bar.addAdjustmentListener(painter);
        constraints.gridx = 1;
        constraints.insets = new Insets(0,0,0,0);
        add(bar, constraints);

        constraints.insets = new Insets(5,5,5,5);
        constraints.weightx = 100;
        constraints.gridx = 0;
        constraints.gridy = 2;
        Choice choice = new Choice();
        choice.setMinimumSize(dim);
        choice.setPreferredSize(dim);
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0,0,5,0);
        choice.add("Чёрный");
        choice.add("Розовый");
        choice.addItemListener(painter);
        add(choice, constraints);
    }

    @Override
    public void setEnabled(boolean b) {
        Component[] components = getComponents();
        for (int i=0; i<components.length; i++){
            components[i].setEnabled(b);
        }
    }
}
