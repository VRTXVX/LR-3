package com.droidbattle.droid;


import java.util.Random;

import static com.droidbattle.color.Color.*;

public class Attacker extends BaseDroid {
    
    public Attacker(String name){
        this.name = name;
        maxHealth = health = 100;
        damage = 18;
        chance = 10;
    }

    @Override
    public int superPower(BaseDroid defender, int chance){
        int fluff = ((new Random().nextInt(-25, 25)) * this.getDamage()) / 100;
        int curDamage = this.getDamage() + fluff + 10;
        defender.getHit(curDamage, chance, this);
        setInfo(RED + "SUPERPOWER!!! " + RESET_COLOUR + GREEN + this.name +  RESET_COLOUR + " hits 10 more points of health!");
        return curDamage;
    }

    @Override
    public String[] getImage() {
        return new String[]{
                " ",
                " ",
                " ",
                "       __            ",
                "   _  |@@|           ",
                "  / \\ \\--/ __        ",
                "  ) O|----|  |   __  ",
                " / / \\ }{ /\\ )_ / _\\ ",
                " )/  /\\__/\\ \\__O (__ ",
                "|/  (--/\\--)    \\__/ ",
                "/   _)(  )(_         ",
                "   `---''---`        "};
    }
}

