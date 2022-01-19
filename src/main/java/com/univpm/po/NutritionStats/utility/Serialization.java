package com.univpm.po.NutritionStats.utility;

import com.univpm.po.NutritionStats.api.ChompBarcodeSearchAPI;
import com.univpm.po.NutritionStats.api.EdamamNutritionAnalysisAPI;
import com.univpm.po.NutritionStats.enums.Measure;
import com.univpm.po.NutritionStats.model.Diary;
import com.univpm.po.NutritionStats.model.Mailbox;
import com.univpm.po.NutritionStats.model.User;

import java.io.*;

/**
 * {@code Serialization}'s purpose is to work with instances of {@link Serializable serializable} classes.
 * This class can handle tasks like:
 * <p>•<b>Write</b> an object to disk,
 * <p>•<b>Read</b> an object from disk,
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
public class Serialization {
    private String path = "";
    private final String fileName;

    /**
     * First constructor.
     *
     * @param fileName the absolute or relative path to the file to work on.
     */
    public Serialization(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Second constructor.
     *
     * @param path     the directory of the file.
     * @param fileName the name of the file.
     */
    public Serialization(String path, String fileName) {
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
     * <b>Method used to load an object from file.</b>
     *
     * @return an instance of {@link Object} assigned with the real type of the serialized object ({@code late binding}).
     * The casting to the real type will be done by the caller method.
     */
    public Object loadObject() {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path + fileName)));) {
            return in.readObject();
        } catch (IOException e) {
            return ".dat not found!";
        } catch (ClassNotFoundException e) {
            System.err.println("cannot load the object!");
        }
        return null;
    }

    /**
     * <b>Method used to write provided object inside a file.</b>
     *
     * @param obj the object to serialize.
     * @param <T> the class of the provided object (which <b>must</b> implements the interface {@link Serializable}),
     */
    public <T extends Serializable> void saveObject(T obj) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path + fileName)))) {
            out.writeObject(obj);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
