import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private Direction direction = Direction.SOUTH; // Direction of the Hero when the game begins
    protected double speed = 5; // Speed of the sprite
    private double timeBetweenFrame = 250; // Time in milliseconds between animation frames
    private boolean isWalking = true; // Indicates if the sprite is walking
    private final int spriteSheetNumberOfColumn = 10; // Number of columns in the sprite sheet
    boolean gameOver; // Indicates if the game is over

    /**
     * Constructs a DynamicSprite, Extends SolidSprite, with the specified position, image, and dimensions.
     *
     * @param x      The x-coordinate of the sprite.
     * @param y      The y-coordinate of the sprite.
     * @param image  The image representing the sprite.
     * @param width  The width of the sprite.
     * @param height The height of the sprite.
     */
    public DynamicSprite(double x, double y, Image image, double width, double height) {
        super(x, y, image, width, height);
    }

    public int getX() {
        return (int) this.x;
    }

    public int getY() {
        return (int) this.y;
    }

    public int getWidth() {
        return (int) this.width;
    }

    public int getHeight() {
        return (int) this.height;
    }

    /**
     * Determines if movement in the current direction is possible, based on the environment.
     *
     * @param environment A list of all sprites in the current environment.
     * @return True if movement is possible, false otherwise.
     */
    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch (direction) {
            case EAST -> moved.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case WEST -> moved.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case NORTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
            case SOUTH -> moved.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
        }

        for (Sprite s : environment) {
            if ((s instanceof SolidSprite) && (s != this)) {
                if (((SolidSprite) s).intersect(moved)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Moves the sprite in its current direction.
     */
    private void move() {
        switch (direction) {
            case NORTH -> this.y -= speed;
            case SOUTH -> this.y += speed;
            case EAST -> this.x += speed;
            case WEST -> this.x -= speed;
        }
    }

    /**
     * Attempts to move the sprite. If movement is not possible, applies collision logic.
     *
     * @param environment A list of all sprites in the current environment.
     */
    public void moveIfPossible(ArrayList<Sprite> environment, boolean gameOver) {
        if (gameOver) {
            return;  // Do not allow movement if the game is over
        }

        if (isMovingPossible(environment)) {
            move();
        } else {
            if (this instanceof HeroWithHealth) {
                ((HeroWithHealth) this).takeDamage(1); // Apply damage (-1PV) if the sprite is a HeroWithHealth
            }
        }
    }

    /**
     * Draws the animated sprite, selecting the correct frame from the sprite sheet based on the time and direction.
     *
     * @param g The Graphics context used for rendering.
     */
    @Override
    public void draw(Graphics g) {
        int index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);
    }
}
