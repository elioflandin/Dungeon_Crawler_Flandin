import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {

    /**
     * Constructs a SolidSprite, Extends Sprite, with the specified position, image, and dimensions.
     *
     * @param x      The x-coordinate of the sprite.
     * @param y      The y-coordinate of the sprite.
     * @param image  The image representing the sprite.
     * @param width  The width of the sprite.
     * @param height The height of the sprite.
     */
    public SolidSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    /**
     * Retrieves the hitbox of the sprite, defined as a rectangle matching its position and size.
     *
     * @return A Rectangle2D representing the sprite's hitbox.
     */
    public Rectangle2D getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    /**
     * Checks whether this sprite's hitbox intersects with another hitbox.
     *
     * @param hitBox The hitbox to test for intersection.
     * @return True if the hitboxes intersect, false otherwise.
     */
    public boolean intersect(Rectangle2D.Double hitBox) {
        return this.getHitBox().intersects(hitBox);
    }
}
