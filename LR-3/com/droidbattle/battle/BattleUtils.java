package com.droidbattle.battle;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.droidbattle.droid.BaseDroid;
import static com.droidbattle.color.Color.*;
import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;
import static com.droidbattle.recreatebattle.SaveBattleToFile.*;
import static com.droidbattle.color.Color.getColoredString;

public class BattleUtils {
    public static void doFight(List<BaseDroid> team1, List<BaseDroid> team2) {
        BaseDroid attacker,defender;
        // string with which we will write information to the file
        String string;

        List<BaseDroid> copyOfTeam1 = new ArrayList<>(team1);
        List<BaseDroid> copyOfTeam2 = new ArrayList<>(team2);

        int indexTeam1,indexTeam2;

        //random first round team choosing
        int i = new Random().nextInt(0, 1), round = 1;

        for(; !copyOfTeam1.isEmpty() && !copyOfTeam2.isEmpty(); i++, round++) {
            clearConsole();
            string = printRoundImage(round);
            System.out.println(string);

            writeRoundIntoFile(string);

            // random choose droid from teams
            indexTeam1 = new Random().nextInt(copyOfTeam1.size());
            indexTeam2 = new Random().nextInt(copyOfTeam2.size());

            // choose attacker and defender step by step
            if (i % 2 != 0) {
                attacker = copyOfTeam1.get(indexTeam1);
                defender = copyOfTeam2.get(indexTeam2);
            } else {
                attacker = copyOfTeam2.get(indexTeam2);
                defender = copyOfTeam1.get(indexTeam1);
            }

            int damage = attacker.attack(defender);

            // call method for print pseudo-graphic battle
            DrawBattle.drawTeamBattle(indexTeam1, indexTeam2, copyOfTeam1, copyOfTeam2, attacker == copyOfTeam1.get(indexTeam1), damage);

            defender.setTotalReceivedDamage(defender.getTotalReceivedDamage() + damage);
            attacker.setTotalAttacks(attacker.getTotalAttacks() + 1);
            attacker.setTotalDamage(attacker.getTotalDamage() + damage);

            // if defender is dead, remove him from team
            if (!defender.isAlive()) {
                attacker.setTotalKills(attacker.getTotalKills() + 1);
                if (i % 2 != 0) {
                    copyOfTeam2.remove(indexTeam2);
                } else {
                    copyOfTeam1.remove(indexTeam1);
                }
            }

        }

        clearConsole();


        //  after the battle is over print winner
        string = String.format("\n\n\n" +
                        "%45s%s████████╗███████╗░█████╗░███╗░░░███╗░░░░░%s░░░░░░██╗░░░░░░░██╗░█████╗░███╗░░██╗██╗\n" +
                        "%45s╚══██╔══╝██╔════╝██╔══██╗████╗░████║░░░░░%s░░░░░░██║░░██╗░░██║██╔══██╗████╗░██║██║\n" +
                        "%45s░░░██║░░░█████╗░░███████║██╔████╔██║░░░░░%s░░░░░░╚██╗████╗██╔╝██║░░██║██╔██╗██║██║\n" +
                        "%45s░░░██║░░░██╔══╝░░██╔══██║██║╚██╔╝██║░░░░░%s░░░░░░░████╔═████║░██║░░██║██║╚████║╚═╝\n" +
                        "%45s░░░██║░░░███████╗██║░░██║██║░╚═╝░██║░░░░░███████╗░░░░░░░╚██╔╝░╚██╔╝░╚█████╔╝██║░╚███║██╗\n" +
                        "%45s░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝░░░░░╚═╝░░░░░╚══════╝░░░░░░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚══╝╚═╝%s\n",
                " ", copyOfTeam1.isEmpty() ? BLUE : RED, copyOfTeam1.isEmpty() ? "██████╗░" : "░░███╗░░", " ", copyOfTeam1.isEmpty() ? "╚════██╗" : "░████║░░", " ",
                copyOfTeam1.isEmpty() ? "░░███╔═╝" : "██╔██║░░", " ", copyOfTeam1.isEmpty() ? "██╔══╝░░" : "╚═╝██║░░", " ", " ", RESET_COLOUR);

        string += String.format(BLACK + "\n%45sPress enter to continue..." + RESET_COLOUR, " ");
        System.out.print(string);
        writeRoundIntoFile(string);
        in.nextLine();
        clearConsole();
    }

    private static String printRoundImage(int round){
        String[] roundImage = {
                "██████╗░░█████╗░██╗░░░██╗███╗░░██╗██████╗░  ",
                "██╔══██╗██╔══██╗██║░░░██║████╗░██║██╔══██╗  ",
                "██████╔╝██║░░██║██║░░░██║██╔██╗██║██║░░██║  ",
                "██╔══██╗██║░░██║██║░░░██║██║╚████║██║░░██║  ",
                "██║░░██║╚█████╔╝╚██████╔╝██║░╚███║██████╔╝  ",
                "╚═╝░░╚═╝░╚════╝░░╚═════╝░╚═╝░░╚══╝╚═════╝░  "};
        String[] numbersImage = {
                "░█████╗░", "░░███╗░░", "██████╗░", "██████╗░", "░░██╗██╗", "███████╗", "░█████╗░", "███████╗", "░█████╗░", "░█████╗░",
                "██╔══██╗", "░████║░░", "╚════██╗", "╚════██╗", "░██╔╝██║", "██╔════╝", "██╔═══╝░", "╚════██║", "██╔══██╗", "██╔══██╗",
                "██║░░██║", "██╔██║░░", "░░███╔═╝", "░█████╔╝", "██╔╝░██║", "██████╗░", "██████╗░", "░░░░██╔╝", "╚█████╔╝", "╚██████║",
                "██║░░██║", "╚═╝██║░░", "██╔══╝░░", "░╚═══██╗", "███████║", "╚════██╗", "██╔══██╗", "░░░██╔╝░", "██╔══██╗", "░╚═══██║",
                "╚█████╔╝", "███████╗", "███████╗", "██████╔╝", "╚════██║", "██████╔╝", "╚█████╔╝", "░░██╔╝░░", "╚█████╔╝", "░█████╔╝",
                "░╚════╝░", "╚══════╝", "╚══════╝", "╚═════╝░", "░░░░░╚═╝", "╚═════╝░", "░╚════╝░", "░░╚═╝░░░", "░╚════╝░", "░╚════╝░"};

        String result = "\n" + GREEN, tabs = "", roundString = String.valueOf(round);
        for (int i = 0; i < 8 - roundString.length(); i++)
            tabs += "\t";

        for (int i = 0; i < 6; i++) {
            result += tabs + roundImage[i];
            for (int j = 0; j < roundString.length(); j++) {
                result += numbersImage[Integer.parseInt(String.valueOf(roundString.charAt(j))) + i * 10];
            }
            result += '\n';
        }
        result += RESET_COLOUR;
        return result;
    }

    public static void printStatistics(List<BaseDroid> team1, List<BaseDroid> team2) {

        String string = String.format("""


                %45s%s░██████╗████████╗░█████╗░████████╗██╗░██████╗████████╗██╗░█████╗░░██████╗
                %45s██╔════╝╚══██╔══╝██╔══██╗╚══██╔══╝██║██╔════╝╚══██╔══╝██║██╔══██╗██╔════╝
                %45s╚█████╗░░░░██║░░░███████║░░░██║░░░██║╚█████╗░░░░██║░░░██║██║░░╚═╝╚█████╗░
                %45s░╚═══██╗░░░██║░░░██╔══██║░░░██║░░░██║░╚═══██╗░░░██║░░░██║██║░░██╗░╚═══██╗
                %45s██████╔╝░░░██║░░░██║░░██║░░░██║░░░██║██████╔╝░░░██║░░░██║╚█████╔╝██████╔╝
                %45s╚═════╝░░░░╚═╝░░░╚═╝░░╚═╝░░░╚═╝░░░╚═╝╚═════╝░░░░╚═╝░░░╚═╝░╚════╝░╚═════╝░%s
            """," ",YELLOW, " ", " ", " ", " ", " ", RESET_COLOUR);

        string += "\n\n";


        String[] logoTeam1 = {
                        "████████╗███████╗░█████╗░███╗░░░███╗   ░░███╗░░",
                        "╚══██╔══╝██╔════╝██╔══██╗████╗░████║   ░████║░░",
                        "░░░██║░░░█████╗░░███████║██╔████╔██║   ██╔██║░░",
                        "░░░██║░░░██╔══╝░░██╔══██║██║╚██╔╝██║   ╚═╝██║░░",
                        "░░░██║░░░███████╗██║░░██║██║░╚═╝░██║   ███████╗",
                        "░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝░░░░░╚═╝   ╚══════╝"};

        String[] logoTeam2 = {
                        "████████╗███████╗░█████╗░███╗░░░███╗    ██████╗░",
                        "╚══██╔══╝██╔════╝██╔══██╗████╗░████║    ╚════██╗",
                        "░░░██║░░░█████╗░░███████║██╔████╔██║    ░░███╔═╝",
                        "░░░██║░░░██╔══╝░░██╔══██║██║╚██╔╝██║    ██╔══╝░░",
                        "░░░██║░░░███████╗██║░░██║██║░╚═╝░██║    ███████╗",
                        "░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝░░░░░╚═╝    ╚══════╝"};

        String colorTeam1 = teamIsWin(team1) ? GREEN : RED;
        String colorTeam2 = teamIsWin(team2) ? GREEN : RED;

        for(int i = 0;i < logoTeam1.length;i++){
            string += String.format("%75s  %95s\n",getColoredString(logoTeam1[i],colorTeam1),getColoredString(logoTeam2[i],colorTeam2));
        }

        List<String> tableTeam1 = getStatisticsTable(team1);
        List<String> tableTeam2 = getStatisticsTable(team2);

        for(int i = 0;i < tableTeam1.size();i++) {
            string += String.format("%s\t%s\n",tableTeam1.get(i),tableTeam2.get(i));
        }

        string += BLACK + "Press enter to continue..." + RESET_COLOUR;
        System.out.print(string);
        in.nextLine();
        clearConsole();

        writeRoundIntoFile(string);
    }

    private static List<String> getStatisticsTable(List<BaseDroid> team) {

        List<BaseDroid> sortedTeam = team.stream().sorted((p1, p2) -> p2.getInfluence(team) - p1.getInfluence(team)).collect(Collectors.toList());

        List<String> table = new ArrayList<>();
        String line;
        String color = teamIsWin(sortedTeam) ? GREEN : RED;
        String symbol = getColoredString("║",color);

        line = "╔═════════════╦═════════╦══════════╦═══════════╦════════════╦═══════════╦═══════════╗";
        table.add(getColoredString(line,color));

        line = String.format("%s    Droid    %s  Kills  %s  Damage  %s  Attacks  %s SuperPower %s   Score   %s Influence %s",
                symbol,symbol,symbol,symbol,symbol,symbol,symbol,symbol);
        table.add(getColoredString(line,color));

        line = "╠═════════════╬═════════╬══════════╬═══════════╬════════════╬═══════════╬═══════════╣";
        table.add(getColoredString(line,color));


        for(BaseDroid droid : sortedTeam){
            line = symbol;
            for (int i = 0; i < 13 - droid.getName().length(); i++) {
                line += " ";
            }
            line += String.format("%s%s%9d%s%10d%s%11d%s%12d%s%11d%s%10d%%%s",getColoredString(droid.getName(),droid.isAlive() ? GREEN : RED),
                    symbol,droid.getTotalKills(),symbol,droid.getTotalDamage(),symbol,droid.getTotalAttacks(),symbol,droid.getTotalSuperPower(),symbol,droid.getScore(),symbol,droid.getInfluence(sortedTeam),symbol);
            table.add(line);
        }
        line = "╚═════════════╩═════════╩══════════╩═══════════╩════════════╩═══════════╩═══════════╝";
        table.add(getColoredString(line,color));

        return table;
    }


    public static boolean teamIsWin(List<BaseDroid> team){
        for (BaseDroid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
