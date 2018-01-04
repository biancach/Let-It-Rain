
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;

public class Model implements ActionListener {

    private UmbrellaMan umbrella;
    private WaterLayer water;
    private ArrayList<Raindrop> raindrops;
    private ArrayList<RaindropObserver> raindropObservers;
    private EndGameObserver endGameObserver;
    private boolean isGameOver;
    private int panelWidth, panelHeight;
    private Timer updatePositionsTimer, raindropCreationTimer;
    private boolean isMovingRight, isMoving;
    private int score = 0, highscore = 0;
    private int currentRaindropSpeed = 3;
    private final int HAPPY = 0, UNHAPPY = 1, DEAD = 2;
    

    public Model() {
        this.umbrella = new UmbrellaMan(0, 199);
        this.water = new WaterLayer(0, 0, 0, 0);
        this.raindrops = new ArrayList<Raindrop>();
        this.raindropObservers = new ArrayList<RaindropObserver>();
        this.updatePositionsTimer = new Timer(50, new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                updateRaindropPositions();
                updateUmbrellaAndGame();
            }
        });
    }

    public void setUpNewGame(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        umbrella.setX(panelWidth/2 - umbrella.getWidth()/2);
        umbrella.setY(panelHeight - umbrella.getHeight());
        umbrella.setFace(HAPPY);
        water.setY(panelHeight);
        water.setWidth(panelWidth);
        water.setHeight(0);
        raindropCreationTimer = new Timer(750, this);
        raindrops.clear();
        isGameOver = false;
        score = 0;
        currentRaindropSpeed = 3;
    }

    public void startGame() {
        if (raindropCreationTimer != null) {
            raindropCreationTimer.start();
        }
        if (updatePositionsTimer != null) {
            updatePositionsTimer.start();
        }
        umbrella.setMoveable(true);
        isMoving = false;
        umbrella.setFace(HAPPY);
        umbrella.setSpeed(24);
        score = 0;
        currentRaindropSpeed = 3;
    }

    public void pauseGame() {
        if (raindropCreationTimer != null) {
            raindropCreationTimer.stop();
        }
        if (updatePositionsTimer != null) {
            updatePositionsTimer.stop();
        }
        umbrella.setMoveable(false);
    }

    public void resumeGame() {
        if (raindropCreationTimer != null) {
            raindropCreationTimer.start();
        }
        if (updatePositionsTimer != null) {
            updatePositionsTimer.start();
        }
        umbrella.setMoveable(true);
    }

    public void endGame() {
        isGameOver = true;
        if (raindropCreationTimer != null) {
            raindropCreationTimer.stop();
        }
        if (updatePositionsTimer != null) {
            updatePositionsTimer.stop();
        }
        umbrella.setMoveable(false);
        raindrops.clear();
    }

    public void catchRaindrops() {
        for (Raindrop drop : raindrops) {
            if ((drop.getY() + drop.getHeight() > umbrella.getY() && drop.getY() + drop.getHeight() < umbrella.getY() + umbrella.getHeight() / 4)
                    && (drop.getX() > umbrella.getX() && drop.getX() + drop.getWidth() < umbrella.getX() + umbrella.getWidth())) {
                drop.setCaught(true);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        addRaindrop();
        notifyRaindropObservers();
    }

    public void registerRaindropObserver(RaindropObserver observer) {
        raindropObservers.add(observer);
    }

    public void registerEndGameObserver(EndGameObserver observer) {
        this.endGameObserver = observer;
    }

    public void notifyRaindropObservers() {
        for (RaindropObserver observer : raindropObservers) {
            observer.raindropsChanged();
        }
    }

    public void notifyEndGameObserver() {
        endGameObserver.dead();
    }

    public void updateRaindropPositions() {
        catchRaindrops();
        Iterator iterator = raindrops.iterator();
        while (iterator.hasNext()) {
            Raindrop raindrop = (Raindrop) iterator.next();
            raindrop.updatePosition();
            if (raindrop.getY() + raindrop.getHeight() > water.getY() - water.getHeight()) {
                iterator.remove();
                water.increaseHeight();
                umbrella.setFace(UNHAPPY);
                umbrella.decreaseSpeed();
            } else if (raindrop.isCaught()) {
                iterator.remove();
                umbrella.setFace(HAPPY);
                score++;
            }
            notifyRaindropObservers();
        }
        if (score > highscore) {
            highscore = score;
        }
    }

    public void updateUmbrellaAndGame() {
        if (isMovingRight && isMoving && umbrella.getX() < panelWidth - umbrella.getWidth()) {
            umbrella.moveRight();
        } else if (!isMovingRight && isMoving && umbrella.getX() > 0) {
            umbrella.moveLeft();
        }
        if (water.getHeight() >= 150) {
            umbrella.setFace(DEAD);
            notifyEndGameObserver();
        }
    }

    public int getNewRandomRaindropLocation() {
        return (int) (Math.random() * (panelWidth - 30));
    }

    public void addRaindrop() {
        raindrops.add(new Raindrop(getNewRandomRaindropLocation(), 0, currentRaindropSpeed));
    }

    public void moveRight(boolean direction) {
        isMovingRight = direction;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void move(boolean moving) {
        isMoving = moving;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public UmbrellaMan getUmbrella() {
        return umbrella;
    }

    public WaterLayer getWater() {
        return water;
    }

    public ArrayList<Raindrop> getRaindrops() {
        return raindrops;
    }

    public int getScore() {
        return score;
    }

    public int getHighscore() {
        return highscore;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
    
    public void setSpeed(int newSpeed) {
    	this.currentRaindropSpeed = newSpeed;
    }
}
