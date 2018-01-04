
import java.awt.Color;
import java.awt.Graphics;

public class Raindrop {

    private int x;
    private int y;
    private Color color = Color.CYAN;
    private final int WIDTH = 30;
    private final int HEIGHT = 45;
    private boolean isCaught = false;
    private int speed = 3;

    public Raindrop(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Raindrop(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillArc(x, y + HEIGHT / 3, WIDTH, WIDTH, 0, -180);

        int[] xPoints = {x, x + WIDTH / 2, x + WIDTH};
        int[] yPoints = {y + 2 * HEIGHT / 3, y, y + 2 * HEIGHT / 3};
        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void updatePosition() {
        this.y += 2*speed;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCaught(boolean isCaught) {
        this.isCaught = isCaught;
    }

    public boolean isCaught() {
        return isCaught;
    }

}
