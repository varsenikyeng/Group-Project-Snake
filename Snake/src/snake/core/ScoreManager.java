package snake.core;
import java.util.Scanner;
import java.io.*;

/**
 * Manages the scoring system for the Snake game.
 */
public class ScoreManager {
    /** The file path for the score of the game with the "worm" difficulty. */
    private static final String SCORE_FILE_1 = "Snake/snakeGameScore.txt";

    /** The file path for the score of the game with the "python" difficulty. */
    private static final String SCORE_FILE_2 = "Snake/hardSnakeGameScore.txt";

    /**
     * Retrieves the highest score recorded for a given difficulty level.
     * @param difficulty The difficulty level of the game ("worm" or "hard").
     * @return The highest score recorded for the specified difficulty level.
     */
    public static int getHighestScore(String difficulty) {
        String file = (difficulty.equals("worm")) ? SCORE_FILE_1 : SCORE_FILE_2;
        try {
            Scanner scanner = new Scanner(new File(file));
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }
        return 0;
    }

    /**
     * Updates the highest score for a given difficulty level if the current score surpasses it.
     * @param currentScore The current score achieved in the game.
     * @param difficulty The difficulty level of the game ("worm" or "hard").
     */
    public static void updateScore(int currentScore, String difficulty) {
        String file = (difficulty.equals("worm")) ? SCORE_FILE_1 : SCORE_FILE_2;
        int highestScore = getHighestScore(difficulty);
        if(currentScore > highestScore) {
            PrintWriter writer = null;
            try {
                writer =  new PrintWriter(new FileOutputStream(file, false));
                writer.print(currentScore);
                writer.flush();
                writer.close();
            } catch(FileNotFoundException e) {
                System.out.println("Error opening the file " + file);
                System.exit(0);
            }
        }
    }
}
