import java.util.Scanner;
public class SnakeConsole {
    private SnakeGame game;
    public void play() throws InterruptedException {
        try {
            Scanner sc = new Scanner(System.in);
            this.game = new SnakeGame();
            game.printBoard();
            while (game.getSnake().getIsAlive()) {
                String input = sc.nextLine();
                while (!sc.hasNext()) {
                    if (isValidInput(input)) {
                        Move move = game.getSnake().generateFromPositionAndDirection(input);
                        if (game.performMove(move)) {
                            game.printBoard();
                            System.out.println();
                        }
                    }
                   Thread.sleep(600);
                }
            }
            sc.close();
        } catch (OutOfBoundMoveException e) {
            System.out.println("Game over, try better next time!");
            System.exit(0);
        }
    }

    public boolean isValidInput (String input){
        return input.equalsIgnoreCase("w") || input.equalsIgnoreCase("s")
                || input.equalsIgnoreCase("a") || input.equalsIgnoreCase("d");
    }
}
