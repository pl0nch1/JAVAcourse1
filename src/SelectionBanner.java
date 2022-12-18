import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SelectionBanner extends Panel implements ItemListener {
    private ItemListener middlePanel, rightPanel;
    CheckboxGroup cbg;
    Checkbox ban, tri, oval, line, rect;
    public SelectionBanner(ItemListener middlePanel, ItemListener rightPanel){
        setBackground(new Color(179, 190, 230));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.weighty = 0.5;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0,25,0,0);
        constraints.gridy = 0;
        cbg = new CheckboxGroup();
        add(new Label("Выберите фигуру"), constraints);
        constraints.gridy = 1;
        tri = new Checkbox("Треугольник", cbg, true);
        tri.addItemListener(this);
        add(tri, constraints);
        constraints.gridy = 2;
        line = new Checkbox("Линия", cbg, false);
        line.addItemListener(this);
        add(line, constraints);
        constraints.gridy = 3;
        rect = new Checkbox("Прямоугольник", cbg, false);
        rect.addItemListener(this);
        add(rect, constraints);
        constraints.gridy = 4;
        oval = new Checkbox("Овал", cbg, false);
        oval.addItemListener(this);
        add(oval, constraints);
        constraints.gridy = 5;
        ban = new Checkbox("Запретить изменения");
        ban.addItemListener(this);
        add(ban, constraints);

        this.middlePanel = middlePanel;
        this.rightPanel = rightPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        App.getTopLevelContainerApp(this).log(e.toString());
        if (ban.getState()) {
            oval.setEnabled(false);
            rect.setEnabled(false);
            tri.setEnabled(false);
            line.setEnabled(false);
            e = new ItemEvent(e.getItemSelectable(), 0, "ban", ItemEvent.SELECTED);
        }
        else {
            oval.setEnabled(true);
            rect.setEnabled(true);
            tri.setEnabled(true);
            line.setEnabled(true);
            e = new ItemEvent(e.getItemSelectable(), 0, cbg.getSelectedCheckbox().getLabel(), ItemEvent.SELECTED);
        }

        middlePanel.itemStateChanged(e);
        rightPanel.itemStateChanged(e);
    }
}
