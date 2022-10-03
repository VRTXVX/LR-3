package com.droidbattle.droid;

import java.util.List;
import java.util.Random;

public abstract class BaseDroid {
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected String name;
    protected int chance;

    protected String info;

    protected int totalKills;
    protected int totalReceivedDamage;
    protected int totalDamage;
    protected int totalAttacks;
    protected int totalSuperPower;

    public int attack(BaseDroid defender) {
        this.setInfo(null);
        defender.setInfo(null);

        Random random = new Random();
        int curChance = random.nextInt(99);

        // if chance is less than 10, droid has a super attack
        if (curChance < this.getChance()) {
            this.setTotalSuperPower(getTotalSuperPower() + 1);
            return superPower(defender, chance);
        }

        // if chance super attack is not successful, droid attacks normally
        // generate random number this will be a deviation from normal damage
        int curDamage = new Random().nextInt(-25, 25);

        // calculate damage with deviation
        curDamage = getDamage() + (getDamage() * curDamage) / 100;

        defender.getHit(curDamage, chance, this);

        return curDamage;
    }

    // this is a functions which we will overload
    // because each droid will have different parameters
    public int superPower(BaseDroid defender, int chance){ return 0; }
    public String[] getImage(){ return null; }

    public void setTotalDamage(int totalDamage) { this.totalDamage = totalDamage;}
    public void setTotalKills(int totalKills) { this.totalKills = totalKills;}
    public void setTotalAttacks(int totalAttacks) { this.totalAttacks = totalAttacks;}
    public void setTotalReceivedDamage(int totalReceivedDamage) { this.totalReceivedDamage = totalReceivedDamage;}
    public void setTotalSuperPower(int totalSuperPower) { this.totalSuperPower = totalSuperPower;}

    public int getHit(int damage, int chance, BaseDroid attacker) {
        int curHealth = getHealth() - damage;
        if (curHealth < 0) {
            curHealth = 0;
        }
        setHealth(curHealth);
        return curHealth;
    }

    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setName(String name) { this.name = name; }
    public void setChance(int chance){ this.chance = chance; }
    public void setInfo(String info) { this.info = info; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }

    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public String getName() { return name; }
    public boolean isAlive() { return health > 0; }
    public int getChance(){ return chance; }
    public String getInfo() { return info; }

    public int getTotalDamage(){ return this.totalDamage; }
    public int getTotalKills(){ return this.totalKills; }
    public int getTotalAttacks(){ return this.totalAttacks; }
    public int getTotalReceivedDamage(){ return this.totalReceivedDamage; }
    public int getTotalSuperPower(){ return this.totalSuperPower; }
    public int getMaxHealth(){ return this.maxHealth; }



    public int getScore() {
        return getTotalKills() * 50 + getTotalReceivedDamage() + getTotalDamage() * 2;
    }


    public int getInfluence(List<BaseDroid> team){
        int totalTeamDamage = 0;
        // calculate total max health of all droids in team
        for(BaseDroid droid : team)
            totalTeamDamage += droid.getTotalDamage();

        // calculate influence of current droid
        return (int)Math.round((getTotalDamage() / (double)totalTeamDamage) * 100);
    }

    public boolean isBlockShot() { return false;}
    public void setBlockShot(boolean blockShot) {  }
}