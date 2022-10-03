package com.droidbattle.menu;

import static com.droidbattle.menu.MenuImage.*;
import static com.droidbattle.menu.BattleMenu.battleMenu;
import static com.droidbattle.menu.MenuImage.printMenuExit;
import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;
import static com.droidbattle.menu.SettingsMenu.settingsMenu;

public class MainMenu {

    public static void mainMenu(){
        int choice, key;
        String string;
        do {
            clearConsole();
            printMainMenu();

            try {
                choice = in.nextInt();
                if (choice == 1) {
                    battleMenu();
                }
                else if (choice == 2) {
                    settingsMenu();
                }
                else if (choice == 0) {
                    clearConsole();
                    printMenuExit();
                    key = in.nextInt();
                    if (key == 1) {
                        clearConsole();
                        System.exit(0);
                    }
                }
            } catch (Exception e) {
                in.nextLine(); // clear buffer
                System.out.println("Incorrect value");
            }
        }while (true);
    }
}
