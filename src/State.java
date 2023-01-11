// Класс, отвечающий за управление состоянием (так же используется для создание блокировки поток)
public class State {
    private int num = 0;
    public void next(){
        num = (num + 1) % 4;
    }

    public int getNum(){
        return num;
    }
}
