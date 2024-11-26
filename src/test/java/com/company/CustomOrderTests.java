package com.company;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.condition.JRE;

import java.util.Comparator;

@TestMethodOrder(CustomOrder.class)
//@TestMethodOrder(OrderAnnotation.class)
class CustomOrderedTest {

    @Test
    @EnabledOnOs(OS.WINDOWS)
//    @EnabledOnJre(JRE.JAVA_11)
//    @EnabledIfEnvironmentVariable(named = "ENV", matches = "TEST")
//    @DisabledOnOs({OS.MAC, OS.LINUX})
//    @Order(1)
    void testZ() {
        System.out.println("Test Z");
    }

    @Test
//    @Order(2)
    void testA() {
        System.out.println("Test A");
    }
}

class CustomOrder implements MethodOrderer {
    @Override
    public void orderMethods(MethodOrdererContext context) {
        context.getMethodDescriptors().sort(Comparator.comparing(m -> m.getMethod().getName()));
    }
}
