import java.awt.*;

public class Sprite implements Displayable {
    protected double x; // X-coordinate of the sprite
    protected double y; // Y-coordinate of the sprite
    protected final Image image; // Image representing the sprite
    protected final double width; // Width of the sprite
    protected final double height; // Height of the sprite

    /**
     * Constructs a new Sprite with the specified position, image, and dimensions.
     *
     * @param x      The x-coordinate of the sprite.
     * @param y      The y-coordinate of the sprite.
     * @param image  The image representing the sprite.
     * @param width  The width of the sprite.
     * @param height The height of the sprite.
     */
    public Sprite(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the sprite's image at its current position.
     *
     * @param g The Graphics context used for rendering.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
