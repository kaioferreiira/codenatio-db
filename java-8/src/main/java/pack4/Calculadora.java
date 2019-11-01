package pack4;

import java.math.BigDecimal;

public class Calculadora {

    @Somar(value="100.00")
    private BigDecimal numero1;

    @Somar(value="100.50")
    private BigDecimal numero2;

    @Somar(value="10.50")
    private BigDecimal numero3;

    @Subtrair(value="20.50")
    private BigDecimal numero4;
}
