package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.utility.InputOutput;
import com.univpm.po.NutritionStats.utility.Serialization;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

class SerializationTest {
    final String FILE_NAME="jsonList.dat";
    ArrayList<JSONObject> jsonList=new ArrayList<>();
    Serialization si=new Serialization(FILE_NAME);
    InputOutput io=new InputOutput(FILE_NAME);

    @BeforeEach
    void setUp() {
        jsonList.add(new JSONObject(Map.of("test1 s","test1 r","test2 s","test2 r")));
    }

    /**
     * Try to save a sample JsonObject object.
     */
    @Test
    void saveObject() {
        System.out.println("Test START: saveObject");
        si.saveObject(jsonList);
        assert io.existFile();
    }

    /**
     * Try to load a sample JsonObject object.
     */
    @Test
    void loadObject() {
        System.out.println("Test START: loadObject");
        Assertions.assertEquals(si.loadObject(),jsonList);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tests END");
    }
}