package Controller;

import View.BasicView;

import java.util.Scanner;

/**
 * Created by Rasmus on 24-11-2016.
 */
public class InputController {

    private static Scanner scanner = new Scanner(System.in);

    //Rasmus
    public static String getString() {

        return scanner.nextLine();
    }

    //Rasmus
    public static int getInt() {

        while (true) {

            try {

                String userInput = scanner.nextLine();
                return Integer.parseInt(userInput);

            } catch (NumberFormatException e) {

                BasicView.wrongInputInt();

            }
        }
    }

    //Rasmus
    public static double getDouble() {

        while (true) {

            try {

                String userInput = scanner.nextLine();
                return Double.parseDouble(userInput);

            } catch (NumberFormatException e) {

                BasicView.wrongInputDouble();

            }
        }
    }

    public static boolean getBoolean() {

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("true")) {
                return true;
            }
            if (userInput.equals("false")) {
                return false;
            }

            BasicView.wrongInputBoolean();
        }
    }
}
