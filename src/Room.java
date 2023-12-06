/**
 * This class represents a room object
 *
 * @author Shushana Saki
 */
public class Room {
    boolean isLit;

    public Room() {
        this.isLit = false;
    }

    public void lightTorch() {
        this.isLit = true;
    }
}
