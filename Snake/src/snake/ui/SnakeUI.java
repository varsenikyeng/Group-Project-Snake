package snake.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeUI extends JFrame {
    private JPanel welcomePanel;
    private JPanel gamePanel;
    private CardLayout cardLayout = new CardLayout();
    private SnakeGame wormGame;
    private HardSnakeGame pythonGame;
    public SnakeUI(){
        setTitle("Snake Game");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(cardLayout);
        initializeWelcomePanel();
        initializeLevelSelectionPanel();
        initializeRulesPanel();
        initializeWormPanel();
        setVisible(true);
        setResizable(false);

    }
    private void initializeWelcomePanel(){
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(157, 213, 139));
        JLabel welcomeLabel = new JLabel("WELCOME TO SNAKE GAME!", SwingConstants.CENTER);
        welcomeLabel.setForeground(new Color(40, 22, 56));
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 34));

        JPanel buttonPanel = new JPanel();
        JButton playButton = new JButton("Play");
        playButton.setForeground(new Color(51, 70, 44));
        playButton.setFont(new Font("Arial", Font.PLAIN, 38));
        playButton.setBackground(new Color(157, 213, 139));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "levelSelectionPanel" );
            }
        });
        buttonPanel.add(playButton);

        JButton rulesButton = new JButton("Rules");
        rulesButton.setForeground(new Color(51, 70, 44));
        rulesButton.setFont(new Font("Arial", Font.PLAIN, 38));
        rulesButton.setBackground(new Color(157, 213, 139));
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "rulesPanel");
            }
        });
        buttonPanel.add(rulesButton);

        welcomePanel.add(welcomeLabel, BorderLayout.NORTH);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        ImageIcon originalIcon = new ImageIcon("Snake/src/snake/ui/snake.png");
        Image image = originalIcon.getImage(); // Convert ImageIcon to Image
        Image resizedImage = image.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        JLabel iconLabel = new JLabel(resizedIcon);
        welcomePanel.add(iconLabel, BorderLayout.CENTER);

        ImageIcon appleIcon = new ImageIcon("Snake/src/snake/ui/apple.png");
        Image appleImage = appleIcon.getImage();
        Image resizedAppleImage = appleImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedAppleIcon = new ImageIcon(resizedAppleImage);
        JLabel appleLabel = new JLabel(resizedAppleIcon);
        welcomePanel.add(appleLabel, BorderLayout.WEST);

        ImageIcon mushroomIcon = new ImageIcon("Snake/src/snake/ui/mushroom.png");
        Image mushroomImage = mushroomIcon.getImage();
        Image resizedMushroomImage = mushroomImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedMushroomIcon = new ImageIcon(resizedMushroomImage);
        JLabel mushroomLabel = new JLabel(resizedMushroomIcon);
        welcomePanel.add(mushroomLabel, BorderLayout.EAST);

        getContentPane().add(welcomePanel, "welcomePanel");
        //setVisible(true);
    }
    private void initializeLevelSelectionPanel(){
        JPanel levelSelectionPanel  = new JPanel(new BorderLayout());
        levelSelectionPanel.setBackground(new Color(155, 175, 163));
        JLabel chooseLevel = new JLabel("CHOOSE THE DIFFICULTY LEVEL", SwingConstants.CENTER);
        chooseLevel.setForeground(new Color(26, 66, 14));
        chooseLevel.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel levelsPanel = new JPanel();
        JButton wormButton = new JButton("Worm");
        wormButton.setBackground(new Color(150, 210, 158));
        wormButton.setForeground(new Color(10, 10, 10));
        wormButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        wormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "boardPanel");
            }
        });
        levelsPanel.add(wormButton);

        JButton pythonButton = new JButton("Python");
        pythonButton.setBackground(new Color(150, 210, 158));
        pythonButton.setForeground(new Color(10, 10, 10));
        pythonButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        levelsPanel.add(pythonButton);


        JPanel backPanel = new JPanel(new BorderLayout());
        JButton backToMain = new JButton("Back");
        backToMain.setBackground(new Color(164, 203, 169));
        backToMain.setForeground(new Color(10, 10, 10));
        backToMain.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "welcomePanel");
            }
        });
        backPanel.setBackground(new Color(155, 175, 163));
        backPanel.add(backToMain, BorderLayout.WEST);

        levelSelectionPanel.add(chooseLevel, BorderLayout.CENTER);
        levelSelectionPanel.add(levelsPanel, BorderLayout.SOUTH);
        levelSelectionPanel.add(backPanel, BorderLayout.NORTH);


        getContentPane().add(levelSelectionPanel, "levelSelectionPanel");

    }
    private void initializeRulesPanel(){

        JPanel rulesPanel = new JPanel();
        rulesPanel.setLayout(new BoxLayout(rulesPanel, BoxLayout.Y_AXIS));
        rulesPanel.setBackground(new Color(106, 133, 121));

        JButton backToMain = new JButton("Back");
        backToMain.setBackground(new Color(130, 164, 133));
        backToMain.setForeground(new Color(10, 10, 10));
        backToMain.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        backToMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(getContentPane(), "welcomePanel");
            }
        });

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(backToMain);
        backPanel.setBackground(new Color(106, 133, 121));
        rulesPanel.add(backPanel);

        JLabel rules = new JLabel("THE RULES");
        rules.setForeground(Color.black);
        rules.setFont(new Font("Times New Roman", Font.BOLD, 40));
        JPanel rulesTitlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rulesTitlePanel.add(rules);
        rulesTitlePanel.setBackground(new Color(106, 133, 121));
        rulesPanel.add(rulesTitlePanel);

        JLabel theRulesOfWorm = new JLabel("Worm: The snake eats fruits and grows, but if it eats a mushroom, it shrinks.");
        theRulesOfWorm.setForeground(Color.black);
        theRulesOfWorm.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JPanel wormRulesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wormRulesPanel.setBackground(new Color(106, 133, 121));
        wormRulesPanel.add(theRulesOfWorm);
        rulesPanel.add(wormRulesPanel);

        JLabel theRulesOfPython = new JLabel("Python: The snake has three lives. If it eats fruits, it grows, but if it eats a mushroom, it loses a heart.");
        theRulesOfPython.setForeground(Color.black);
        theRulesOfPython.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JPanel pythonRulesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pythonRulesPanel.setBackground(new Color(106, 133, 121));
        pythonRulesPanel.add(theRulesOfPython);
        rulesPanel.add(pythonRulesPanel);

        getContentPane().add(rulesPanel, "rulesPanel");
    }

    private void initializeWormPanel(){
        wormGame = new SnakeGame();
        JPanel boardPanel = new JPanel(new GridLayout(wormGame.BOARD_ROWS, wormGame.BOARD_COLUMNS));
        boardPanel.setPreferredSize(new Dimension(400, 400));
        for (int i = 0; i < SnakeGame.BOARD_ROWS; i++) {
            for (int j = 0; j < SnakeGame.BOARD_COLUMNS; j++) {
                JLabel cellLabel = new JLabel();
                cellLabel.setBackground(new Color(113, 147, 101));
                boardPanel.add(cellLabel);
            }
        }
        boardPanel.setBackground(new Color(131, 178, 113));
        getContentPane().add(boardPanel, "boardPanel");


    }



    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (Exception e){
            e.printStackTrace();
        }
        new SnakeUI();
    }

}
