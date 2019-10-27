package challenge;


import org.junit.Before;
import org.junit.Test;

import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.zip.DeflaterOutputStream;

public class EstacionamentoTest {

    private Estacionamento estacionamento;

    @Before
    public void setUp() {
        estacionamento = new Estacionamento();
    }

    @Test(expected = NullPointerException.class)
    public void deveRetornarErroQuandoMotoristaNaoTemHabilitacao() {

        Motorista motorista = Motorista.builder()
                .withIdade(17)
                .withPontos(10)
                .withIdade(-1)
                .build();

        Carro carro =  Carro.builder()
                .withMotorista(motorista)
                .build();
        estacionamento.estacionar(carro);

    }

    @Test(expected = NullPointerException.class)
    public void deveRetornarErroQuandoMotoristaNaoTemNome() {
        Motorista motorista = Motorista.builder()
                .withIdade(17)
                .withPontos(10)
                .withHabilitacao("12213")
                .build();

        Carro carro =  Carro.builder().withCor(Cor.COLORIDO).withMotorista(motorista).build();
        estacionamento.estacionar(carro);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetorarErroQuandoIdadeNegativa() {
        Motorista motorista = Motorista.builder()
                .withIdade(17)
                .withPontos(10)
                .withHabilitacao("12213")
                .withIdade(-1).build();


        Carro carro =  Carro.builder()
                .withPlaca("1231231")
                .withCor(Cor.COLORIDO)
                .withMotorista(motorista)
                .build();
        estacionamento.estacionar(carro);



    }

    @Test(expected = IllegalArgumentException.class)
    public void deveRetorarErroQuandoPontosNegativos() {

        Motorista motorista = Motorista.builder()
                .withIdade(17)
                .withPontos(10)
                .withHabilitacao("12213")
                .withIdade(-1).build();


        Carro carro =  Carro.builder()
                .withPlaca("1231231")
                .withCor(Cor.COLORIDO)
                .withMotorista(motorista)
                .build();
        estacionamento.estacionar(carro);


    }


    @Test(expected = NullPointerException.class)
    public void deveRetornarErroQuandoNaoTemPlaca() {

        Motorista motorista = Motorista.builder()
                .withIdade(17)
                .withPontos(10)
                .withHabilitacao("12213")
                .withIdade(-1)
                .build();

        Carro carro =  Carro.builder()
                .withMotorista(motorista)
                .build();
        estacionamento.estacionar(carro);
    }

    @Test(expected = NullPointerException.class)
    public void deveRetornarErroQuandoNaoTemCor() {
        Motorista motorista = Motorista.builder()
                .withIdade(17)
                .withPontos(10)
                .withHabilitacao("12213")
                .withIdade(-1).build();


        Carro carro =  Carro.builder()
                .withPlaca("1231231")
                .withMotorista(motorista)
                .build();
        estacionamento.estacionar(carro);
    }


    @Test(expected = EstacionamentoException.class)
    public void naoDeveTerCarroAutonomo() {
        Carro carro = Carro.builder().withCor(Cor.COLORIDO).withPlaca("123").build();
        estacionamento.estacionar(carro);
    }

    @Test(expected = EstacionamentoException.class)
    public void naoDeveTerMotoristaDeMenor() {
        Motorista motorista = Motorista.builder().withNome("Ada")
                .withIdade(17)
                .withPontos(10)
                .withHabilitacao("1231")
                .build();
        Carro carro = Carro.builder().withCor(Cor.PRETO).withPlaca("123").withMotorista(motorista).build();
        estacionamento.estacionar(carro);
    }

    @Test(expected = EstacionamentoException.class)
    public void naoDeveEstacionarMotoristaSemPontos() {
        Motorista motorista = Motorista.builder().withNome("Ada")
                .withIdade(25)
                .withPontos(30)
                .withHabilitacao("1231")
                .build();
        Carro carro = Carro.builder().withCor(Cor.BRANCO).withPlaca("123").withMotorista(motorista).build();
        estacionamento.estacionar(carro);
    }

    @Test
    public void deveEstacionar() {
        Motorista motorista = Motorista.builder().withNome("Ada").withIdade(20)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();
        Carro carro = Carro.builder().withCor(Cor.BRANCO).
                withPlaca("123")
                .withMotorista(motorista)
                .build();

        estacionamento.estacionar(carro);

        assertEquals(1, estacionamento.carrosEstacionados());

    }

    @Test
    public void naoDeveUltrapassarEstacionamento() {
        Motorista ada = Motorista.builder().withNome("Ada").withIdade(20)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();

        Carro carroBranco = Carro.builder().withCor(Cor.BRANCO).
                withPlaca("123")
                .withMotorista(ada)
                .build();

        estacionamento.estacionar(carroBranco);

        for (int indice = 1; indice <= 10; indice++) {

            Motorista motorista = Motorista.builder()
                    .withNome("Motorista " + indice)
                    .withIdade(20)
                    .withPontos(3)
                    .withHabilitacao(Long.toString(current().nextLong()))
                    .build();

            Carro carro = Carro.builder().withCor(Cor.BRANCO).
                    withPlaca("123")
                    .withMotorista(motorista)
                    .build();

            estacionamento.estacionar(carro);
        }
        int carros = estacionamento.carrosEstacionados() +1;

        assertEquals(10,carros);
        assertFalse(estacionamento.carroEstacionado(carroBranco));
    }


    @Test
    public void casoPrimeiroMotoristaSejaSeniorEleNaoDeveSair() {

        Motorista ada = Motorista.builder().withNome("Ada")
                .withIdade(60)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();

        Carro carroBranco = Carro.builder().withCor(Cor.BRANCO).
                withPlaca("123")
                .withMotorista(ada)
                .build();

        estacionamento.estacionar(carroBranco);

        for (int indice = 1; indice <= 10; indice++) {


            double x = Math.random();

            String placa = String.valueOf(x);

            Motorista motorista = Motorista.builder()
                    .withNome("Motorista " + indice)
                    .withIdade(20)
                    .withPontos(3)
                    .withHabilitacao(Long.toString(current().nextLong()))
                    .build();

            Carro carro = Carro.builder()
                    .withCor(getCor()).
                            withPlaca(placa)
                    .withMotorista(motorista)
                    .build();

            estacionamento.estacionar(carro);
        }

        assertEquals(10, estacionamento.carrosEstacionados()+1);
        assertTrue(estacionamento.carroEstacionado(carroBranco));
    }


    @Test(expected = EstacionamentoException.class)
    public void casoTodosSejamSeniorONovoMotoristaNaoTeraVaga() {

        Motorista ada = Motorista.builder().withNome("Ada")
                .withIdade(60)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();

        Carro carroBranco = Carro.builder().withCor(Cor.BRANCO).
                withPlaca("123")
                .withMotorista(ada)
                .build();

        estacionamento.estacionar(carroBranco);

        for (int indice = 1; indice <= 10; indice++) {

            double x = Math.random();

            String placa = String.valueOf(x); // str is '123.456'

            Motorista motorista = Motorista.builder()
                    .withNome("Motorista " + indice)
                    .withIdade(60)
                    .withPontos(3)
                    .withHabilitacao(Long.toString(current().nextLong()))
                    .build();

            Carro carro = Carro.builder()
                    .withCor(getCor()).
                            withPlaca(placa)
                    .withMotorista(motorista)
                    .build();

            estacionamento.estacionar(carro);
        }

    }

    private Cor getCor() {
        return Cor.values()[current().nextInt(0, 2)];
    }

}
