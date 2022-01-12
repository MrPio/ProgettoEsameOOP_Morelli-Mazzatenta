package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.api.DropboxAPI;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

class DropboxAPITest {
    final String desktopPath = System.getProperty("user.home") + "/Desktop/";

    /**
     *
     */
    @Test
    void uploadFile() {

        assert DropboxAPI.uploadFile(
                new File(desktopPath + "file.jpg"),
                "\\test\\").toString().contains("file.jpg");
    }

    @Test
    void downloadFile() {
        assert DropboxAPI.downloadFile(
                "\\test\\file.jpg",
                desktopPath + "file2.jpg");
    }

    @Test
    void getFilesInFolder() {
        ArrayList<String> files = DropboxAPI.getFilesInFolder("/test");
        System.out.print(Arrays.toString(files.toArray()));

        assert files.size() > 0;
    }

    @Test
    void downloadFilesInFolder() {
        String path = "/test/";
        for (String fileName : DropboxAPI.getFilesInFolder(path))
            DropboxAPI.downloadFile(path + fileName, desktopPath + fileName);
    }
}