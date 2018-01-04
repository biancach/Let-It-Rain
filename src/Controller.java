
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Controller implements KeyListener, ActionListener, ChangeListener, RaindropObserver, EndGameObserver {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        model.registerRaindropObserver(this);
        model.registerEndGameObserver(this);
        view.addActionListener(this);
        view.addChangeListener((ChangeListener) this);
        view.addKeyListener(this);
        view.setUmbrella(model.getUmbrella());
        view.setRaindrops(model.getRaindrops());
        view.setWater(model.getWater());
        setUpNewGame();

    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if (model.getUmbrella().isMoveable()) {
            if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
                model.move(true);
                model.moveRight(false);
            } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
                model.move(true);
                model.moveRight(true);
            }
            view.repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            model.move(false);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            model.move(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String command = evt.getActionCommand();
        if (command.equals("Start Game")) {
            startGame();
            view.showPanel("gamePanel");
        } else if (command.equals("Pause Game")) {
            pauseGame();
        } else if (command.equals("Resume Game")) {
            resumeGame();
        } else if (command.equals("End Game")) {
            model.getUmbrella().setFace(2);
        	endGame();
        }
        view.requestFocus();

    }

    @Override
    public void stateChanged(ChangeEvent evt) {
        if (evt.getSource() == view.getSlider()) {
            for (Raindrop raindrop : model.getRaindrops()) {
                raindrop.setSpeed(view.getSlider().getValue()); //only applies to existing raindrops!!!
            }
            model.setSpeed(view.getSlider().getValue());
        }
        view.requestFocus();
    }

    @Override
    public void raindropsChanged() {
        view.setRaindrops(model.getRaindrops());
        view.setScore(model.getScore());
        view.setHighscore(model.getHighscore());
        view.repaint();
    }

    @Override
    public void dead() {
        endGame();
    }

    public void setUpNewGame() {
    	view.showPanel("startPanel");
        view.setEnabledStartGameButton(true);
        view.setEnabledPauseGameButton(false);
        view.setEnabledResumeGameButton(false);
        view.setEnabledEndGameButton(false);
        model.setUpNewGame(view.getGamePanelWidth(), view.getGamePanelHeight());
        model.getUmbrella().setFace(0);
    }

    public void startGame() {
        view.setEnabledStartGameButton(false);
        view.setEnabledPauseGameButton(true);
        view.setEnabledResumeGameButton(false);
        view.setEnabledEndGameButton(true);
        view.setMessage("Catch the raindrops before they reach the ground!");
        model.setUpNewGame(view.getGamePanelWidth(), view.getGamePanelHeight());
        model.startGame();
    }

    private void pauseGame() {
        view.setEnabledStartGameButton(false);
        view.setEnabledPauseGameButton(false);
        view.setEnabledResumeGameButton(true);
        view.setEnabledEndGameButton(true);
        model.pauseGame();
    }

    public void resumeGame() {
        view.setEnabledStartGameButton(false);
        view.setEnabledPauseGameButton(true);
        view.setEnabledResumeGameButton(false);
        view.setEnabledEndGameButton(true);
        model.resumeGame();
    }

    private void endGame() {
        view.setEnabledStartGameButton(true);
        view.setEnabledPauseGameButton(false);
        view.setEnabledResumeGameButton(false);
        view.setEnabledEndGameButton(false);
        model.endGame();
    }

    @Override
    public void keyTyped(KeyEvent evt) {
    }
}
