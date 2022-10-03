package com.droidbattle.menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;

import static com.droidbattle.color.Color.*;
import static com.droidbattle.menu.MenuImage.*;
import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;
import static com.droidbattle.recreatebattle.RunBattleFromFile.recreateBattle;

public class ReplayBattleMenu {
    public static void replayBattleMenu() {
        int choice1 = 0, choice2 = 0;
        String filePath, folderPath = new String(System.getProperty("user.home") + "\\AppData\\Local\\DroidBattle");
        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdir();
        }

        while (true) {
            clearConsole();

            printTextSaving();

            File[] listOfFiles = folder.listFiles();
            SimpleDateFormat sd = null;
            BasicFileAttributes fileAttributes = null;

            if (listOfFiles.length != 0) {
                System.out.printf("%62s%sName of battle%16sDate of battle%s\n", "", GREEN, "", RESET_COLOUR);
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        try {
                            fileAttributes = Files.readAttributes(Path.of(listOfFiles[i].getPath()), BasicFileAttributes.class);
                            sd = new SimpleDateFormat("dd.MM.yyyy");
                        } catch (IOException e) {
                            System.out.println("Error has occurred!");
                            System.exit(1);
                        }
                        System.out.printf("%56s[%s] - %-30s%s\n", "", i + 1,
                                listOfFiles[i].getName().replaceFirst("[.][^.]+$", ""),
                                sd.format(fileAttributes.creationTime().toMillis()));
                    }
                }
                System.out.printf("\n%56s[0] - Back to battle menu", "");
                System.out.printf("\n\n%69sChoose saved battle:\n\n%74s>>> ", "", "");
                try {
                    choice1 = in.nextInt();
                } catch (Exception e) {
                    in.nextLine(); // clear buffer
                    System.out.println("Incorrect value");
                }
                if (choice1 >= 1 && choice1 <= listOfFiles.length) {
                    filePath = new String(String.valueOf(listOfFiles[choice1 - 1]));
                    System.out.printf("\n\n%61sWhat you want to do with battle \"%s\":\n\n"
                            + "%56s[1] - Replay\t\t\t[0] - Delete"
                            + "\n%74s>>> ", "", listOfFiles[choice1 - 1].getName().replaceFirst("[.][^.]+$", ""), "", "");
                    try {
                        choice2 = in.nextInt();
                    } catch (Exception e) {
                        in.nextLine(); // clear buffer
                        System.out.println("Incorrect value");
                    }
                    if (choice2 == 1)
                        recreateBattle(filePath);
                    else if (choice2 == 0) {
                        File file = new File(filePath);
                        file.delete();
                    }
                } else if (choice1 == 0)
                    break;
            } else {
                System.out.printf("%65sThere are no saved battles...\n\n" +
                        "%52sHint: you save battle by turning on this option in settings.\n\n", "", "");
                System.out.printf(BLACK + "%66sPress enter to continue..." + RESET_COLOUR, " ");
                in.nextLine();
                in.nextLine();
                break;
            }
        }
    }
}