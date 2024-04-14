public class Fruit extends GameEntity {

    public Fruit(Position position){
        super(EntityColor.RED, position, " \u001B[31m\u25CF\u001B[0m " );
    }


    @Override
    public void effect(Snake snake) {
        snake.grow();
    }
}
