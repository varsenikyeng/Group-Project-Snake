package snake.core;
import snake.core.GameEntity;
public class Obstacle extends GameEntity {
    private long spawnTime;
    private static final int LIFETIME = 6000;
    public Obstacle(Position position){
        super(position, " \uD83C\uDF44 ");
        this.spawnTime = System.currentTimeMillis();
    }

    public boolean isMature(){
        return (System.currentTimeMillis() - spawnTime) > LIFETIME;
    }



}
