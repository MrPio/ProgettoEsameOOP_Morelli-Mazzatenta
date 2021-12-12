package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import com.univpm.po.NutritionStats.utility.SerializationImpl;
import org.json.simple.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

class SerializationImplTest {
    final String FILE_NAME="jsonList.dat";
    ArrayList<JSONObject> jsonList=new ArrayList<>();
    SerializationImpl si=new SerializationImpl(FILE_NAME);
    InputOutputImpl io=new InputOutputImpl(FILE_NAME);

    @BeforeEach
    void setUp() {
        jsonList.add(new JSONObject(Map.of("test1 s","test1 r","test2 s","test2 r")));
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tests END");
    }

    @Test
    void saveObject() {
        System.out.println("Test START: saveObject");
        si.saveObject(jsonList);
        assert io.existFile();
    }

    @Test
    void loadObject() {
        System.out.println("Test START: loadObject");
        assertEquals(si.loadObject(),jsonList);
    }
}