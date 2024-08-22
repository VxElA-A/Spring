package com.example.DZTen.Calculator;

import com.example.DZTen.Calculator.example.CalculatorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {


    @Test
    void testSum() {
        CalculatorCode calculatorCode = new CalculatorCode();
        int actual = calculatorCode.sum(2, 3);
        int expected = 5;
//        assertEquals(expected, actual);

        Assertions.assertEquals(expected, actual);

    }
}
