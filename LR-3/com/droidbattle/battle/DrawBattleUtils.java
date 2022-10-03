package com.droidbattle.battle;

import com.droidbattle.droid.BaseDroid;

import java.util.ArrayList;
import java.util.List;

import static com.droidbattle.recreatebattle.SaveBattleToFile.writeRoundIntoFile;
import static com.droidbattle.color.Color.*;

public class DrawBattleUtils {

    public static void printDroidsAndLists(int indexTeam1, int indexTeam2, List<BaseDroid> team1, List<BaseDroid>team2, boolean isFirstAttacking) {
        BaseDroid droid1 = team1.get(indexTeam1);
        BaseDroid droid2 = team2.get(indexTeam2);

        String string = "";

        // get ascii art of droids
        String[] droid1Image = droid1.getImage();
        String[] droid2Image = droid2.getImage();

        // get list teams of droids
        ArrayList<String> team1Image = getTeamList(indexTeam1, isFirstAttacking, team1, true);
        ArrayList<String> team2Image = getTeamList(indexTeam2, !isFirstAttacking, team2, false);

        // print two droids and list teams
        for (int i = 0; i < droid1Image.length; i++) {
            string += String.format("%40s", "");
            string += printTeamImage(i, team1Image);
            string += String.format("%-23s     %26s", droid1Image[i], reverseString(droid2Image[i]));
            string += printTeamImage(i, team2Image);
            string += "\n";
        }
        System.out.print(string);
        writeRoundIntoFile(string);
    }

    // print team list
    private static String printTeamImage(int i, ArrayList<String> image){
        String result = "";

        if (i < image.size())
            result += image.get(i);
        else
            result = String.format("%15s", " ");

        return result;
    }

    // this is method reverse special symbols in order to turn the droid in the other direction
    private static StringBuilder reverseString(String str){
        StringBuilder reversed = new StringBuilder(str).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            if(reversed.charAt(i) == '\\')
                reversed.setCharAt(i, '/');
            else if(reversed.charAt(i) == '/')
                reversed.setCharAt(i, '\\');
            else if(reversed.charAt(i) == '}')
                reversed.setCharAt(i, '{');
            else if(reversed.charAt(i) == '{')
                reversed.setCharAt(i, '}');
            else if(reversed.charAt(i) == '[')
                reversed.setCharAt(i, ']');
            else if(reversed.charAt(i) == ']')
                reversed.setCharAt(i, '[');
            else if(reversed.charAt(i) == ')')
                reversed.setCharAt(i, '(');
            else if(reversed.charAt(i) == '(')
                reversed.setCharAt(i, ')');
        }
            return reversed;
    }

    // get list of droids in team
    public static ArrayList<String> getTeamList(int droidIndex, boolean isAttacker, List<BaseDroid> team, boolean isLeft){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(0,"╔═════════════╗");
        for (int i = 0; i < team.size(); i++)
            strings.add(i + 1, String.format("║" + (i == droidIndex ? (isAttacker ? GREEN : RED) : "")
                    + (isLeft ? "%-13s" : "%13s") + (i == droidIndex ? RESET_COLOUR : "") + "║", team.get(i).getName()));
        strings.add(strings.size(), "╚═════════════╝");
        return strings;
    }

    // print scale of health and damage which was inflicted on the defender
    public static void printHealth(int health1,int health2, boolean isFirstAttacking, int damage) {

        String string = String.format("%57s╔══════════╗ %22s ╔══════════╗\n", " ", " ");

        string += String.format("%57s║%10s║ ", "", getScaleHealth(health1,isFirstAttacking ? 0 : damage));
        string += health1 + (isFirstAttacking ? "": "(" + RED + (damage == 0 ? "" : "-") + damage + RESET_COLOUR + ")");

        int iter = 23 - (isFirstAttacking ? 0 : String.valueOf(damage).length() + (damage == 0 ? 2 : 3)) - String.valueOf(health1).length();
        for (int i = 0; i < iter; i++)
            string += " ";

        string += String.format("║%10s║ %d%s\n", getScaleHealth(health2, isFirstAttacking ? damage : 0), health2,
                isFirstAttacking ? "(" + RED + (damage == 0 ? "" : "-") + damage + RESET_COLOUR + ")": "");

        string += String.format("%57s╚══════════╝ %22s ╚══════════╝\n", " ", " ");
        System.out.print(string);
        writeRoundIntoFile(string);
    }

    // get a scale with a symbol that will show health
    private static String getScaleHealth(int health, int damage){
        String index;
        // depending on health, we give it a color
        if(health >= 66)
            index = GREEN;
        else if(health >= 33)
            index = YELLOW;
        else
            index = RED;

        if ((damage + health) / 10 == health / 10)
            damage = 0;
        else
            damage -= damage % 10;
        int i,j,n;

        for(i = 0;health > 0;health -= 10,i++)
            index += "/";
        index += RESET_COLOUR;

        index = index.concat(BLACK);
        for(j = 0; damage > 0; damage -= 10, j++)
            index = index.concat("/");

        index += RESET_COLOUR;

        n = 10 - i - j;

        for(i = 0;i < n;i++)
            index += " ";

        return index;
    }
}
