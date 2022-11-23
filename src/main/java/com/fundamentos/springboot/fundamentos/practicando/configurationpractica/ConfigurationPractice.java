package com.fundamentos.springboot.fundamentos.practicando.configurationpractica;

import com.fundamentos.springboot.fundamentos.practicando.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class ConfigurationPractice {

   /* @Bean
    public Calculadora calculadora(){
        //return new CalculadoraImplementSuma();
        //return new CalculadoraImplementResta();
        //return new CalculadoraImplementMultiplicacion();
        //return new CalculadoraImplementDivision();
    }*/

    @Bean
    public CalculadoraSuma calculadoraSuma(){
        return  new CalculadoraImplementSuma();
    }

    @Bean CalculadoraResta calculadoraResta(){
        return new CalculadoraImplementResta();
    }

    @Bean CalculadoraMulti calculadoraMulti(){
        return new CalculadoraImplementMultiplicacion();
    }

    @Bean CalculadoraDivision calculadoraDivision(){

        return new CalculadoraImplementDivision();
    }

    @Bean Calculadora calculadora(CalculadoraSuma cs,CalculadoraResta cr,CalculadoraMulti cm,CalculadoraDivision cd){
        return new CalculadoraImplementOperations(cs,cr,cm,cd);
    }

}
