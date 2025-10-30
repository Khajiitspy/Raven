package Graveyard;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of questions - ");
        int n = Integer.parseInt(scanner.nextLine());

        int cor = 0;
        for (int i = 0; i < n; i++) {
            int num1 = GetRandom(1, 10);
            int num2 = GetRandom(1, 10);
            System.out.print(num1 + " x " + num2 + " = ");
            int ans = Integer.parseInt(scanner.nextLine());
            if (ans == num1*num2){
                cor++;
            }
        }

        System.out.println("You got " + cor + "/" + n + " correct! (%" + ((double)cor/n*100.0d) + ")");
    }

    public static int GetRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
