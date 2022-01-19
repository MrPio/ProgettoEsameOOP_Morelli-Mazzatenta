package com.univpm.po.NutritionStats.utility;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Mailbox;
import com.univpm.po.NutritionStats.model.User;

import java.io.*;
import java.util.Scanner;

/**
 * {@code InputOutput}'s purpose is to work between primary (<b>RAM</b>) and secondary (<b>HDD/SSD</b>) memory to
 * execute tasks like :
 * <p>•<b>Write</b> a file to disk,
 * <p>•<b>Read</b> a file from disk,
 * <p>•<b>Check</b> if a file exists,
 * <p>•<b>List</b> all the files inside a directory.
 * <p>This class is instantiated almost everywhere inside the project.
 *
 * @see Diary#save()
 * @see Diary#load(String)
 * @see ChompBarcodeSearchAPI#getEanInfo(long)
 * @see ChompBarcodeSearchAPI#getFood(long, int)
 * @see EdamamNutritionAnalysisAPI#getFoodInfo(String)
 * @see EdamamNutritionAnalysisAPI#getFood(String, int, Measure)
 * @see Mailbox#save(String)
 * @see User#loadMailbox()
 */
public class InputOutput {
    private String path = "";
    private final String fileName;

    /**
     * First constructor.
     *
     * @param fileName the absolute or relative path to the file to work on.
     */
    public InputOutput(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Second constructor.
     *
     * @param path     the directory of the file.
     * @param fileName the name of the file.
     */
    public InputOutput(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;

        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
    }

    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFullPath() {
        return path + fileName;
    }

    /**
     * <b>Method used to read the file interpreting it as a sequence of ASCII characters.</b>
     *
     * @return an instance of {@link String} containing the text of the file.
     */
    public String readFile() {
        StringBuilder content = new StringBuilder();
        try (Scanner fileInput = new Scanner(new BufferedReader(new FileReader(path + fileName)));) {
            while (fileInput.hasNextLine())
                content.append(fileInput.nextLine());
        } catch (IOException e) {
            return "";
        }
        return content.toString();
    }

    /**
     * <b>Method used to check if a file exists.</b>
     *
     * @return a boolean value representing the existence of the file.
     */
    public boolean existFile() {
        File file = new File(path + fileName);
        return file.exists();
    }

    /**
     * <b>Method used to write provided text inside a file.</b>
     *
     * @param text   the text to write inside the file.
     * @param append an optional variable (<b>varargs</b>) which decide if overwrite or append
     *               the file in case it already exists
     * @return an instance of {@link String} containing the text of the file.
     */
    public boolean writeFile(String text, boolean... append) {
        if (append.length == 0)
            append = new boolean[]{false};
        try (PrintWriter fileOutput = new PrintWriter(new BufferedWriter(new FileWriter(path + fileName, append[0])));) {
            fileOutput.print(text);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public File[] listFilesInDirectory() {
        return new File(path).listFiles();
    }
}
