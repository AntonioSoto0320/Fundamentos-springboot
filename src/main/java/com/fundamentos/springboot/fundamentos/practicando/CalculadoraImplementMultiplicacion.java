package com.fundamentos.springboot.fundamentos.practicando;

public class CalculadoraImplementMultiplicacion implements  CalculadoraMulti{

    @Override
    public int multi(int num1, int num2) {
        return num1*num2;
    }
}
