package com.droidbattle.recreatebattle;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;


public class RunBattleFromFile {
    public static void recreateBattle(String fullPathToFile){
        Path fileName = Path.of(fullPathToFile);
        String line;

        clearConsole();

        File file = new File(fullPathToFile);
        file.setWritable(true);

        try(Scanner scanner = new Scanner(fileName)) {
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (line.contains("Press enter to continue...")) {
                    System.out.print(line);
                    in.nextLine();
                    clearConsole();
                }
                else if(line.startsWith("Wait")) {
                    try {
                        Thread.sleep(Integer.parseInt(line.split(" ")[1]) * 1000L);
                        clearConsole();
                    } catch (Exception e){
                        System.out.println("Error occurred!");
                        return;
                    }
                }
                else
                    System.out.println(line);
            }
        }catch(Exception e){
            System.out.println("Error occurred while reading file!");
            System.exit(1);
        }
        file.setWritable(false);
    }
}
