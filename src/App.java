import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static java.lang.System.out;

public class App extends Frame{
    private String fileName = "application.log";
    private TextArea logs; // Буферизированный символьный поток записи в файл
    private BufferedWriter out;
    public static void main(String[] args) {
        App singleApp = new App();
    }

    private App(){
        super();
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
        logs = new TextArea("");
        logs.setEditable(false);
        Panel p3 = new Panel();
        p3.setMinimumSize(new Dimension(1000, 100));
        p3.setLayout(new BorderLayout());
        Button b3 = new Button("Чтение файла-журнала");
        b3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Сброс данных из буфера потока (запись в файл)
                    out.flush();
                    // Чтение из файла
                    BufferedReader in =
                            new BufferedReader(new FileReader(fileName));
                    String s = "", line;
                    while ((line = in.readLine()) != null)
                        s += line + "\n";
                    logs.setText(s);
                    in.close();
                }
                catch(IOException ex) {
                    logs.setText("Ошибка чтения файла: \n");
                    logs.append(ex.toString());
                }
            }});
        p3.add(b3, BorderLayout.NORTH);
        p3.add(logs, BorderLayout.CENTER);
        add(p3, constraints);
        try {
            out = new BufferedWriter(new FileWriter(fileName));
        }
        catch(IOException ex) {
            logs.setText("Ошибка при создании потока записи: " + '\n');
            logs.append(ex.toString() + '\n');
        }
        catch(SecurityException ex) {
            logs.setText("Нет разрешения доступа к файлу: " + '\n');
            logs.append(ex.toString()  + '\n');
            b3.setEnabled(false);
        }
        if (out != null){
            Calendar c = Calendar.getInstance();
            String dateMessage =
                    "Дата и время: " + c.get(Calendar.DAY_OF_MONTH) + "/" +
                            (c.get(Calendar.MONTH)+1) + " - " +  c.get(Calendar.YEAR) +
                            " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
            log(dateMessage);
            log("Инициализация приложения завершена");
        }
        setVisible(true);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                closeAppl();
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
        try {
            out.write(message);
            out.newLine();
        }
        catch(IOException ex) {
            logs.setText("Ошибка записи в файл:\n");
            logs.append(ex.toString() + '\n');
        }
    }

    // Сброс и закрытие потока записи в файл
    // при завершении выполнения приложения
    private void closeAppl()
    {
        if (out != null) {
            Calendar c = Calendar.getInstance();
            String dateMessage =
                    "Дата и время: " + c.get(Calendar.DAY_OF_MONTH) + "/" +
                            (c.get(Calendar.MONTH)+1) + " - " +  c.get(Calendar.YEAR) +
                            " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
            log(dateMessage);
            log("Выполнение приложения завершено");
            try {
                out.close();
            }
            catch(IOException ex) {}
        }
    }

}
