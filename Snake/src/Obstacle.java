public class Obstacle extends GameEntity{
    public Obstacle(Position position){
        super(position, " \uD83C\uDF44 ");
    }


    @Override
    public void effect(Snake snake) {
        try {
            snake.shrink();
        } catch (OutOfBoundMoveException e) {
            System.out.println(e.getMessage());
        }
    }

}
