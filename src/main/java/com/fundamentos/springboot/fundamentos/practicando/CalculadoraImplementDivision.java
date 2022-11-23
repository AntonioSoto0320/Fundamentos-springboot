package com.fundamentos.springboot.fundamentos.practicando;

public class CalculadoraImplementDivision implements  CalculadoraDivision{

    @Override
    public int div(int num1, int num2) {
        return num1/num2;
    }
}
