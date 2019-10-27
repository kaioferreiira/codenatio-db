package com.challenge.desafio;

import java.math.BigDecimal;

import com.challenge.Calculadora;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {


//    O método somar: deverá somar todos os atributos do tipo BigDecimal de uma classe recebida como parâmetro e retornar um BigDecimal.
//
//    O método subtrair: deverá somar todos os atributos do tipo BigDecimal de uma classe recebida como parâmetro e retornar um BigDecimal.
//
//    O método total: deverá subtrair os atributos annotados com “Subtrair” dos atributos annotados com “Somar”.
//
//    Caso a classe não tenha nenhum atributo com annotation, retornar BigDecimal.ZERO
//
//    Caso o atributo não seja BigDecimal, retornar BigDecimal.ZERO
//
//    Utilizar os packages existentes do projeto para crias as devidas classes.


    @Override
    public BigDecimal somar(Calculadora calculadora) {
        calculadora.setTotal(calculadora.getValorA().add(calculadora.getValorB()));
        BigDecimal total = calculadora.getTotal();
        return total;
    }

    @Override
    public BigDecimal subtrair(Calculadora calculadora) {
        calculadora.setTotal(calculadora.getValorA().subtract(calculadora.getValorB()));
        BigDecimal total = calculadora.getTotal();
        return total;
    }

    @Override
    public BigDecimal Totalizar(Calculadora calculadora) {

        calculadora.getValorA();
        calculadora.getValorB();


        return null;
    }
}
