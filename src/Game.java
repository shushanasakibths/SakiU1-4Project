/**
 * This class represents a game object
 *
 * @author Shushana Saki
 */
import java.util.Scanner;
public class Game {
    private Player player;
    private Room[] rooms;
    private Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.player = new Player();
        this.rooms = new Room[7];
        for (int i = 0; i < 7; i++) {
            this.rooms[i] = new Room();
        }
    }

    public Game(int playerHealth) {
        this.scanner = new Scanner(System.in);
        this.player = new Player(playerHealth);
        this.rooms = new Room[7];
        for (int i = 0; i < 7; i++) {
            this.rooms[i] = new Room();
        }
    }

    public void start() {
        System.out.println("Welcome to the Glitchtrap Castle!\nYour soul is now connected to the game!\nYou've agreed to give Glitchtrap your soul if you die in game...");
        System.out.println();
        exploreCastle();
    }

    private void exploreCastle() {
        System.out.println("You are in a dark room with a locked door.\nIt is SO dark, you can only see whats directly in front of you...\nuntil you notice the moonlight bounce off something shiny...\ntorch holders!");
        System.out.println();
        System.out.println("To proceed, you need to light all 4 torches in the next room.");
        handleFloatingHeads();

        if (lightTorches()) {
            System.out.println("You have opened the door and entered a room with Floating Glitchtrap Heads!");
            handleFloatingHeads();
            randHeart();
            System.out.println("You've lit all the torches. Now you can go to the next room to get the key.");
            System.out.println("Do you want to proceed to the next room? (yes/no)");
            String nextRoom = scanner.nextLine();
            System.out.println();

            if (nextRoom.equals("yes")) {
                System.out.println("You've got the key and entered a room with Glitchtrap Blockers!");
                handleBlockers();
                System.out.println("Would you like to search for a heart? yes/no");
                String searchHeart = scanner.nextLine();
                while (!searchHeart.equals("no")) {
                    if (searchHeart.equals("yes")) {
                        findHeart();
                        break;
                    } else {
                        System.out.println("Please enter a valid response!");
                        searchHeart = scanner.nextLine();
                    }
                }
                System.out.println();
                System.out.println("You've successfully passed the blockers and found a purple chest.");
                GlitchtrapBoss boss = new GlitchtrapBoss(GlitchtrapBoss.chooseBossHealth());
                battleGlitchtrapBoss(boss);
                System.out.println();
                System.out.println("You can finally open the chest! There is a key inside....");

                System.out.println("You use the purple key to unlock the exit!");

                System.out.println("Congratulations! You've defeated Glitchtrap...your soul is free\nhold on...what is that glitching??");
            } else {
                System.out.println("Glitchtrap thanks you for willingly offering yourself...happy murdering!");
            }
        }
    }

    private boolean lightTorches() {
        boolean light;
        for (int i = 0; i < 4; i++) {
            System.out.println("To light torch " + (i + 1) + ", solve the following math problem, answer as a int:");
            light = solveMathProblem();
            if (!light) {
                System.out.println("Incorrect answer. You failed to light the torch. Try again.");
                i--;
            } else {
                System.out.println("Correct! You lit torch " + (i + 1));
                rooms[1].lightTorch();
            }
            System.out.println();
        }
        return true;
    }

    private boolean solveMathProblem() {
        int problem = (int) (Math.random() * 3) + 1;
        int num1 = (int) (Math.random() * 100) + 1;
        int num2 = (int) (Math.random() * 100) + 1;
        if (problem == 1) {
            System.out.print(num1 + " + " + num2 + " = ");
            int ans = num1 + num2;
            int playerAnswer = scanner.nextInt();
            if (playerAnswer == ans) {
                scanner.nextLine();
                return true;
            } else {
                return false;
            }
        } else if (problem == 2) {
            System.out.print(num1 + " - " + num2 + " = ");
            int ans = num1 - num2;
            int playerAnswer = scanner.nextInt();
            if (playerAnswer == ans) {
                scanner.nextLine();
                return true;
            } else {
                return false;
            }
        } else if (problem == 3) {
            System.out.print(num1 + " * " + num2 + " = ");
            int ans = num1 * num2;
            int playerAnswer = scanner.nextInt();
            if (playerAnswer == ans) {
                scanner.nextLine();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    private void handleFloatingHeads() {
        System.out.println("You encounter Floating Glitchtrap Heads!");
        System.out.println("They will randomly attack you, draining health.");

        int attackChance = (int) (Math.random() * 10) + 1;

        if (attackChance < 5) {
            player.decreaseHealth();
            System.out.println();
            System.out.println("A Floating Glitchtrap Head attacked you. You lost one heart.");
            System.out.println("Current health: " + player.getHealth());
            System.out.println();

            if (player.getHealth() <= 0) {
                System.out.println("Game over. You ran out of health.");
                System.exit(0);
            }
        } else {
            System.out.println("The Floating Glitchtrap Heads did not attack. Lucky you!");
            System.out.println();
        }
    }

    private void randHeart() {
        int chance =(int) (Math.random() + 3) + 1;
        if (chance < 3) {
            player.increaseHealth(1);
            System.out.println("Scott cawthon was generous and gifted you a heart! You gained 1 health!");
        }
    }

    private void findHeart() {
        System.out.println("Searching for a heart...");

        outerLoop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                int searchResult =(int) (Math.random() + 3) + 1;
                if (searchResult < 3) {
                    player.increaseHealth(1);
                    System.out.println("You found a heart and gained 1 health!");
                    System.out.println("Current health: " + player.getHealth());
                    break outerLoop;
                } else {
                    System.out.println("You didn't find a heart in this area.");
                }
            }
        }
        System.out.println("Search complete. No hearts found this time.");
    }

    private void battleGlitchtrapBoss(GlitchtrapBoss boss) {
        System.out.println("Looks like Glitchtrap himself is guarding the chest you need!");
        System.out.println("Prepare for a battle!");

        while (!boss.isDefeated()) {
            System.out.println("Choose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Heal");

            int choice = scanner.nextInt();
            // https://docs.oracle.com/javase/tutorial/java/nutsandbolts/switch.html
            switch (choice) {
                case 1:
                    int playerDamage = (int) (Math.random() * 4) + 1;
                    boss.takeDamage(playerDamage);
                    System.out.println("You dealt " + playerDamage + " damage to the Glitchtrap Boss!");
                    break;

                case 2:
                    int healAmount = (int) (Math.random() * 4) + 1;
                    player.increaseHealth(healAmount);
                    System.out.println("You healed for " + healAmount + " health!");
                    if (healAmount == 1) {
                        boss.takeDamage();
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }

            if (!boss.isDefeated()) {
                int bossDamage = (int) (Math.random() * 4) + 1;
                player.decreaseHealth(bossDamage);
                System.out.println("The Glitchtrap Boss dealt " + bossDamage + " damage to you!");

                System.out.println("Your health: " + player.getHealth());
                System.out.println("Boss health: " + boss.getHealth());

                if (player.getHealth() <= 0) {
                    System.out.println("Game over. You were defeated by the Glitchtrap Boss...your soul is his to torment.");
                    System.exit(0);
                }
            }
        }

        System.out.println("Congratulations! You defeated the Glitchtrap Boss! He'll get you next time....");
    }

    private void handleBlockers() {
        System.out.println();
        System.out.println("You encounter Glitchtrap Blockers!");
        System.out.println("They will attack if you get too close and chase you until you leave the room.");
        System.out.println();

        while (rooms[3].isLit) {
            System.out.println("The room is lit. No enemies are present. Proceed safely.");
        }

        while (true) {
            System.out.println("Do you want to move closer to the blockers? (yes/no)");
            String moveCloser = scanner.nextLine();

            if (moveCloser.equals("yes")) {
                player.decreaseHealth();
                System.out.println();
                System.out.println("You moved closer to the blockers. They attacked, and you lost one heart.");
                System.out.println("Current health: " + player.getHealth());
                System.out.println();

                if (player.getHealth() <= 0) {
                    System.out.println("Game over. You ran out of health.");
                    System.exit(0);
                }
            } else if (moveCloser.equals("no")) {
                System.out.println();
                System.out.println("You decided not to approach the blockers. Good choice!");
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }
}