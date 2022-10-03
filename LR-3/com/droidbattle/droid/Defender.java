package com.droidbattle.droid;

import static com.droidbattle.color.Color.*;

public class Defender extends BaseDroid {

    public Defender(String name){
        this.name = name;
        maxHealth = health = 100;
        damage = 10;
        chance = 25;
    }

    @Override
    public int getHit(int damage, int chance, BaseDroid attacker) {
        if(chance <= this.chance) {
            superPower(attacker, chance);
            return 0;
        }
        else
            return super.getHit(damage, chance, attacker);
    }

    @Override
    public int superPower(BaseDroid attacker, int chance){
        this.setInfo(RED + "SUPERPOWER!!! " + RESET_COLOUR + GREEN + this.name + RESET_COLOUR + " blocks " + RED + attacker.getName() + RESET_COLOUR + "`s hit!");
        return 0;
    }

    @Override
    public String[] getImage() {
        return new String[]{
                " ",
                        " ",
                        " ",
                                "    \\_\\              ",
                                "   (_**)             ",
                                "  __) #_             ",
                                " ( )...()            ",
                                " || | |I|            ",
                                " || | |()__/         ",
                                " /\\(___)             ",
                                "_-\"\"\"\"\"\"\"-_\"\"-_      ",
                                "-,,,,,,,,- ,,-       "};
    }
}
