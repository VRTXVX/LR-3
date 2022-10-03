 package com.droidbattle.menu;

 import static com.droidbattle.color.Color.*;
 import static com.droidbattle.menu.MenuImage.getTextWelcome;
 import static com.droidbattle.menu.MenuUtils.clearConsole;
 import static com.droidbattle.menu.MenuImage.*;

 public class WelcomeWindow {

    public static void welcomeWindow() {
        String[] strings = getTextWelcome();
        String image = "";
        for (int i = 0; i < strings.length / 6; i++) {
            clearConsole();
            System.out.println();

            for (int k = 0; k < 6; k++) {
                for (int j = 0; j < i + 1; j++) {
                    image += strings[j * 6 + k];
                }
                image += '\n';
            }
            System.out.print(PURPLE + image + RESET_COLOUR);
            try {
                Thread.sleep(111);
            }
            catch (InterruptedException e) {
                System.out.println("Oops! Something went wrong...");
                System.exit(1);
            }
            image = "";
        }
        printPressEnter();
        try {
            System.in.read();
        }
        catch (Exception e){
            System.out.println("Oops! Something went wrong...");
            System.out.print("ERROR: " + e);
            System.exit(1);
        }
    }
}