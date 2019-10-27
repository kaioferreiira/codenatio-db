package challenge;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Estacionamento {

    List<Carro> carrosBanco = new ArrayList<Carro>();
//    private static final Map<String, Carro> carrosBanco = new HashMap<>();

    private final int LOTACAO_ESTACIONAMENTO = 10;
    private final int IDADE_SENIOR = 55;
    private final int IDADE_MINIMA_PARA_HABILITACAO = 18;
    private final int MAXIMO_DE_PONTOS_HABILITACAO = 20;



    public void estacionar(Carro carro) {

        if (Objects.isNull(carro.getMotorista())){
            throw  new EstacionamentoException("sem motorista ");
        }

        if (Objects.isNull(carro.getCor())){
           throw  new NullPointerException();
        }

        if (Objects.isNull(carro.getPlaca())){
            throw  new NullPointerException();
        }

        if (Objects.isNull(carro.getMotorista().getHabilitacao())){
            throw  new NullPointerException();
        }

        if (carro.getMotorista().getIdade() < 0){
            throw  new IllegalArgumentException();
        }


        if (Objects.isNull(carro.getMotorista()) || carro.getMotorista().getIdade() < IDADE_MINIMA_PARA_HABILITACAO || carro.getMotorista().getPontos() > MAXIMO_DE_PONTOS_HABILITACAO){
            throw new EstacionamentoException("Nao e possivel estacionar o motorista");
        }

        long qtdCarrosBanco = carrosBanco.stream().count();
        long carrosSenior = carrosBanco.stream()
                .filter(carro1 -> carro1.getMotorista().getIdade() > IDADE_SENIOR)
                .count();

        if (qtdCarrosBanco >= LOTACAO_ESTACIONAMENTO){

            if (carrosSenior >= 10){
                throw  new EstacionamentoException(" fechado");
            }

            if (Objects.nonNull(carrosSenior)) {

                Optional<Carro> carroSeniorBanco = carrosBanco
                        .stream()
                        .filter(validaCarro -> validaCarro.getMotorista().getIdade() < IDADE_SENIOR)
                        .findFirst();

                carrosBanco.remove(carroSeniorBanco.get());

            } else {
                carrosBanco.remove(0);
            }

        } else {
                carrosBanco.add(carro);

        }

    }

    public int carrosEstacionados() {

        long count = carrosBanco.stream().count();
        int i=(int)count;

        return i;
    }

    public boolean carroEstacionado(Carro carro) {

        boolean carroEncontrado = carrosBanco.stream().filter(carroBanco -> carroBanco.getMotorista().equals(carro.getMotorista())).findFirst().isPresent();

        return carroEncontrado;
    }
}
