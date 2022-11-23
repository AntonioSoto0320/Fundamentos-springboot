package com.fundamentos.springboot.fundamentos.bean;

import com.fundamentos.springboot.fundamentos.FundamentosApplication;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements  MyBeanWithDependency{
    Log LOG= LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;


    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOG.info("Hemos ingresado al metodo prontWithDependecy");
        int numero = 2;
        LOG.debug("el numero enviado por parametro es : "+numero);
        System.out.println("el numero es :"+myOperation.sum(numero));
        System.out.println("Hola desde la implementacion de  WithDependency");
    }
}
