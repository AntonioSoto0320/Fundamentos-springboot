package com.fundamentos.springboot.fundamentos.practicando;

import com.fundamentos.springboot.fundamentos.practicando.configurationpractica.CalculadoraResta;

public class CalculadoraImplementResta implements CalculadoraResta {

    @Override
    public int res(int num1, int num2) {
        return num1-num2;
    }
}
