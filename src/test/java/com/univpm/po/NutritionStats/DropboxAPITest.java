package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import com.univpm.po.NutritionStats.utility.InputOutput;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class DropboxAPITest {
    final String desktopPath = System.getProperty("user.home") + "/Desktop/";
    boolean fileUploaded=false;

    /**
     * Try to upload a file named "file.jpg" located on desktop.
     */
    @Test
    void uploadFile() {
        if (!new InputOutput(desktopPath, "file.jpg").existFile()) {
            System.err.println("Cannot find " + desktopPath + "file.jpg" + " !");
            return;
        }
        assert DropboxAPI.uploadFile(
                new File(desktopPath + "file.jpg"),
                "\\test\\").toString().contains("file.jpg");
        fileUploaded=true;
    }

    /**
     * Try to download a file named "file.jpg" inside the desktop folder.
     */
    @Test
    void downloadFile() {
        if(!fileUploaded)
            return;
        assert DropboxAPI.downloadFile(
                "\\test\\file.jpg",
                desktopPath + "file2.jpg");
    }

    /**
     * Try to list all the files inside the "/test" folder.
     */
    @Test
    void getFilesInFolder() {
        String path = "/test/";
        ArrayList<String> files = DropboxAPI.getFilesInFolder(path);
        if (files.size() > 0)
            System.err.println("The folder is empty!");
        else
            System.out.print(Arrays.toString(files.toArray()));
    }

    /**
     * Try to download all the files inside the "/test" folder.
     */
    @Test
    void downloadFilesInFolder() {
        String path = "/test/";
        for (String fileName : DropboxAPI.getFilesInFolder(path)) {
            if (!new InputOutput(desktopPath, fileName).existFile())
                DropboxAPI.downloadFile(path + fileName, desktopPath + fileName);
        }

    }
}