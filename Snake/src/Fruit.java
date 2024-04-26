public class Fruit extends GameEntity {

    public Fruit(Position position){
        super(position, " \uD83C\uDF4E ");
    }


    @Override
    public void effect(Snake snake) {
        snake.grow();
    }
}
