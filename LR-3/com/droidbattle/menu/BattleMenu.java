package com.droidbattle.menu;

import static com.droidbattle.menu.ArenaMenu.arenaMenu;
import static com.droidbattle.menu.MenuImage.*;
import static com.droidbattle.menu.MenuUtils.*;
import static com.droidbattle.menu.ReplayBattleMenu.replayBattleMenu;

public class BattleMenu {
    public static void battleMenu() {
        int choice;
        do {
            clearConsole();
            printBattleMenu();
            try {
                choice = in.nextInt();
                if (choice == 1) {
                    if(arenaMenu())
                        createBattle(true);
                }
                else if (choice == 2) {
                    if(arenaMenu())
                        createBattle(false);
                }
                else if (choice == 3) {
                    replayBattleMenu();
                }
                else if (choice == 0) {
                    break;
                }
                else {
                    System.out.println("Incorrect value");
                }

            } catch (Exception e) {
                in.nextLine(); // clear buffer
                System.out.println("Incorrect value");
            }
        } while (true);
        clearConsole();
    }
}
