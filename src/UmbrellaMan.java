
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class UmbrellaMan {

    private int x;
    private int y;
    private final int WIDTH = 200;
    private final int HEIGHT = 300;
    private int speed = 24;
    private Color color;
    private int face;
    private final int HAPPY = 0, UNHAPPY = 1, DEAD = 2;
    private boolean isMoveable = false;

    public UmbrellaMan(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
    }

    public UmbrellaMan(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        //draw umbrella	
        g.setColor(color);
        g.fillArc(x, y, WIDTH, HEIGHT / 2, 0, 180);

        g2.setPaint(new Color(1, 1, 1, 0.5f));

        //g2.setColor(new Color(237, 237, 237));
        g2.fillArc(x, y + 5 * HEIGHT / 24, WIDTH / 3, HEIGHT / 12, 0, 180);
        g2.fillArc(x + WIDTH / 3, y + 5 * HEIGHT / 24, WIDTH / 3, HEIGHT / 12, 0, 180);
        g2.fillArc(x + 2 * WIDTH / 3, y + 5 * HEIGHT / 24, WIDTH / 3, HEIGHT / 12, 0, 180);

        g2.setColor(Color.BLACK);
        g2.fillRect(x + WIDTH / 2, y + HEIGHT / 6 + HEIGHT / 24, WIDTH / 10, 3 * HEIGHT / 8 + HEIGHT / 48);
        g2.fillArc(x + 3 * WIDTH / 8, y + HEIGHT / 2, 9 * WIDTH / 40, HEIGHT / 6, 0, -180);

        //draw person
        //body
        g.setColor(Color.WHITE);
        g.fillOval(x, y + HEIGHT / 2, WIDTH / 4, HEIGHT / 6);
        g.setColor(Color.BLACK);
        g.drawOval(x, y + HEIGHT / 2, WIDTH / 4, HEIGHT / 6);
        g.fillRoundRect(x + WIDTH / 20, y + 2 * HEIGHT / 3, 3 * WIDTH / 20, HEIGHT / 6 + HEIGHT / 30, WIDTH / 10, HEIGHT / 15);
        g.fillRect(x + WIDTH / 8 - WIDTH / 40, y + 2 * HEIGHT / 3, WIDTH / 20, HEIGHT / 6);

        //arms
        int[] xPointsRightArm = {x + WIDTH / 8 + WIDTH / 40, x + WIDTH / 8 + WIDTH / 40, x + 3 * WIDTH / 8 + WIDTH / 100, x + 3 * WIDTH / 8};
        int[] yPointsRightArm = {y + 2 * HEIGHT / 3 + HEIGHT / 30, y + 2 * HEIGHT / 3 + HEIGHT / 15, y + 7 * HEIGHT / 12 + 10, y + 7 * HEIGHT / 12};
        g.fillPolygon(xPointsRightArm, yPointsRightArm, xPointsRightArm.length);
        int[] xPointsLeftArm = {x + WIDTH / 20, x + 3 * WIDTH / 40, x + WIDTH / 40, x - WIDTH / 100};
        int[] yPointsLeftArm = {y + 2 * HEIGHT / 3 + HEIGHT / 30, y + 2 * HEIGHT / 3 + HEIGHT / 30 + HEIGHT / 30, y + 5 * HEIGHT / 6 + HEIGHT / 30, y + 5 * HEIGHT / 6 + HEIGHT / 30};
        g.fillPolygon(xPointsLeftArm, yPointsLeftArm, xPointsLeftArm.length);

        //legs
        int[] xPointsRightLeg = {x + WIDTH / 8, x + WIDTH / 8 + WIDTH / 20, x + WIDTH / 4 - WIDTH/20, x + WIDTH / 4 - WIDTH / 10};
        int[] yPointsRightLeg = {y + 5 * HEIGHT / 6, y + 5 * HEIGHT / 6, y + HEIGHT, y + HEIGHT};
        g.fillPolygon(xPointsRightLeg, yPointsRightLeg, xPointsRightLeg.length);
        int[] xPointsLeftLeg = {x + 3 * WIDTH / 40, x + WIDTH / 8, x + WIDTH / 10, x + WIDTH/20};
        int[] yPointsLeftLeg = {y + 5 * HEIGHT / 6, y + 5 * HEIGHT / 6, y + HEIGHT, y + HEIGHT};
        g.fillPolygon(xPointsLeftLeg, yPointsLeftLeg, xPointsLeftLeg.length);

        //hat
        g.fillRect(x, y + HEIGHT / 2, WIDTH / 4, HEIGHT / 64);
        g.fillRect(x + WIDTH / 32, y + HEIGHT / 2 - HEIGHT / 8, 3 * WIDTH / 16, HEIGHT / 8);

        //tie
        g.setColor(Color.WHITE);
        int[] xPointsTie = {x + WIDTH / 8, x + WIDTH / 8 + WIDTH / 40, x + WIDTH / 8, x + WIDTH / 8 + WIDTH / 40, x + WIDTH / 8, x + WIDTH / 8 - WIDTH / 40, x + WIDTH / 8, x + WIDTH / 8 - WIDTH / 40};
        int[] yPointsTie = {y + 2 * HEIGHT / 3 + HEIGHT / 60, y + 2 * HEIGHT / 3 + HEIGHT / 30, y + 2 * HEIGHT / 3 + HEIGHT / 20, y + 2 * HEIGHT / 3 + 7 * HEIGHT / 60, y + 2 * HEIGHT / 3 + 2 * HEIGHT / 15, y + 2 * HEIGHT / 3 + 7 * HEIGHT / 60, y + 2 * HEIGHT / 3 + HEIGHT / 20, y + 2 * HEIGHT / 3 + HEIGHT / 30};
        g.fillPolygon(xPointsTie, yPointsTie, xPointsTie.length);

        //face
        g.setColor(Color.BLACK);
        if (face == HAPPY) {
            g.fillOval(x + WIDTH / 16, y + HEIGHT / 2 + HEIGHT / 16, WIDTH / 32, WIDTH / 32);
            g.fillOval(x + WIDTH / 8, y + HEIGHT / 2 + HEIGHT / 16, WIDTH / 32, WIDTH / 32);
            g.drawArc(x + WIDTH / 16, y + HEIGHT / 2 + HEIGHT / 12, WIDTH / 8, WIDTH / 16, 0, -180);
        } else if (face == UNHAPPY) {
            g.fillOval(x + WIDTH / 16, y + HEIGHT / 2 + HEIGHT / 16, WIDTH / 32, WIDTH / 32);
            g.fillOval(x + WIDTH / 8, y + HEIGHT / 2 + HEIGHT / 16, WIDTH / 32, WIDTH / 32);
            g.drawArc(x + WIDTH / 16, y + HEIGHT / 2 + HEIGHT / 10, WIDTH / 8, WIDTH / 16, 0, 180);
        } else if (face == DEAD) {
            g.drawLine(x + WIDTH / 20, y + 13 * HEIGHT / 24, x + WIDTH / 10, y + 7 * HEIGHT / 12);
            g.drawLine(x + WIDTH / 10, y + 13 * HEIGHT / 24, x + WIDTH / 20, y + 7 * HEIGHT / 12);
            g.drawLine(x + 3 * WIDTH / 20, y + 13 * HEIGHT / 24, x + WIDTH / 5, y + 7 * HEIGHT / 12);
            g.drawLine(x + WIDTH / 5, y + 13 * HEIGHT / 24, x + 3 * WIDTH / 20, y + 7 * HEIGHT / 12);
            g.drawLine(x + 3 * WIDTH / 40, y + 5 * HEIGHT / 8, x + 7 * WIDTH / 40, y + 5 * HEIGHT / 8);
        }

        
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

    public void setFace(int face) {
        this.face = face;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void moveLeft() {
        this.x -= speed;
    }

    public void moveRight() {
        this.x += speed;
    }

    public boolean isMoveable() {
        return isMoveable;
    }

    public void setMoveable(boolean isMoveable) {
        this.isMoveable = isMoveable;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void increaseSpeed() {
        speed += 2;
    }

    public void decreaseSpeed() {
        speed -= 2;
    }
}
