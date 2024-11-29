import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Image;

public class GameEngine implements Engine, KeyListener {
    private HeroWithHealth hero; // The main character controlled by the player
    private ArrayList<Sprite> environment; // List of environment sprites (obstacles, etc.)
    private Image gameOverImage; // Image displayed when the game ends
    private boolean gameOver = false; // Indicates whether the game is over
    private RenderEngine renderEngine; // Responsible for rendering the game state

    /**
     * Constructs a new GameEngine with a hero and an environment.
     *
     * @param hero        The hero controlled by the player.
     * @param environment A list of sprites representing the game's environment.
     */
    public GameEngine(HeroWithHealth hero, ArrayList<Sprite> environment) {
        this.hero = hero;
        this.environment = environment;
//        this.gameOverImage = new ImageIcon("./img/Game_Over.png").getImage();
//        if (this.gameOverImage == null) {
//            System.out.println("Error: Game_Over.png could not be loaded.");
//        }
    }

    /**
     * Updates the game logic. Checks for game-over conditions and hero collisions.
     * If the hero's health reaches zero, the game ends.
     */
    @Override
    public void update() {
        if (hero.getHealth() <= 0) { // Check if the hero's health is depleted
            gameOver = true; // Set game-over state
            renderEngine.setGameOver(true);
        }
        if (!gameOver) {
            hero.checkCollisionWithObstacles(environment); // Check collisions with environment
        }
    }

    /**
     * Links a RenderEngine to the GameEngine.
     *
     * @param renderEngine The RenderEngine responsible for drawing the game.
     */
    public void setRenderEngine(RenderEngine renderEngine) {
        this.renderEngine = renderEngine;
    }

    /**
     * Handles key press events for controlling the hero and triggering actions.
     *
     * @param e The KeyEvent triggered by a key press.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyCode());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH); // Move hero upward
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH); // Move hero downward
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST); // Move hero to the left
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST); // Move hero to the right
                break;
        }

        // Additional controls for actions
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            hero.takeDamage(20); // Press SPACE to take damage
        }
        if (key == KeyEvent.VK_H) {
            hero.heal(20); // Press H to heal the hero
        }
        if (key == KeyEvent.VK_SHIFT) {
            hero.setSpeed(10.0); // Press SHIFT for increased speed
        }
    }

    /**
     * Handles key release events. Resets hero speed when SHIFT is released.
     *
     * @param e The KeyEvent triggered by a key release.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            hero.setSpeed(5.0); // Reset to normal speed when SHIFT is released
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but required
    }

//    public void drawGameOver(Graphics g) {
//        g.drawImage(gameOverImage, 0, 0, null); // Display the game-over image
//    }

//    public void draw(Graphics g) {
//        if (gameOver) {
//            drawGameOver(g); // If the game is over, draw the game-over screen
//        } else {
//            hero.draw(g); // Otherwise, draw the hero
//        }
//    }
}
