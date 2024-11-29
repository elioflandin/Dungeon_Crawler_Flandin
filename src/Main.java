import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame; // Main frame for rendering the game
    RenderEngine renderEngine; // Handles all rendering operations
    GameEngine gameEngine; // Controls game logic
    PhysicEngine physicEngine; // Manages physics calculations
    int heroMaxHealth = 100; // Maximum health of the hero
    DynamicSprite hero; // Represents the player character

    public Main() throws Exception {
        // Initialize the game frame
        displayZoneFrame = new JFrame("Dungeon Crawler");
        displayZoneFrame.setSize(1800, 1056); // Set the frame size
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); // Exit on close

        // Create the hero character
        hero = new HeroWithHealth(200, 300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")), 48, 50,
                ImageIO.read(new File("./img/life_bar_remove.png")));

        // Initialize game engines
        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine((HeroWithHealth) hero, physicEngine.getEnvironment());
        gameEngine.setRenderEngine(renderEngine);

        // Set up timers to update rendering, physics, and game logic at fixed intervals
        Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
        Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(50, (time) -> physicEngine.update());

        renderTimer.start(); // Start rendering updates
        gameTimer.start(); // Start game logic updates
        physicTimer.start(); // Start physics updates

        // Add the render engine as the main display component
        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        // Load the game level and configure engines
        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList()); // Add level sprites to rendering
        renderEngine.addToRenderList(hero); // Add the hero to rendering
        physicEngine.addToMovingSpriteList(hero); // Add the hero to physics calculations
        physicEngine.setEnvironment(level.getSolidSpriteList()); // Set level obstacles for collision

        // Add key listener to the game engine for user input
        displayZoneFrame.addKeyListener(gameEngine);
    }

    /**
     * Entry point for the application.
     *
     * @param args Command line arguments (not used).
     * @throws Exception If the game fails to initialize.
     */
    public static void main(String[] args) throws Exception {
        new Main(); // Create and initialize the game
    }
}
