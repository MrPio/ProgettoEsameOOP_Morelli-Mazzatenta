package com.univpm.po.NutritionStats;

import com.univpm.po.NutritionStats.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    final String email="email@example.com";
    User user;

    @BeforeEach
    void setUp() {
        user=new User(email);
    }

    @Test
    void generateToken() {
        assertEquals(user.generateToken(),"5658ffccee7f0ebfda2b226238b1eb6e");
    }
}