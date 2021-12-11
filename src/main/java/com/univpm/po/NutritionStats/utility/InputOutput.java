package com.univpm.po.NutritionStats.utility;

public interface InputOutput {
    String readFile();

    boolean writeFile(String text,boolean... append);

    boolean existFile();
}
