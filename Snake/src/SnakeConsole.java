import java.util.Scanner;
public class SnakeConsole {
    private SnakeGame game;
    public void play() {
        try {
            Scanner sc = new Scanner(System.in);
            this.game = new SnakeGame();
            game.printBoard();
            while (game.getSnake().getIsAlive()) {
                String input = sc.next();
                    if (isValidInput(input)) {
                        Move move = game.getSnake().generateFromPositionAndDirection(input);
                        if (game.performMove(move)) {
                            game.getSnake().setDirection(input);
                            game.printBoard();
                            System.out.println(game.getSnake().getDirection());
                        }
                    }
            }
            sc.close();
        } catch (OutOfBoundMoveException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
    public boolean isValidInput (String input){
        return input.equalsIgnoreCase("w") || input.equalsIgnoreCase("s")
                || input.equalsIgnoreCase("a") || input.equalsIgnoreCase("d");
    }
}
