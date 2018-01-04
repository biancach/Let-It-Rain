
import java.awt.*;

public class WaterLayer {

    private int x, y, width, height;
    private Color color = Color.CYAN;
    private int singleWaterLayerHeight = 15;

    public WaterLayer(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y - height, width, height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void increaseHeight() {
        height += singleWaterLayerHeight;
    }

    public void decreaseHeight() {
        if (height >= 0) {
            height -= singleWaterLayerHeight;
        }
    }
}
