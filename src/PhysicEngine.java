import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The PhysicEngine class handles the movement of dynamic sprites within the game environment.
 * It updates the positions of the sprites based on their possible movement, considering obstacles and boundaries.
 */
public class PhysicEngine implements Engine {
    private ArrayList<DynamicSprite> movingSpriteList;  // List of dynamic sprites that can move
    private ArrayList<Sprite> environment;              // List of all sprites in the environment, including obstacles

    public PhysicEngine() {
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

    /**
     * Adds a sprite to the environment list if it is not already present.
     * (no usage for the moment)
     *
     * @param sprite Sprite to be added to the environment
     */
    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite)) {
            environment.add(sprite);
        }
    }

    /**
     * @return The list of environment sprites
     */
    public ArrayList<Sprite> getEnvironment() {
        return environment;
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }

    /**
     * Adds a dynamic sprite to the list of moving sprites if it is not already present.
     *
     * @param sprite The dynamic sprite to be added to the moving list
     */
    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite)) {
            movingSpriteList.add(sprite);
        }
    }

    /**
     * Updates the state of all moving sprites by attempting to move them.
     */
    @Override
    public void update() {
        // Iterate through all moving sprites and attempt to move them
        for (DynamicSprite dynamicSprite : movingSpriteList) {
            dynamicSprite.moveIfPossible(environment);  // Check if the sprite can move and update position
        }
    }
}
