package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.utility.InputOutput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputOutputTest {
    final String PATH = "api_response/chomp/";
    final String FILE_NAME = "file.txt";
    InputOutput inputOutput = new InputOutput(PATH, FILE_NAME);

    /**
     * Try to verify the existence of a sample text file.
     */
    @Test
    void existFile() {
        inputOutput.existFile();
    }

    /**
     * Try to write a sample text file.
     */
    @Test
    void writeFile() {
        inputOutput.writeFile("Hello World!");
    }

    /**
     * Try to read a sample text file.
     */
    @Test
    void readFile() {
        assertEquals(inputOutput.readFile(), "Hello World!");
    }


}