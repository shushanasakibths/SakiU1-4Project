/**
 * This class represents a game object
 *
 * @author Shushana Saki
 */
public class Player {
    private int health;

    public Player() {
        this.health = 3;
    }

    public Player(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void decreaseHealth() {
        this.health -= 1;
    }

    public void decreaseHealth(int damage) {
        this.health -= damage;
    }

    public void increaseHealth() {
        this.health += 1;
    }

    public void increaseHealth(int healthIncrease) {
        this.health += healthIncrease;
    }
}
