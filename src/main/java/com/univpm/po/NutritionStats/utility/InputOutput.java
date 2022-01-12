package com.univpm.po.NutritionStats.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class InputOutput {
    private String path = "";
    private String fileName;

    public InputOutput(String fileName) {
        this.fileName = fileName;
    }

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

    public boolean existFile() {
        File file = new File(path + fileName);
        return file.exists();
    }

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

    public File[] listFilesInDirectory(){
        return new File(path).listFiles();
    }
}
