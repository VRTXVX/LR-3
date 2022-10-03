package com.droidbattle.recreatebattle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.droidbattle.color.Color.*;
import static com.droidbattle.menu.MenuUtils.clearConsole;
import static com.droidbattle.menu.MenuUtils.in;

public class SaveBattleToFile {

    private static boolean isSaving = false;
    private static String fullPathToFile;

    public static void createFile(){
        if(!isSaving)
            return;
        clearConsole();
        String fileName;

        String path = System.getProperty("user.home") + "\\AppData\\Local\\DroidBattle";

        File folder = new File(path);

        if(!folder.exists()){
            folder.mkdir();
        }

        try {
            printFileImage();
            System.out.printf("%64sEnter name of file to write battle in:\n\n%79s>>> ", "", "");
            fileName = in.nextLine();

            fullPathToFile = path + "\\" + fileName + (fileName.contains(".txt") ? "" : ".txt");

            File file = new File(fullPathToFile);
            file.createNewFile();

            if(!file.exists()){
                System.out.println("Error has occurred! The file will not be created!");
                isSaving = false;
            }

            file.setWritable(false);
        }catch (Exception e){
            System.out.println("Error has occurred! The file will not be created!");
            isSaving = false;
        }
        clearConsole();
    }

    private static void printFileImage(){
        System.out.printf("\n" +
                        "%70s%s███████╗██╗██╗░░░░░███████╗\n" +
                        "%70s██╔════╝██║██║░░░░░██╔════╝\n" +
                        "%70s█████╗░░██║██║░░░░░█████╗░░\n" +
                        "%70s██╔══╝░░██║██║░░░░░██╔══╝░░\n" +
                        "%70s██║░░░░░██║███████╗███████╗\n" +
                        "%70s╚═╝░░░░░╚═╝╚══════╝╚══════╝%s\n\n", "", GREEN, "", "", "", "", "", RESET_COLOUR);
    }

    public static void writeRoundIntoFile(String string) {
        if(!isSaving)
            return;
        File file = new File(fullPathToFile);
        file.setWritable(true);
        try (FileWriter myWriter = new FileWriter(fullPathToFile, true)) {
            myWriter.write(string);
        }
        catch(IOException e){
            System.out.println("Error: " + e);
        }
        file.setWritable(false);
    }

    public static void changeIsSaving() { isSaving = !isSaving; }
    public static boolean getIsSaving(){ return isSaving; }
}
