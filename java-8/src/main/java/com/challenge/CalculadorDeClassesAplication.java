package com.challenge;

import com.challenge.desafio.CalculadorDeClasses;
import pack4.ClasseSemNada;
import pack4.ClasseZoada;

public class CalculadorDeClassesAplication {

    public static void main(String[] args) {

        CalculadorDeClasses verificaAnnotations = new CalculadorDeClasses(Calculadora.class);

        System.out.println("somar: " + verificaAnnotations.subtrair());
        System.out.println("subtrair:  " + verificaAnnotations.somar());
        System.out.println("totalizar:  " + verificaAnnotations.totalizar());

        CalculadorDeClasses an2 = new CalculadorDeClasses(ClasseSemNada.class);
        System.out.println("sdfsdfsd " + an2.totalizar());

        CalculadorDeClasses an3 = new CalculadorDeClasses(ClasseZoada.class);
        System.out.println("sdfsdfsd " + an3.totalizar());
    }

}
