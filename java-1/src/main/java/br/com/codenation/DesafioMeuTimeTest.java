package br.com.codenation;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import jdk.nashorn.internal.scripts.JO;

public class DesafioMeuTimeTest {

    private static final Map<Long, TimeFutebol> timesCadastradosBD = new HashMap<>();
    private static final Map<Long, Jogador> jogadoresCadastradosBD = new HashMap<>();


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

        Jogador jogador1 = new Jogador(1l, timeFutebol1,"Kaio", LocalDate.now(), 90, new BigDecimal(12121) );

        jogadoresCadastradosBD.put(jogador1.getId(), jogador1);


    }

    public static void main(String[] args) {
        carregaBaseDados();
        incluirTime(2l, "flamengo", LocalDate.now(), "vermelho", "branco");
        incluirJogador(2l, 2l, "lucas", LocalDate.now(), 30, new BigDecimal(2000.00));
        incluirJogador(3l, 1l, "mateus", LocalDate.now(), 60, new BigDecimal(6000.00));

        definirCapitao(1l);

        buscarCapitaoDoTime(1l);



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

//    Realiza a inclusão de um novo time.
//
//    Exceções:
//    Caso o identificador já exista, retornar br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException

	public static void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal,
            String corUniformeSecundario) {

        Boolean validaTimeBanco = timesFindById(id).isPresent();

        if (validaTimeBanco.equals(Boolean.TRUE)){
            throw new IdentificadorUtilizadoException();
        }
        TimeFutebol timeFutebol = new TimeFutebol(id,nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null);
        cadastraNovoTimeBD(timeFutebol);
	}

//    Realiza a inclusão de um novo jogador.
//    Exceções:
//    Caso o identificador já exista, retornar br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException
//    Caso o time informado não exista, retornar br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
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

//    Define um jogador como capitão do seu time. Um time deve ter apenas um capitão, por tanto o capitão anterior voltará a ser apenas jogador.
//
//    Long idJogador* Identificador do jogador.
//            Exceções:
//
//    Caso o jogador informado não exista, retornar br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException
    public static void definirCapitao(Long idJogador) {

        Boolean validaJogador = jogadorFindById(idJogador).isPresent();

        if (validaJogador.equals(Boolean.FALSE)){
            throw new JogadorNaoEncontradoException();
        }

        Jogador jogador = jogadorFindById(idJogador).get();
        jogador.getTime().setJogadorCapitaoTime(jogador);


    }

    /**
     * Mostra o identificador do capitao do time.
     *
     * Caso o time informado nao exista, retornar
     * br.com.codenation.desafio.exceptions.TimeNaoEncontradoException
     *
     * Caso o time informado nao tenha um capitao, retornar
     * br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException
     */
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

	public String buscarNomeJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	public String buscarNomeTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public List<Long> buscarTimes() {
		throw new UnsupportedOperationException();
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		throw new UnsupportedOperationException();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	public List<Long> buscarTopJogadores(Integer top) {
		throw new UnsupportedOperationException();
	}

	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		throw new UnsupportedOperationException();
	}

}
