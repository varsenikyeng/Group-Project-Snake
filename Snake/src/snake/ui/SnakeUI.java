package snake.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.StyleConstants.setIcon;

public class SnakeUI extends JFrame {
    private JPanel welcomePanel;
    private JPanel gamePanel;
    private CardLayout cardLayout = new CardLayout();
    public SnakeUI(){
        setTitle("Snake Game");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(cardLayout);
        initializeWelcomePanel();
        initializeLevelSelectionPanel();
        initializeRulesPanel();
        setVisible(true);

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
        levelSelectionPanel.setBackground(new Color(83, 201, 150));
        JLabel chooseLevel = new JLabel("CHOOSE THE DIFFICULTY LEVEL", SwingConstants.CENTER);
        chooseLevel.setForeground(new Color(26, 66, 14));
        chooseLevel.setFont(new Font("Arial", Font.BOLD, 32));

        JPanel levelsPanel = new JPanel();
        JButton wormButton = new JButton("Worm");
        wormButton.setBackground(new Color(150, 210, 158));
        wormButton.setForeground(new Color(10, 10, 10));
        wormButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        levelsPanel.add(wormButton);

        JButton pythonButton = new JButton("Python");
        pythonButton.setBackground(new Color(150, 210, 158));
        pythonButton.setForeground(new Color(10, 10, 10));
        pythonButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        levelsPanel.add(pythonButton);

        levelSelectionPanel.add(chooseLevel, BorderLayout.CENTER);
        levelSelectionPanel.add(levelsPanel, BorderLayout.SOUTH);

        getContentPane().add(levelSelectionPanel, "levelSelectionPanel");
        //setVisible(true);

    }
    private void initializeRulesPanel(){
        JPanel rulesPanel = new JPanel(new BorderLayout());
        rulesPanel.setBackground(new Color(141, 157, 148));
        JLabel rules = new JLabel("RULES");
        JLabel theRulesOfWorm = new JLabel("Worm: The snake eats fruits and grows, but if it eats mushroom, it shrinks.");

        rules.setForeground(Color.black);
        theRulesOfWorm.setForeground(Color.black);
        rules.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        theRulesOfWorm.setFont(new Font("Times New Roman", Font.BOLD, 20));
        rulesPanel.add(rules, BorderLayout.NORTH);
        rulesPanel.add(theRulesOfWorm, BorderLayout.CENTER);
        getContentPane().add(rulesPanel, "rulesPanel");

        JLabel theRulesOfPython = new JLabel("Python: The snake has three lives. If it eats fruits, it grows, but if it eats a mushroom, it loses a heart.");
        theRulesOfPython.setForeground(Color.black);
        rules.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        theRulesOfPython.setFont(new Font("Times New Roman", Font.BOLD, 20));
        rulesPanel.add(theRulesOfPython, BorderLayout.SOUTH);
        getContentPane().add(rulesPanel, "rulesPanel");
        //setVisible(true);

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
