// Класс, отвечающий за управление состоянием (так же используется для создание блокировки поток)
public class State {
    private int num = 0;
    private boolean stopped = false;
    public void next(){
        num = (num + 1) % 4;
    }

    public void stop(){
        stopped = true;
    }

    public void resume(){
        stopped = false;
    }

    public int getNum(){
        return num;
    }

    public boolean getStopped(){
        return stopped;
    }
}
