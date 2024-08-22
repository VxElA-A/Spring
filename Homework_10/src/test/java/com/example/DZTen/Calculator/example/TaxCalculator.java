package com.example.DZTen.Calculator.example;

public class TaxCalculator {

    private final TaxResolver taxResolver;

    public TaxCalculator(TaxResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    public double getPriceWithTax(double price){
        double currentTax = taxResolver.getCurrentTax();
        return price + price * currentTax;

    }
}
