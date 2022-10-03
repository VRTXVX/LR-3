package com.droidbattle.battle;

import com.droidbattle.droid.BaseDroid;

import java.util.List;

import static com.droidbattle.recreatebattle.SaveBattleToFile.createFile;

public class Battle {
    private static Arena currentArena = null;
    public Battle(List<BaseDroid>team1, List<BaseDroid>team2) {
        // if user choose function for save battle we create new file
        createFile();
        BattleUtils.doFight(team1, team2);
        BattleUtils.printStatistics(team1, team2);
    }

    public static void setCurrentArena(Arena currentArena) { Battle.currentArena = currentArena; }
    public static Arena getCurrentArena() { return currentArena; }
}