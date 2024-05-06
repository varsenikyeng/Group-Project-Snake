package snake.core;

public class Heart extends GameEntity{
    private int count;
    public Heart(Position position){
        super(position, "\u2764\ufe0f");
        count = 3;
    }
    public int getCount(){
        return this.count;
    }
    public void setCount(int count){
        this.count = count;
    }

}
