package com.challenge;

import java.math.BigDecimal;

import com.challenge.desafio.CalculadorDeClasses;

public class CalculadoraAplication {

    public static  CalculadorDeClasses calculadorDeClasses = new CalculadorDeClasses();

    public static void main(String[] args) {

        Calculadora calculadora = new Calculadora();
        calculadora.setValorA(new BigDecimal(10.00));
        calculadora.setValorB(new BigDecimal(5.00));

        BigDecimal somar = calculadorDeClasses.somar(calculadora);

    }
}
