package com.univpm.po.NutritionStats.junit;

import com.univpm.po.NutritionStats.utility.InputOutputImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputOutputImplTest {
    final String PATH = "api_response/chomp/";
    final String FILE_NAME = "file.txt";
    InputOutputImpl inputOutput = new InputOutputImpl(PATH, FILE_NAME);

    @Test
    void existFile() {
        inputOutput.existFile();
    }

    @Test
    void writeFile() {
        inputOutput.writeFile("Hello World!");
    }

    @Test
    void readFile() {
        assertEquals(inputOutput.readFile(),"Hello World!");
    }


}