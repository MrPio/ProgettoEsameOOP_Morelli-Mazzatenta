package com.univpm.po.NutritionStats.utility;

import java.io.*;

public class SerializationImpl implements Serialization {
    String path = "";
    String fileName;

    public SerializationImpl(String fileName) {
        this.fileName = fileName;
    }

    public SerializationImpl(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;

        File dir=new File(path);
        if(!dir.exists())
            dir.mkdirs();
    }

    public Object loadObject() {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path+fileName)));) {
            return in.readObject();
        } catch (IOException e) {
            return ".dat not found!";
        } catch (ClassNotFoundException e) {
            System.err.println("cannot load the object!");
        }
        return null;
    }

    public <T extends Serializable> void saveObject(T obj) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path+fileName)))) {
            out.writeObject(obj);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
