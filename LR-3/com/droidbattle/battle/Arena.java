package com.droidbattle.battle;


import com.droidbattle.droid.BaseDroid;

import static com.droidbattle.battle.Battle.getCurrentArena;

public enum Arena {
    STEPPES(1, 1, 1),
    ARCTIC(0.75, 1.25, 1),
    CITY(1, 0.7, 1.3),
    WOOD(0.95, 1.2, 0.8);

    final double healthCoefficient, damageCoefficient, chanceCoefficient;

    Arena(double healthCoefficient, double damageCoefficient, double chanceCoefficient) {
        this.healthCoefficient = healthCoefficient;
        this.damageCoefficient = damageCoefficient;
        this.chanceCoefficient = chanceCoefficient;
    }

    public static BaseDroid arenaSettings(BaseDroid droid){
        droid.setHealth((int) (droid.getHealth() * getCurrentArena().healthCoefficient));
        droid.setDamage((int) (droid.getDamage() * getCurrentArena().damageCoefficient));
        droid.setChance((int) (droid.getChance() * getCurrentArena().chanceCoefficient));
        return droid;
    }
}