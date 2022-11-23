package com.fundamentos.springboot.fundamentos.practicando;

import com.fundamentos.springboot.fundamentos.practicando.configurationpractica.CalculadoraResta;

public class CalculadoraImplementOperations implements  Calculadora{

    private CalculadoraSuma calculadoraSuma;
    private CalculadoraResta calculadoraResta;
    private CalculadoraMulti calculadoraMulti;
    private CalculadoraDivision calculadoraDivision;

    public CalculadoraImplementOperations(CalculadoraSuma calculadoraSuma, CalculadoraResta calculadoraResta, CalculadoraMulti calculadoraMulti, CalculadoraDivision calculadoraDivision) {
        this.calculadoraSuma = calculadoraSuma;
        this.calculadoraResta = calculadoraResta;
        this.calculadoraMulti = calculadoraMulti;
        this.calculadoraDivision = calculadoraDivision;
    }


    @Override
    public void operationsCalculator(int number1, int number2) {
        System.out.println("La suma es: "+ calculadoraSuma.sum(number1,number2));
        System.out.println("La resta es : "+calculadoraResta.res(number1,number2));
        System.out.println("La multiplicacion es : "+calculadoraMulti.multi(number1,number2));
        System.out.println("La division es : "+calculadoraDivision.div(number1,number2));

    }
}
