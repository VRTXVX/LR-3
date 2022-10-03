package com.droidbattle.menu;

import com.droidbattle.battle.Arena;

import static com.droidbattle.battle.Battle.setCurrentArena;
import static com.droidbattle.menu.MenuImage.*;
import static com.droidbattle.menu.MenuUtils.*;

public class ArenaMenu {
    public static boolean arenaMenu(){
        int choice, n = 1;
        do {
            clearConsole();

            if (n == 0) {
                break;
            }

            printBattleArena();
            if (n >= 1 && n <= 4) {
                System.out.print(arenas[n - 1]);
            }
            try {
                if (n != 4) {
                    printSwitchMenu(n == 1);
                } else {
                    printSwitchToBack();
                }

                choice = in.nextInt();

                if (choice == 0) {
                    n--;
                } else if (choice == 1) {
                    if (n == 1)
                        setCurrentArena(Arena.STEPPES);
                    else if (n == 2)
                        setCurrentArena(Arena.ARCTIC);
                    else if (n == 3)
                        setCurrentArena(Arena.CITY);
                    else
                        setCurrentArena(Arena.WOOD);
                    clearConsole();
                    return true;
                } else if (choice == 2 && n != 4) {
                    n++;
                }

            } catch (Exception e) {
                System.out.println("Incorrect value");
                System.exit(1);
            }
        } while (true);
        clearConsole();
        return false;
    }
}
