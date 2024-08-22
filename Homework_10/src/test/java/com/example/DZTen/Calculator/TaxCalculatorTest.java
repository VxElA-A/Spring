package com.example.DZTen.Calculator;

import com.example.DZTen.Calculator.example.TaxCalculator;
import com.example.DZTen.Calculator.example.TaxResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class TaxCalculatorTest {

    @Mock
    TaxResolver mock;

    @Test
    void testGetPriceWithTax(){
//        TaxResolver mock = mock(TaxResolver.class);
        when(mock.getCurrentTax()).thenReturn(0.5);
        TaxCalculator taxCalculator = new TaxCalculator(mock);

        Assertions.assertEquals(150.0,taxCalculator.getPriceWithTax(100.0),0.000009);
    }

}