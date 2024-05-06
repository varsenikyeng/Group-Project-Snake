package snake.cli;
import snake.core.*;
import java.util.Scanner;

public class SnakeConsole {
    private SnakeGame game;
    private HardSnakeGame hardSnakeGame;
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a difficulty level: worm/python");
        String difficulty = sc.next();
        if (difficulty.equals("worm")) {
            try {
                this.game = new SnakeGame();
                int highScore = ScoreManager.getHighestScore();
                System.out.println("Highest Score: " + highScore);
                System.out.println("Current Score: " + game.getScore());
                game.printBoard();
                while (game.getSnake().getIsAlive()) {
                    if (game.getSnake().getBody().size() == 25) {
                        System.out.println("YOU WON!");
                        System.exit(0);
                    }
                    String input = sc.next();
                    if (isValidInput(input, game.getSnake().getDirection())) {
                        game.getSnake().setDirection(input);
                        Move move = game.getSnake().generateFromPositionAndDirection(input);
                        if (game.performMove(move)) {
                            System.out.println("Highest Score: " + highScore);
                            System.out.println("Current Score: " + game.getScore());
                            game.printBoard();
                            System.out.println(game.getSnake().getDirection());
                        }
                    }
                    if (!game.getSnake().getIsAlive()) {
                        ScoreManager.updateScore(game.getScore());
                        System.out.println("Դու ճխլվեցիր:(");
                        System.exit(0);
                    }

                }
                sc.close();
            } catch (OutOfBoundMoveException e) {
                ScoreManager.updateScore(game.getScore());
                System.out.println(e.getMessage());
                System.exit(0);
            }

        } else if (difficulty.equals("python")) { // Compare with "python"
            try {
                this.hardSnakeGame = new HardSnakeGame();
                hardSnakeGame.printHearts();
                System.out.println(hardSnakeGame.getScore());
                hardSnakeGame.printBoard();
                while (hardSnakeGame.getSnake().getIsAlive()) {
                    if (hardSnakeGame.getSnake().getBody().size() == 25) {
                        System.out.println("YOU WON!");
                        System.exit(0);
                    }
                    String input = sc.next();
                    if (isValidInput(input, hardSnakeGame.getSnake().getDirection())) {
                        hardSnakeGame.getSnake().setDirection(input);
                        Move move = hardSnakeGame.getSnake().generateFromPositionAndDirection(input);
                        if (hardSnakeGame.performMove(move)) {
                            hardSnakeGame.printHearts();
                            System.out.println(hardSnakeGame.getScore());
                            hardSnakeGame.printBoard();
                            System.out.println(hardSnakeGame.getSnake().getDirection());
                        }
                    }
                    if (!hardSnakeGame.getSnake().getIsAlive()) {
                        System.out.println("Դու ճխլվեցիր:(");
                        System.exit(0);
                    }
                    if (hardSnakeGame.getHeart().getCount() <= 0) {
                        hardSnakeGame.getSnake().setIsAlive(false);
                        System.out.println("All lives lost! Game over.");
                        System.exit(0);
                    }
                }
                sc.close();
            } catch (OutOfBoundMoveException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }

        }
        ScoreManager.updateScore(game.getScore());
    }

    public boolean isValidInput (String input, String direction){
        return input.equalsIgnoreCase("w") && !direction.equalsIgnoreCase("s") ||
                input.equalsIgnoreCase("s") && !direction.equalsIgnoreCase("w") ||
                input.equalsIgnoreCase("a") && !direction.equalsIgnoreCase("d") ||
                input.equalsIgnoreCase("d") && !direction.equalsIgnoreCase("a") ;
    }
}


