package challenge;

public class EstacionamentoAplication {

        public static Estacionamento estacionamento = new Estacionamento();


    public static void main(String[] args) {


        Motorista ada = Motorista.builder()
                .withNome("Ada")
                .withIdade(30)
                .withPontos(3)
                .withHabilitacao("1231")
                .build();

        Carro carroBranco = Carro.builder()
                .withCor(Cor.BRANCO)
                .withPlaca("5")
                .withMotorista(ada)
                .build();



//        estacionamento.cargaBanco();

        estacionamento.estacionar(carroBranco);

        estacionamento.carrosEstacionados();


        estacionamento.carroEstacionado(carroBranco);





    }
}
