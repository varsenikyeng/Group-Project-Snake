package snake.core;
import java.util.Scanner;
import java.io.*;

public class ScoreManager {
    private static final String SCORE_FILE_1 = "Snake/src/snake/core/snakeGameScore.txt";
    private static final String SCORE_FILE_2 = "hardSnakeGameScore.txt";

    public static int getHighestScore() {
        try {
            Scanner scanner = new Scanner(new File(SCORE_FILE_1));
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
            System.exit(0);
        }
        return 0;
    }
    public static void updateScore(int currentScore){
        int highestScore = getHighestScore();
        if(currentScore > highestScore){
            PrintWriter writer = null;
            try{
                writer =  new PrintWriter(new FileOutputStream(SCORE_FILE_1, false));
                writer.print(currentScore);
                writer.flush();
                writer.close();
            } catch(FileNotFoundException e){
                System.out.println("Error opening the file " + SCORE_FILE_1);
                System.exit(0);
            }
        }
    }

}

