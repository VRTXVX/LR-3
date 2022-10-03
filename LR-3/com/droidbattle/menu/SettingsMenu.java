package com.droidbattle.menu;

import static com.droidbattle.battle.DrawBattle.*;
import static com.droidbattle.menu.MenuImage.*;
import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;
import static com.droidbattle.recreatebattle.SaveBattleToFile.changeIsSaving;

public class SettingsMenu {
    public static void settingsMenu() {
        int choice, key;
        do {
            clearConsole();
            printSettingsMenu();
            try {
                choice = in.nextInt();
                if (choice == 1) {
                    clearConsole();
                    changeIsSaving();
                }
                else if (choice == 2){
                    changeAutomaticRounds();
                }
                else if (choice == 3 && getAutomaticRounds()) {
                    clearConsole();
                    printTextRoundDuration();
                    System.out.printf("%64sSet interval between rounds:\n\n%73s>>> ", "", "");
                    key = in.nextInt();
                    if (key < 1)
                        System.out.println("Incorrect value");
                    else
                        setIntervalBetweenRounds(key);
                }
                else if (choice == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Incorrect value");
                System.exit(1);
            }
        } while (true);

        clearConsole();
    }
}
