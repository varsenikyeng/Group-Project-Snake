package snake.core;
import java.util.Scanner;
import java.io.*;

public class ScoreManager {
    private static final String SCORE_FILE_1 = "Snake/src/snake/core/snakeGameScore.txt";
    private static final String SCORE_FILE_2 = "Snake/src/snake/core/hardSnakeGameScore.txt";

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
    public static void updateScore(int currentScore, String difficulty){
        String file = (difficulty.equals("worm")) ? SCORE_FILE_1 : SCORE_FILE_2;
        int highestScore = getHighestScore(difficulty);
        if(currentScore > highestScore){
            PrintWriter writer = null;
            try{
                writer =  new PrintWriter(new FileOutputStream(file, false));
                writer.print(currentScore);
                writer.flush();
                writer.close();
            } catch(FileNotFoundException e){
                System.out.println("Error opening the file " + file);
                System.exit(0);
            }
        }
    }

}

