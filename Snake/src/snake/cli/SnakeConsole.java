package snake.cli;

import snake.core.*;

import java.util.Scanner;

public class SnakeConsole {
    private SnakeGame game;
    private HardSnakeGame hardSnakeGame;

    /**
     * Begins the gameplay loop, allowing the player to choose a difficulty level (worm or python) and interact with the game until it's over.
     */
    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose a difficulty level: worm/python");
        String difficulty = sc.next();
        if (difficulty.equals("worm")) {
            try {
                this.game = new SnakeGame();
                int highScore = ScoreManager.getHighestScore(difficulty);
                System.out.println("Highest Score: " + highScore);
                System.out.println("Current Score: " + game.getScore());
                game.printBoard();
                while (game.getSnake().getIsAlive()) {
                    if (game.getSnake().getBody().size() == game.BOARD_COLUMNS * game.BOARD_ROWS) {
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
                        ScoreManager.updateScore(game.getScore(), difficulty);
                        System.out.println("Դու ճխլվեցիր :(");
                        System.exit(0);
                    }

                }
                sc.close();
            } catch (OutOfBoundMoveException e) {
                ScoreManager.updateScore(game.getScore(), difficulty);
                System.out.println(e.getMessage());
                System.exit(0);
            }

        } else if (difficulty.equals("python")) {
            try {
                this.hardSnakeGame = new HardSnakeGame();
                hardSnakeGame.printHearts();
                int highScore = ScoreManager.getHighestScore(difficulty);
                System.out.println("Current Score: " + hardSnakeGame.getScore());
                System.out.println("Highest Score: " + highScore);
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
                            System.out.println("Current Score: " + hardSnakeGame.getScore());
                            System.out.println("Highest Score: " + highScore);
                            hardSnakeGame.printBoard();
                            System.out.println(hardSnakeGame.getSnake().getDirection());
                        }
                    }
                    if (!hardSnakeGame.getSnake().getIsAlive()) {
                        ScoreManager.updateScore(hardSnakeGame.getScore(), difficulty);
                        System.out.println("Դու լխճվեցիր:(");
                        System.exit(0);
                    }
                    if (hardSnakeGame.getHeart().getCount() <= 0) {
                        ScoreManager.updateScore(hardSnakeGame.getScore(), difficulty);
                        hardSnakeGame.getSnake().setIsAlive(false);
                        System.out.println("All lives lost! Game over.");
                        System.exit(0);
                    }
                }
                sc.close();
            } catch (OutOfBoundMoveException e) {
                ScoreManager.updateScore(hardSnakeGame.getScore(), difficulty);
                System.out.println(e.getMessage());
                System.exit(0);
            }

        }
        ScoreManager.updateScore(game.getScore(), difficulty);
    }

    /**
     * Checks if the input provided by the player is valid based on the current direction of the snake.
     *
     * @param input     The input provided by the player.
     * @param direction The current direction of the snake.
     * @return True if the input is valid, false otherwise.
     */
    public static boolean isValidInput(String input, String direction) {
        return input.equalsIgnoreCase("w") && !direction.equalsIgnoreCase("s") ||
                input.equalsIgnoreCase("s") && !direction.equalsIgnoreCase("w") ||
                input.equalsIgnoreCase("a") && !direction.equalsIgnoreCase("d") ||
                input.equalsIgnoreCase("d") && !direction.equalsIgnoreCase("a");
    }
}


