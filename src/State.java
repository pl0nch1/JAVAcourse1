public class State {
    private int num = 0;
    public void next(){
        num = (num + 1) % 4;
    }

    public int getNum(){
        return num;
    }
}
