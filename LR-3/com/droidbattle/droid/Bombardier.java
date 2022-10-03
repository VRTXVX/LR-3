package com.droidbattle.droid;


import java.util.Random;

import static com.droidbattle.color.Color.*;

public class Bombardier extends BaseDroid {
    public Bombardier(String name) {
        this.name = name;
        maxHealth = health = 90;
        damage = 15;
        chance = 20;
    }

    @Override
    public int superPower(BaseDroid defender, int chance) {
        int fluff = ((new Random().nextInt(-25, 25)) * this.getDamage()) / 100;
        int curDamage = (getDamage() + fluff) * 2;
        defender.getHit(curDamage, chance, this);
        setInfo(RED + "SUPERPOWER!!! " + RESET_COLOUR + GREEN + this.name + RESET_COLOUR + " hits " + RED + defender.getName() + RESET_COLOUR + " twice as strong!");
        return curDamage;
    }

    @Override
    public String[] getImage() {
        return new String[]{
                "   __,_,             ",
                "  [_|_/              ",
                "   //                ",
                " _//    __           ",
                "(_|)   |@@|          ",
                " \\ \\__ \\--/ __       ",
                "  \\o__|----|  |   __ ",
                "      \\ }{ /\\ )_ / _\\",
                "      /\\__/\\ \\__O (__",
                "     (--/\\--)    \\__/",
                "     _)(  )(_        ",
                "    `---''---`       "};
    }
}
