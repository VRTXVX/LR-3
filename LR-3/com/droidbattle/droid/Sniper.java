package com.droidbattle.droid;

import static com.droidbattle.color.Color.*;

public class Sniper extends BaseDroid {

    public Sniper(String name){
        this.name = name;
        maxHealth = health = 80;
        damage = 30;
        chance = 7;

    }

    @Override
    public int superPower(BaseDroid defender, int chance){
        int curDamage = defender.getHealth();
        defender.getHit(defender.getDamage(), chance, this);
        setInfo(CYAN + "SUPERPOWER!!! " + RESET_COLOUR + GREEN + this.getName() + RESET_COLOUR + " instantly kills " + RED + defender.getName() + RESET_COLOUR + "!");
        return curDamage;
    }

    @Override
    public String[] getImage() {
        return new String[]{
                " ",
                        " ",
                        " ",
                        "     ,     ,         ",
                                "    (\\____/)         ",
                                "     (_oo_)          ",
                                "       (O)           ",
                                "     __||__    \\)    ",
                                "  []/______\\[] /     ",
                                "  / \\______/ \\/      ",
                                " /    /__\\           ",
                                "(\\   /____\\          "};
    }
}
