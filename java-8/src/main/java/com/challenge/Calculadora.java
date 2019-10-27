package com.challenge;

import java.math.BigDecimal;

public class Calculadora {

    private BigDecimal valorA;
    private BigDecimal valorB;
    private BigDecimal total;

    public Calculadora() {
    }

    public Calculadora(BigDecimal valorA, BigDecimal valorB, BigDecimal total) {
        this.valorA = valorA;
        this.valorB = valorB;
        this.total = total;
    }

    public BigDecimal getValorA() {
        return valorA;
    }

    public void setValorA(BigDecimal valorA) {
        this.valorA = valorA;
    }

    public BigDecimal getValorB() {
        return valorB;
    }

    public void setValorB(BigDecimal valorB) {
        this.valorB = valorB;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
