package com.droidbattle.battle;

import com.droidbattle.droid.BaseDroid;

import java.util.List;

import static com.droidbattle.color.Color.*;
import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;
import static com.droidbattle.recreatebattle.SaveBattleToFile.writeRoundIntoFile;

public class DrawBattle {
    private static boolean automaticRounds = false;
    private static int intervalBetweenRounds = 1;

    public static void drawTeamBattle(int indexTeam1, int indexTeam2, List<BaseDroid> team1, List<BaseDroid> team2, boolean isFirstAttacking , int damage) {
        // print health of droids and other info about round
        DrawBattleUtils.printHealth(team1.get(indexTeam1).getHealth(), team2.get(indexTeam2).getHealth(), isFirstAttacking, damage);
        // print droids and list of droids in team
        DrawBattleUtils.printDroidsAndLists(indexTeam1, indexTeam2, team1, team2, isFirstAttacking);

        String string;
        // print info about superpower usage
        if(team1.get(indexTeam1).getInfo() != null) {
            string = String.format("\n%57s%s\n", "", team1.get(indexTeam1).getInfo());
            System.out.print(string);
            writeRoundIntoFile(string);
        }

        if(team2.get(indexTeam2).getInfo() != null){
            string = String.format("\n%57s%s\n", "", team2.get(indexTeam2).getInfo());
            System.out.print(string);
            writeRoundIntoFile(string);
        }

        // if automatic rounds is on, wait for interval
        if (automaticRounds) {
            try {
                writeRoundIntoFile("Wait " + String.valueOf(intervalBetweenRounds * 1000) + "\n");
                Thread.sleep(intervalBetweenRounds * 1000L);
            } catch (Exception e) {
                System.out.println("Error occurred!");
            }
        }
        // if automatic rounds is off, wait for user input
        else {
            string = BLACK + "Press enter to continue..." + RESET_COLOUR;
            System.out.print(string);
            writeRoundIntoFile(string + '\n');
            in.nextLine();
        }

        // clear console
        clearConsole();
    }


    public static int getIntervalBetweenRounds() { return intervalBetweenRounds; }
    public static void setIntervalBetweenRounds(int intervalBetweenRounds) { DrawBattle.intervalBetweenRounds = intervalBetweenRounds; }
    public static boolean getAutomaticRounds(){ return automaticRounds;}
    public static void changeAutomaticRounds(){ automaticRounds = !automaticRounds; }
}
