package com.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest {

    @Test
    public void testMessage() {
        assertEquals(
            "Hello DevOps! Java App Running Successfully 🚀",
            App.getMessage()
        );
    }
}
