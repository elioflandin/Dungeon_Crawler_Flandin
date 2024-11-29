import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList; // List of objects to render
    private boolean gameOver = false; // Indicates whether the game is in a game-over state
    private Image gameOverImage; // Image displayed when the game ends

    /**
     * Constructs a new RenderEngine with an associated JFrame.
     * The render list is initialized to store objects that will be drawn on the screen.
     *
     * @param jFrame The JFrame that serves as the parent container for this JPanel.
     */
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    /**
     * Adds a single Displayable object to the render list, if not already present.
     *
     * @param displayable The object to be added to the render list.
     */
    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    /**
     * Adds multiple Displayable objects to the render list, if they are not already present.
     *
     * @param displayables A list of objects to be added to the render list.
     */
    public void addToRenderList(ArrayList<Displayable> displayables) {
        for (Displayable displayable : displayables) {
            if (!renderList.contains(displayable)) {
                renderList.add(displayable);
            }
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Draws the game-over screen on top of the existing rendered objects.
     *
     * @param g The Graphics context used for rendering.
     */
    private void drawGameOver(Graphics g) {
        gameOverImage = new ImageIcon("./img/Game_Over.png").getImage();
        if (gameOverImage != null) {
            // Draw the game-over image at the center of the screen
            int centerX = 192 - gameOverImage.getWidth(null) / 2;
            int centerY = 288 - gameOverImage.getHeight(null) / 2;
            g.drawImage(gameOverImage, centerX, centerY, null);
        } else {
            System.out.println("Error: Game_Over.png could not be loaded.");
        }
    }

    /**
     * Paints all the objects in the render list and optionally the game-over screen.
     *
     * @param g The Graphics context used for drawing.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw all objects in the render list
        for (Displayable renderObject : renderList) {
            renderObject.draw(g);
        }
        // Draw the game-over screen if the game is over
        if (gameOver) {
            drawGameOver(g);
        }
    }

    /**
     * Updates the rendering by repainting the panel.
     */
    @Override
    public void update() {
        this.repaint();
    }
}
