package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    final String email="email@example.com";
    User user;

    @BeforeEach
    void setUp() {
        user=new User(email);
    }

    /**
     * Try to verify if the token really equals the encrypted value of the provided email with md5 hash function.
     */
    @Test
    void generateToken() {
        assertEquals(user.generateToken(),"5658ffccee7f0ebfda2b226238b1eb6e");
    }

    /**
     * Try to verify if the token really equals the encrypted value of the provided email with md5 hash function.
     * This time using java {@code reflections}.
     */
    @Test
    void generateTokenWithReflect() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String token=User.class.getDeclaredConstructor(String.class).newInstance(email).generateToken();
        assertEquals(token,"5658ffccee7f0ebfda2b226238b1eb6e");
    }
}