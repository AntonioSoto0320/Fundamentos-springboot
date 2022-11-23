package com.fundamentos.springboot.fundamentos.practicando;

public class CalculadoraImplementSuma implements  CalculadoraSuma{

    @Override
    public int sum(int num1, int num2) {
        return num1+num2;
    }
}
