package br.com.codenation;


import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import jdk.nashorn.internal.scripts.JO;

public class DesafioMeuTimeTest {

    private static final Map<Long, TimeFutebol> timesCadastradosBD = new LinkedHashMap<>();
    private static final Map<Long, Jogador> jogadoresCadastradosBD = new LinkedHashMap<>();


    public static void carregaBaseDados(){
        Long idTime = 1l;
        String nome = "cruzeiro";
        LocalDate dataCriacao = LocalDate.now();
        String corUniformePrincipal = "Azul";
        String corUniformeSecundario = "Branco";

        TimeFutebol timeFutebol1 = new TimeFutebol();
        timeFutebol1.setId(idTime);
        timeFutebol1.setNome(nome);
        timeFutebol1.setDataCriacao(dataCriacao);
        timeFutebol1.setCorUniformePrincipal(corUniformePrincipal);
        timeFutebol1.setCorUniformeSecundario(corUniformeSecundario);
        timeFutebol1.setJogadorCapitaoTime(null);

        timesCadastradosBD.put(timeFutebol1.getId(), timeFutebol1);

        Jogador jogador1 = new Jogador(1l, timeFutebol1,"Kaio", LocalDate.now(), 90, new BigDecimal(1212.22) );

        jogadoresCadastradosBD.put(jogador1.getId(), jogador1);


    }

    public static void main(String[] args) {
        carregaBaseDados();
        incluirTime(2l, "flamengo", LocalDate.now(), "vermelho", "branco");
        incluirJogador(2l, 2l, "lucas", LocalDate.now(), 30, new BigDecimal(2000.00));
        incluirJogador(3l, 1l, "mateus", LocalDate.of(2020,1,1), 60, new BigDecimal(600000.00));

        definirCapitao(1l);

        buscarCapitaoDoTime(1l);

        buscarNomeJogador(1l);

        buscarNomeTime(2l);

        buscarJogadoresDoTime(1l);

        buscarMelhorJogadorDoTime(1l);

        buscarJogadorMaisVelho(1l);

        buscarTimes();

        buscarJogadorMaiorSalario(1l);

        buscarSalarioDoJogador(1l);

        buscarTopJogadores(1);

        buscarCorCamisaTimeDeFora(1l,2l);
    }

    private static Optional<TimeFutebol> timesFindById(Long idTime) {
        TimeFutebol timeFutebol = timesCadastradosBD.get(idTime);
        return Optional.ofNullable(timeFutebol);
    }

    private static Optional<Jogador> jogadorFindById(Long idjogador) {
        Jogador jogador = jogadoresCadastradosBD.get(idjogador);
        return Optional.ofNullable(jogador);
    }


    private static void cadastraNovoTimeBD(TimeFutebol timeFutebol) {
        timesCadastradosBD.put(timeFutebol.getId(), timeFutebol);
    }

    private static void salvaJogadorBD(Jogador jogador) {
        jogadoresCadastradosBD.put(jogador.getId(), jogador);
    }

	public static void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
            String corUniformeSecundario) {

        Boolean validaTimeBanco = timesFindById(id).isPresent();

        if (validaTimeBanco.equals(Boolean.TRUE)){
            throw new IdentificadorUtilizadoException();
        }
        TimeFutebol timeFutebol = new TimeFutebol(id,nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null);
        cadastraNovoTimeBD(timeFutebol);
	}

    public static void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
            Integer nivelHabilidade,
            BigDecimal salario) {

       Boolean validaTime = timesFindById(idTime).isPresent();
       Boolean validaJogador = jogadorFindById(id).isPresent();

        if (validaJogador.equals(Boolean.TRUE)){
            throw new IdentificadorUtilizadoException();
        }

        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        TimeFutebol timeFutebol = timesFindById(idTime).get();

        Jogador jogador = new Jogador(id, timeFutebol, nome, dataNascimento, nivelHabilidade, salario);

        salvaJogadorBD(jogador);
	}

    public static void definirCapitao(Long idJogador) {

        Boolean validaJogador = jogadorFindById(idJogador).isPresent();

        if (validaJogador.equals(Boolean.FALSE)){
            throw new JogadorNaoEncontradoException();
        }

        Jogador jogador = jogadorFindById(idJogador).get();
        jogador.getTime().setJogadorCapitaoTime(jogador);


    }

	public static Long buscarCapitaoDoTime(Long idTime) {


        Optional<TimeFutebol> timeOptional = timesFindById(idTime);

        Boolean validaTime = timeOptional.isPresent();
        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        TimeFutebol time = timeOptional.get();
        Optional<Jogador> capitaoOptional = Optional.ofNullable(time.getJogadorCapitaoTime());

        Boolean timeNaoTemCapitao = capitaoOptional.isPresent();
        if (timeNaoTemCapitao.equals(Boolean.FALSE)) {
            throw new CapitaoNaoInformadoException();
        }

        Jogador capitao = capitaoOptional.get();
        return capitao.getId();
	}

	public static  String buscarNomeJogador(Long idJogador) {


        Optional<Jogador> jogador = jogadorFindById(idJogador);

        Boolean validaJogador = jogador.isPresent();
        if (validaJogador.equals(Boolean.FALSE)){
            throw new JogadorNaoEncontradoException();
        }
        return jogador.get().getNome();
	}

	public static String buscarNomeTime(Long idTime) {

        Optional<TimeFutebol> timeOptional = timesFindById(idTime);

        Boolean validaTime = timeOptional.isPresent();
        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        return timeOptional.get().getNome();
	}

	public static List<Long> buscarJogadoresDoTime(Long idTime) {


        Optional<TimeFutebol> timeOptional = timesFindById(idTime);

        Boolean validaTime = timeOptional.isPresent();
        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        List<Long> idsJogadoresDoTimeList = jogadoresCadastradosBD.values()
                .stream()
                .filter(jogador -> jogador.getTime().getId().equals(idTime))
                .map(Jogador::getId)
                .collect(Collectors.toList());


        return idsJogadoresDoTimeList;
    }

  	public  static Long buscarMelhorJogadorDoTime(Long idTime) {

        Optional<TimeFutebol> timeOptional = timesFindById(idTime);

        Boolean validaTime = timeOptional.isPresent();
        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        Long idMelhorJogador = jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getNivelHabilidade())).map(jogador -> jogador.getId()).get();

        return  idMelhorJogador;

    }

 	public static Long buscarJogadorMaisVelho(Long idTime) {


        Optional<TimeFutebol> timeOptional = timesFindById(idTime);

        Boolean validaTime = timeOptional.isPresent();
        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        Long idJogadorMaisVelho =  jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getDataNascimento())).map(Jogador::getId).get();

        return idJogadorMaisVelho;
	}

    public static List<Long> buscarTimes() {


        List<Long> retornaTimes = timesCadastradosBD.values().stream().map(TimeFutebol::getId).collect(Collectors.toList());

        if ( Objects.isNull(retornaTimes)){
            return new ArrayList<>();
        }

        return  retornaTimes;
	}

 	public static  Long buscarJogadorMaiorSalario(Long idTime) {

        Optional<TimeFutebol> timeOptional = timesFindById(idTime);

        Boolean validaTime = timeOptional.isPresent();
        if (validaTime.equals(Boolean.FALSE)){
            throw new TimeNaoEncontradoException();
        }

        Long jogadorMaisCaro =  jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getSalario())).map(Jogador::getId).get();

        return jogadorMaisCaro;

	}

	public  static BigDecimal buscarSalarioDoJogador(Long idJogador) {


        BigDecimal salarioJogadorMaisCaro = jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getSalario())).get().getSalario();


        if (Objects.isNull(salarioJogadorMaisCaro)){
            throw new JogadorNaoEncontradoException();
        }

        return salarioJogadorMaisCaro;

	}


	public static List<Long> buscarTopJogadores(Integer top) {

        List<Long> listaTopJogadores = jogadoresCadastradosBD.values().stream()
                .sorted(Comparator.comparingLong(Jogador::getId))
                .sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed())
                .limit(top)
                .map(Jogador::getId)
                .collect(Collectors.toList());


        if (Objects.isNull(listaTopJogadores)){
            new ArrayList<>();
        }


        return listaTopJogadores;

	}

	public static String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {


        Optional<TimeFutebol> timeCasaOptional = timesFindById(timeDaCasa);
        Optional<TimeFutebol> timeForaOptional = timesFindById(timeDeFora);

        boolean algumDosTimesNaoExiste = !timeCasaOptional.isPresent() || !timeForaOptional.isPresent();
        if (algumDosTimesNaoExiste) {
            throw new TimeNaoEncontradoException();
        }

        TimeFutebol timeCasa = timeCasaOptional.get();
        String timeCasaUniforme = timeCasa.getCorUniformePrincipal();

        TimeFutebol timeFora = timeForaOptional.get();
        String timeForaUniforme = timeFora.getCorUniformePrincipal();

        if (timeForaUniforme.equals(timeCasaUniforme)) {
            timeForaUniforme = timeFora.getCorUniformeSecundario();
        }

        return timeForaUniforme;



	}

}
