import java.awt.*;
import java.util.ArrayList;

public class HeroWithHealth extends DynamicSprite {
    private int maxHealth = 100; // Maximum health value
    private int health = 100; // Current health value
    private Image healthBarImage; // Image used to display the health bar

    /**
     * Constructs a hero with health attributes and a health bar.
     *
     * @param x              Initial x-coordinate of the hero.
     * @param y              Initial y-coordinate of the hero.
     * @param spriteSheet    Image containing the sprite for the hero.
     * @param width          Width of the hero's sprite.
     * @param height         Height of the hero's sprite.
     * @param healthBarImage Image used to render the health bar.
     */
    public HeroWithHealth(int x, int y, Image spriteSheet, int width, int height, Image healthBarImage) {
        super(x, y, spriteSheet, width, height); // Initialize DynamicSprite properties
        this.healthBarImage = healthBarImage; // Set the health bar image
        this.health = this.maxHealth; // Set initial health to maximum
        System.out.println("Initial health: " + health); // Debugging statement because of an older issue
    }

    public void setSpeed(double speed) {
        super.speed = speed; // Update speed attribute inherited from DynamicSprite
    }

    public double getSpeed() {
        return super.speed; // Retrieve speed value
    }

    /**
     * @param damage The amount of health to subtract.
     */
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage); // Ensure health does not drop below 0
    }

    /**
     * @param amount The amount of health to restore.
     */
    public void heal(int amount) {
        health = Math.min(maxHealth, health + amount); // Ensure health does not exceed maxHealth
    }

    public int getHealth() {
        return health;
    }

    /**
     * Draws the hero's sprite and health bar.
     *
     * @param g Graphics context for rendering.
     */
    @Override
    public void draw(Graphics g) {
        super.draw(g); // Draw the hero's sprite

        // Render the health bar if an image is provided
        if (healthBarImage != null) {
            int totalStates = 6; // Number of health bar segments
            int barWidth = healthBarImage.getWidth(null); // Width of the health bar image
            int barHeight = healthBarImage.getHeight(null) / totalStates; // Height of one segment

            // Calculate the health bar state (0 = empty, 5 = full)
            int healthState = (-1) * (Math.max(0, Math.min(totalStates - 1,
                    (int) ((health / (double) maxHealth) * (totalStates - 1))))) + 5;

            // Determine the health bar position above the hero
            int barX = (int) (this.x + this.width / 2 - barWidth / 2);
            int barY = (int) (this.y - barHeight - 5);

            // Render the appropriate segment of the health bar image
            g.drawImage(healthBarImage,
                    barX, barY, // Destination coordinates
                    barX + barWidth, barY + barHeight, // Destination dimensions
                    0, healthState * barHeight, // Source coordinates
                    barWidth, (healthState + 1) * barHeight, // Source dimensions
                    null);
        }
    }

    /**
     * Checks for collisions between the hero and obstacles in the environment.
     * (no use for the moment)
     *
     * @param environment A list of sprites representing the game's environment.
     */
    public void checkCollisionWithObstacles(ArrayList<Sprite> environment) {
        for (Sprite s : environment) { // Loop through all environment sprites
            if (s instanceof SolidSprite) { // Check if the sprite is a solid object
                // Check if the hero's hitbox intersects the solid sprite's hitbox
                if (super.getHitBox().intersects(((SolidSprite) s).getHitBox())) {
                    takeDamage(1); // Inflict damage if a collision occurs
                }
            }
        }
    }
}
