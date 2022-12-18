import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class App extends Frame{
    private Logger logger;
    private Label logs;
    public static void main(String[] args) {
        App singleApp = new App();
    }

    private App(){
        super();
        logger = Logger.getLogger("EventLogger");
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);        try {
            FileHandler fh = new FileHandler("application.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setSize(600,600);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weighty = 1;
        constraints.weightx = 1;

        Dimension dim = new Dimension(1000,2000);
        MiddleBanner middle = new MiddleBanner();
        middle.setPreferredSize(dim);
        middle.setMinimumSize(dim);
        RightBanner right = new RightBanner();
        right.setPreferredSize(dim);
        right.setMinimumSize(dim);
        SelectionBanner banner = new SelectionBanner(middle, right);
        banner.setPreferredSize(dim);
        banner.setMinimumSize(dim);
        add(banner, constraints);
        constraints.gridx = 1;
        add(middle, constraints);
        constraints.gridx = 2;
        add(right, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.weighty = 0;
        dim = new Dimension(10000,30);
        logs = new Label("LOGS");
        logs.setAlignment(Label.LEFT);
        logs.setPreferredSize(dim);
        logs.setMinimumSize(dim);
        add(logs, constraints);
        setVisible(true);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static App getTopLevelContainerApp(Component component){
        while (component.getParent() != null){
            component = component.getParent();
        }
        return (App) component;
    }

    public void log(String message){
        logger.info(message);
        logs.setText(message);
    }
}
