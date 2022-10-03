package com.droidbattle.droid;

import java.util.Random;

import static com.droidbattle.color.Color.*;

public class RocketLauncher extends BaseDroid {
    public RocketLauncher(String name) {
        this.name = name;
        maxHealth = health = 70;
        damage = 25;
        chance = 10;
    }

    @Override
    public int superPower(BaseDroid defender, int chance) {
        int fluff = ((new Random().nextInt(-25, 25)) * this.getDamage()) / 100;

        defender.getHit(getDamage() + fluff, chance, this);

        if (this.getMaxHealth() == this.getHealth())
            return getDamage() + fluff;

        Random random = new Random();

        // generate a number that will be added to the droid health
        int curRegenerate = random.nextInt(1,20);
        // save the current health of the droid
        int prevHealth = getHealth();

        // change health
        this.setHealth(curRegenerate + getHealth());

        // if health is more than 70, set it to 70
        if (this.getHealth() > getMaxHealth()) {
            this.setHealth(getMaxHealth());
            //calculate the amount of health that was added
            curRegenerate = getMaxHealth() - prevHealth;
        }

        setInfo(RED + "SUPERPOWER!!! " + RESET_COLOUR + GREEN + this.getName() + RESET_COLOUR + " regenerated " + CYAN + curRegenerate + RESET_COLOUR + " points of health!");

        return getDamage() + fluff;
    }

    @Override
    public String[] getImage() {
        return new String[]{" ",
                " ",
                "       _             ",
                        "      [ ]            ",
                        "     (   )           ",
                        "      |>|            ",
                        "   __/===\\__         ",
                        "  //| o=o |\\\\        ",
                        "<]  | o=o |  [>      ",
                        "    \\=====/          ",
                        "   / / | \\ \\         ",
                        "  <_________>        "};
    }
}