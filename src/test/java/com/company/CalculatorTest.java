package com.company;

import com.company.unitTesting.service.Calculator;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class CalculatorTest {
    private static final Calculator calculator = new Calculator();

    @Test
    void add() {
        assertEquals( 6, calculator.add(2,3), "add two number");
//        assertEquals( 5, calculator.add(2,3), "add two number");
    }

    @Test
    /**
     * it's about group assertion
     */
    void multiply() {
        assertAll(() -> assertEquals(4, calculator.multiply(2, 2)),
                () -> assertEquals(-8, calculator.multiply(2, -2)),
                () -> assertNotEquals(585, calculator.multiply(-2, -2)),
                () -> assertEquals(5, calculator.multiply(1, 0)));
    }


    /**
     * if we use DynamicTests, it will show exactly which assertion is wrong!
     * but if we use like method this.add() whit two or more assertion, its just throw failed and say nothing about which assertion is wrong
     */
    @TestFactory
    Collection<DynamicTest> addDynamics() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Test 1 + 1 ", () -> assertEquals(2, calculator.add(1,1))),
                DynamicTest.dynamicTest("Test 1 + 3 ", () -> assertEquals(6, calculator.add(1,3)))
        );
    }

    /**
     * @Nested  organize big test classes and form logical groupings of test cases
     */
    @Nested
    class naturalNumsOperations{
        @Test
        void add(){
            assertEquals( 6, calculator.add(2,3), "add two Natural number");
        }

        @Test
        void multiply(){
            assertEquals(4, calculator.multiply(2, 2));
        }
    }


    @Nested
    class nonNaturalNumsOperations{
        @Test
        void add(){
            assertEquals( -8, calculator.add(2, -10), "add two non Natural number");
        }

        @Test
        void multiply(){
            assertEquals(4, calculator.multiply(2, -4));
        }
    }


    /**
     * Tags are used to filter which tests are executed for a given test plan.
     * For example, a development team may tag tests with values such as "fast" , "slow" , "ci-server" , etc.
     */
    @Test
    @Tag("complicated")
    void addPi() {
        assertEquals( 5, calculator.add(Math.PI ,1), "add pi number");
    }


    @Disabled("disabled until bug ## has been resolved!")
    void testWillSkipped(){
    }


    @RepeatedTest(3)
    void randomAdd(){
        double fRandNum = Math.random();
        double sRandNum = Math.random();
        assertEquals( fRandNum + sRandNum, calculator.add(fRandNum ,sRandNum), "add randomNumber togethers");
    }

    @Test
    @Timeout(value = 3, unit = TimeUnit.SECONDS)
    void testWhitTimeOut() throws InterruptedException {
        Thread.sleep(5000);

        /*another way to check timeOut*/
//        assertTimeout(Duration.ofSeconds(2), this::add);
    }


    @ParameterizedTest
    @ValueSource(ints = {1,-8,3})
    void testParametrizedMultiple(int num){
        assertEquals(num * Math.PI, calculator.multiply(num,Math.PI));
    }

    @ParameterizedTest
    @CsvSource({"1,8", "6,2", "40,3"})
//    @NullAndEmptySource
//    @EmptySource
//    @NullSource
    public void testParametersAdd(int x, int y) {
        assertEquals(x+y, calculator.add(x,y));
    }



    @ParameterizedTest
    @MethodSource("getRandomNum")
    void testGetRandomNumMultiple(int num){
        assertEquals(num * Math.PI, calculator.multiply(num,Math.PI));
    }

    static Stream<Integer> getRandomNum(){
        return Stream.of(5, 8, 458);
    }
}
