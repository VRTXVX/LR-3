package com.droidbattle.menu;

import com.droidbattle.battle.Battle;
import com.droidbattle.droid.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.droidbattle.color.Color.*;
import static com.droidbattle.menu.MenuImage.*;


public class MenuUtils {
    public static final Scanner in = new Scanner(System.in);
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void createBattle(boolean duel) {
        List<BaseDroid> team1 = new ArrayList<>();
        List<BaseDroid> team2 = new ArrayList<>();

        int kst;

        if (!duel) {
            while (true) {
                clearConsole();
                printTextTeams();
                System.out.printf("\n\n%55sNumber of droids in each command >>> ", "");
                try {
                    kst = in.nextInt() * 2;
                    if (kst < 1)
                        System.out.println("Incorrect value");
                    else
                        break;
                } catch (Exception e) {
                    in.nextLine(); // clear buffer
                    System.out.println("Incorrect value");
                }
            }
        }
        else
            kst = 2;

        int droidIndex;
        String name;
        List<BaseDroid> tmp;
        ArrayList<String> team1List, team2List;
        for(int i = 0;i < kst;i++) {
            droidIndex = chooseDroid(kst, i);

            if (i < kst / 2){
                tmp = team1;
            }else{
                tmp = team2;
            }

            if (droidIndex == 0){
                return;
            }
            in.nextLine();
            System.out.printf("\n%70sName of droid:\n%74s>>> ", "", "");
            name = in.nextLine();

            if (droidIndex == 1){
                tmp.add(new Attacker(name));
            }else if (droidIndex == 2){
                tmp.add(new Bombardier(name));
            }else if (droidIndex == 3){
                tmp.add(new Defender(name));
            }else if (droidIndex == 4){
                tmp.add(new RocketLauncher(name));
            }else if (droidIndex == 5){
                tmp.add(new Sniper(name));
            }

        }
        clearConsole();
        Battle battle = new Battle(team1,team2);
    }

    public static int chooseDroid(int kst, int i){
        int choice,n = 1;
        while(true){
            try {
                clearConsole();

                System.out.printf("\n%65s" + BLUE + ">>>" + RESET_COLOUR + CYAN + " Droid %d/%d; Command %d " + RESET_COLOUR
                        + BLUE + "<<<" + RESET_COLOUR + "\n\n", "", (i + 1 > kst / 2 ? i - kst / 2 + 1 : i + 1), kst / 2, (i + 1 > kst / 2 ? 2 : 1));

                if (n == 0) {
                    break;
                } else if (n >= 1 && n <= 5) {
                    System.out.print(InfoDroids.droidsInfo[n - 1]);
                }

                if (n != 5) {
                    printSwitchMenu(n == 1);
                } else {
                    printSwitchToBack();
                }

                choice = in.nextInt();

                if (choice == 0) {
                    n--;
                } else if (choice == 1) {
                    return n;
                } else if (choice == 2 && n != 5) {
                    n++;
                }
            }
            catch (Exception e) {
                in.nextLine(); // clear buffer
                System.out.println("Incorrect value!");
            }
        }
        return 0;
    }

}
