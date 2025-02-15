import java.util.Random;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);

    /**
     * @brief constructon
     */
    public Menu() {

    }

    /**
     * @brief game menu
     * 
     */
    public void gameMenu() {
        System.out.println("HIDE N CODE\nType number:");
        System.out.println("1\tStart game\n2\tHow to play\n3\tExit");
        int ch = choice();
        switch (ch) {
            case 1:
                preGame();
                break;
            case 2:
                rules();
                break;
            case 3:
                break;
            default:
                gameMenu();
                break;
        }
    }

    /**
     * @brief pre-game function
     *
     */
    public void preGame() {
        System.out.println("How big code would you like to guess?");
        int size = choice();
        Number[] hash = getHash(size);
        playGame(hash, size);
    }

    /**
     * @brief one game round (exactly full game logic)
     *
     * @param hash hash code combination
     * @param size size of code and hash
     */
    public void playGame(Number[] hash, int size) {
        int attempts = 1;
        boolean win = false;
        do {
            System.out.print("ATTEMPT: " + attempts + "\nHash:");
            for (int i = 0; i < size; i++) {
                if (hash[i].getKnown()) {
                    System.out.print(" " + hash[i].getValue());
                } else {
                    System.out.print(" #");
                }
            }
            System.out.println();
            System.out.print("Write code: ");
            int[] code = new int[size];
            for (int i = 0; i < size; i++) {
                int number = sc.nextInt();
                code[i] = number;
            }
            System.out.println();
            System.out.print("Your try: ");
            for (int i = 0; i < size; i++) {
                switch (contains(code, hash, i, size)) {
                    case 0:
                        System.out.print(" C");
                        break;
                    case 1:
                        System.out.print(" O");
                        break;
                    case 2:
                        System.out.print(" F");
                        break;
                }
            }
            System.out.println("\n");
            attempts++;
            win = check(hash, size);
        } while (!win);
        System.out.println("YOU WIN!!!\nNumber of attempts: " + attempts + "\n");
        System.out.println("1\tPlay again\n0\tMenu");
        if (choice() == 1) {
            preGame();
        } else {
            gameMenu();
        }
    }

    /**
     * @brief method checks attempt
     *
     * @param code  user attempt
     * @param hash  hash code
     * @param index actual position of number
     * @param size  size of hash and code
     * @return int 0 if numbers on same position are same, 1 if number is into hash
     *         but on other position, 2 if number is not into hash
     */
    public int contains(int[] code, Number[] hash, int index, int size) {
        if (code[index] == hash[index].getValue()) {
            hash[index].setKnown(true);
            return 0;
        }
        for (int i = 0; i < size; i++) {
            if (code[index] == hash[i].getValue()) {
                return 1;
            }
        }
        return 2;
    }

    /**
     * @brief checking if player wins
     *
     * @param hash hash combination
     * @param size size of hash
     * @return true when player wins
     * @return false when user hasn't won yet
     */
    public boolean check(Number[] hash, int size) {
        for (int i = 0; i < size; i++) {
            if (!hash[i].getKnown()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @brief Get the Hash combination
     *
     * @param size size of hash
     * @return hash code combination
     */
    public Number[] getHash(int size) {
        Number[] hsh = new Number[size];
        for (int i = 0; i < size; i++) {
            Random r = new Random();
            int rand = r.nextInt(10);
            hsh[i] = new Number(rand, false);
        }
        return hsh;
    }

    /**
     * @brief shows rules
     *
     */
    public void rules() {
        System.out.println(
                "HOW TO PLAY\nComputer choose some combination of numnbers. Computer let you guess the right combination.");
        System.out.println(
                "Computer shows you number of #, it means one # one number between 0-9.\n\nEvery one code should be unique. Every number can be specific or every number should be same.\n");
        System.out.println(
                "Computer is counting your attempts. Each every attempt you have to write combination.\nFor example:\nComputer writes ###, which means you must try some combination of three numbers.");
        System.out.println(
                "So something like this: 1 or 9 8 or 1 2 3 6 is invalid input and computer say it to you. Only combinations like 1 2 3 or 5 0 6 are correct input.\n");
        System.out.println(
                "C\tcorrect hint, this number is on this position\nO\tcode contains this number but in defferent position\nF\tthis number in not in code combination.\n");
        gameMenu();
    }

    /**
     * @brief returns number of players choice in any menu
     *
     * @return int players choice
     */
    public int choice() {
        System.out.print("Type choice: ");
        int choice = sc.nextInt();
        System.out.println();
        return choice;
    }
}
