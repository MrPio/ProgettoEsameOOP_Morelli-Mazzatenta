package com.univpm.po.NutritionStats.utility;

import java.io.Serializable;

public interface Serialization {
    <T extends Serializable> Object loadObject();

    public <T extends Serializable> void saveObject(T obj);
}
