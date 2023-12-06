import java.util.Scanner;
public class GlitchtrapBoss {
    private int health;

    private static Scanner scanner = new Scanner(System.in);

    public GlitchtrapBoss(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public void takeDamage() {
        this.health -= 1;
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public static int chooseBossHealth() {
        System.out.println("Choose Glitchtrap Boss health (1 to 10):");
        int bossHealth = scanner.nextInt();

        if (bossHealth < 1 || bossHealth > 10) {
            System.out.println("Invalid boss health. Default health set to 5.");
            return 5;
        }

        return bossHealth;
    }
}
