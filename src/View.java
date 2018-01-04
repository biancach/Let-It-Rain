
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class View extends JFrame {

    private JPanel mainPanel, centerPanel;
    private CardLayout cardLayout;
    private GamePanel gamePanel;
    private StartPanel startPanel;
    private JButton startGame, pauseGame, resumeGame, endGame;
    private JLabel message, scoreLabel, highscoreLabel;
    private JSlider speedSlider;
    private UmbrellaMan umbrella = new UmbrellaMan(0, 199);
    private ArrayList<Raindrop> raindrops = new ArrayList<Raindrop>();
    private WaterLayer water = new WaterLayer(0, 0, 0, 0);
    private int score = 0, highscore = 0;

    public View() {
        super("Let It Rain");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setFocusable(true);
        this.setResizable(false);

        gamePanel = new GamePanel();
        startPanel = new StartPanel();
        
        centerPanel = new JPanel();
        cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        centerPanel.add(startPanel, "startPanel");
        centerPanel.add(gamePanel, "gamePanel");
               
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 1));
        controlPanel.setBackground(Color.GREEN);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.GREEN);
        controlPanel.add(buttonPanel);

        startGame = new JButton("Start Game");
        buttonPanel.add(startGame);
        pauseGame = new JButton("Pause Game");
        buttonPanel.add(pauseGame);
        resumeGame = new JButton("Resume Game");
        buttonPanel.add(resumeGame);
        endGame = new JButton("End Game");
        buttonPanel.add(endGame);

        //message panel
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(Color.GREEN);
        messagePanel.setLayout(new GridLayout(2, 0));
        controlPanel.add(messagePanel);


        //instruction panel
        JPanel instructionPanel = new JPanel();
        instructionPanel.setBackground(Color.GREEN);
        instructionPanel.setLayout(new GridLayout(2, 0));
        messagePanel.add(instructionPanel);

        message = new JLabel("Catch the raindrops before they reach the ground!", JLabel.CENTER);
        message.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        instructionPanel.add(message);
        JLabel instructions = new JLabel("If they reach the ground, the water will rise and the man will slow down. "
                + "Don't let him drown!", JLabel.CENTER);
        instructions.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        instructionPanel.add(instructions);

        //score panel
        JPanel scorePanel = new JPanel();
        scorePanel.setBackground(Color.GREEN);
        scorePanel.setLayout(new GridLayout(1, 0));
        messagePanel.add(scorePanel);

        scoreLabel = new JLabel("Score: " + score, JLabel.CENTER);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        scorePanel.add(scoreLabel);
        highscoreLabel = new JLabel("Highscore: " + highscore);
        highscoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        scorePanel.add(highscoreLabel);

        //slider
        JLabel speedSliderLabel = new JLabel("Raindrop Speed: ");
        speedSliderLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        buttonPanel.add(speedSliderLabel);
        speedSlider = new JSlider(1, 5, 3);
        speedSlider.setBackground(Color.GREEN);
        speedSlider.setPaintLabels(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        buttonPanel.add(speedSlider);

    }

    @Override
    public void addKeyListener(KeyListener listener) {
        super.addKeyListener(listener);
        gamePanel.addKeyListener(listener);
    }

    public void addActionListener(ActionListener listener) {
        startGame.addActionListener(listener);
        pauseGame.addActionListener(listener);
        resumeGame.addActionListener(listener);
        endGame.addActionListener(listener);
    }

    public void addChangeListener(ChangeListener listener) {
        speedSlider.addChangeListener(listener);
    }

    public void setEnabledStartGameButton(boolean enabled) {
        startGame.setEnabled(enabled);
    }

    public void setEnabledPauseGameButton(boolean enabled) {
        pauseGame.setEnabled(enabled);
    }

    public void setEnabledResumeGameButton(boolean enabled) {
        resumeGame.setEnabled(enabled);
    }

    public void setEnabledEndGameButton(boolean enabled) {
        endGame.setEnabled(enabled);
    }

    public void setUmbrella(UmbrellaMan umbrella) {
        this.umbrella = umbrella;
    }

    public void setWater(WaterLayer water) {
        this.water = water;
    }

    public void setRaindrops(ArrayList<Raindrop> raindrops) {
        this.raindrops = raindrops;
    }

    public void setMessage(String str) {
        message.setText(str);
        message.requestFocus();
    }

    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setHighscore(int highscore) {
        highscoreLabel.setText("Highscore: " + highscore);
    }

    public void showPanel(String panelName) {
    	cardLayout.show(centerPanel, panelName);
    }

    public int getGamePanelWidth() {
        return gamePanel.getWidth();
    }

    public int getGamePanelHeight() {
        return gamePanel.getHeight();
    }

    public JSlider getSlider() {
        return speedSlider;
    }

    @Override
    public void requestFocus() {
        gamePanel.requestFocus();
    }

    public class GamePanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            g.setColor(new Color(237, 237, 237));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        	for (Raindrop raindrop : raindrops) {
                raindrop.draw(g);
            }
            water.draw(g);
            umbrella.draw(g);
        }
    }

    public class StartPanel extends JPanel {

        private Raindrop[] startRaindrops = new Raindrop[200];

        public StartPanel() {
            for (int i = 0; i < startRaindrops.length; i++) {
                int randomXLocation = (int) (Math.random() * (800 - 30));
                int randomYLocation =  (int) (Math.random() * (750 - 50));
                startRaindrops[i] = new Raindrop(randomXLocation, randomYLocation);
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            for (int i = 0; i < startRaindrops.length; i++) {
                startRaindrops[i].draw(g);
            }
            
        }
    }
}
