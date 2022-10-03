package com.droidbattle.menu;

import com.droidbattle.droid.*;

import static com.droidbattle.battle.Arena.arenaSettings;
import static com.droidbattle.color.Color.*;


public class InfoDroids {
    private static BaseDroid[] droids = {new Attacker(""), new Bombardier(""), new Defender(""), new RocketLauncher(""), new Sniper("")};
    public static String[] droidsInfo = {
            String.format("""     
                                                                                \u001B[31m▄▀█ ▀█▀ ▀█▀ ▄▀█ █▀▀ █▄▀ █▀▀ █▀█
                                                                                █▀█ ░█░ ░█░ █▀█ █▄▄ █░█ ██▄ █▀▄\u001B[0m
                     
                     
                                                                                                                          Characteristics:
                                                                                                                       Health: \u001B[32m%s\u001B[0m
                                                                                              __                       Damage: \u001B[31m%s\u001B[0m
                                                                                          _  |@@|                      Superpower: %s%d%s%% chance of hitting 10 more points
                                                                                         / \\ \\--/ __                   
                                                                                         ) O|----|  |   __  
                                                                                       / / \\ }{ /\\ )_ / _\\    
                                                                                       )/  /\\__/\\ \\__O (__    
                                                                                      |/  (--/\\--)    \\__/   
                                                                                     /   _)(  )(_                   
                                                                                         `---''---`      
                """, arenaSettings(droids[0]).getHealth(), droids[0].getDamage(), CYAN, droids[0].getChance(), RESET_COLOUR),
            String.format("""     
                                                                           \u001B[36m█▄▄ █▀█ █▀▄▀█ █▄▄ ▄▀█ █▀█ █▀▄ █ █▀▀ █▀█
                                                                           █▄█ █▄█ █░▀░█ █▄█ █▀█ █▀▄ █▄▀ █ ██▄ █▀▄\u001B[0m
                                                                                
                                                                                        __,_,                             
                                                                                       [_|_/                              Characteristics:
                                                                                        //                             Health: \u001B[32m%s\u001B[0m
                                                                                      _//    __                        Damage: \u001B[31m%s\u001B[0m
                                                                                     (_|)   |@@|                       Superpower: %s%d%s%% chance of hitting twice as strong
                                                                                      \\ \\__ \\--/ __       
                                                                                       \\o__|----|  |   __
                                                                                           \\ }{ /\\ )_ / _\\
                                                                                           /\\__/\\ \\__O (__
                                                                                          (--/\\--)    \\__/
                                                                                          _)(  )(_       
                                                                                        `---''---`  
                """, arenaSettings(droids[1]).getHealth(), droids[1].getDamage(), CYAN, droids[1].getChance(), RESET_COLOUR),
            String.format("""     
                                                                              \u001B[32m█▀▄ █▀▀ █▀▀ █▀▀ █▄░█ █▀▄ █▀▀ █▀█
                                                                              █▄▀ ██▄ █▀░ ██▄ █░▀█ █▄▀ ██▄ █▀▄\u001B[0m
                                                                                   
                                                                                  
                                                                                                                          Characteristics:
                                                                                                                       Health: \u001B[32m%s\u001B[0m
                                                                                           \\_\\                         Damage: \u001B[31m%s\u001B[0m
                                                                                          (_**)                        Superpower: %s%d%s%% chance of blocking all damage
                                                                                         __) #_
                                                                                        ( )...()
                                                                                        || | |I|
                                                                                        || | |()__/
                                                                                        /\\(___)
                                                                                       _-\"\"\"\"\"\"\"-_\"\"-_
                                                                                       -,,,,,,,,- ,,-
                """, arenaSettings(droids[2]).getHealth(), droids[2].getDamage(), CYAN, droids[2].getChance(), RESET_COLOUR),
            String.format("""     
                                                                   \u001B[33m█▀█ █▀█ █▀▀ █▄▀ █▀▀ ▀█▀   █░░ ▄▀█ █░█ █▄░█ █▀▀ █░█ █▀▀ █▀█
                                                                   █▀▄ █▄█ █▄▄ █░█ ██▄ ░█░   █▄▄ █▀█ █▄█ █░▀█ █▄▄ █▀█ ██▄ █▀▄\u001B[0m
                                                                              
                                                                              
                                                                                                                          Characteristics:
                                                                                                                       Health: \u001B[32m%s\u001B[0m
                                                                                            [ ]                        Damage: \u001B[31m%s\u001B[0m
                                                                                           (   )                       Superpower: %s%d%s%% chance of regeneration
                                                                                            |>|
                                                                                         __/===\\__
                                                                                        //| o=o |\\\\
                                                                                      <]  | o=o |  [>
                                                                                          \\=====/
                                                                                         / / | \\ \\
                                                                                        <_________>
                """, arenaSettings(droids[3]).getHealth(), droids[3].getDamage(), CYAN, droids[3].getChance(), RESET_COLOUR),
            String.format("""     
                                                                                    \u001B[35m█▀ █▄░█ █ █▀█ █▀▀ █▀█
                                                                                    ▄█ █░▀█ █ █▀▀ ██▄ █▀▄\u001B[0m
                                                                                 
                                                                                 
                                                                                                                          Characteristics:
                                                                                                                       Health: \u001B[31m%s\u001B[0m
                                                                                           ,     ,                     Damage: \u001B[32m%s\u001B[0m
                                                                                          (\\\\____/)                Superpower: %s%d%s%% chance of instant kill
                                                                                           (_oo_)
                                                                                             (O)
                                                                                           __||__    \\\\)
                                                                                        []/______\\\\[] /
                                                                                        / \\\\______/ \\\\/
                                                                                       /    /__\\\\
                                                                                      (\\\\   /____\\\\
                """, arenaSettings(droids[4]).getHealth(), droids[4].getDamage(), CYAN, droids[4].getChance(), RESET_COLOUR)
    };
}
