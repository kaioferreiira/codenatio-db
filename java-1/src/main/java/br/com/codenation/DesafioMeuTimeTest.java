package br.com.codenation;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

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
        timesCadastradosBD.put(timeFutebol1.getId(), timeFutebol1);

        Jogador jogador =  new Jogador(1l, 1l, "kaio", LocalDate.now(), 90, new BigDecimal(10000.00));
        jogadoresCadastradosBD.put(jogador.getId(), jogador);


    }

    public static void main(String[] args) {
        carregaBaseDados();
        incluirTime(2l, "flamengo", LocalDate.now(), "vermelho", "branco");
        incluirJogador(2l, 2l, "lucas", LocalDate.now(), 30, new BigDecimal(2000.00));
        incluirJogador(3l, 1l, "mateus", LocalDate.now(), 60, new BigDecimal(6000.00));

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
        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);

        salvaJogadorBD(jogador);
	}


    public void definirCapitao(Long idJogador) {
		throw new UnsupportedOperationException();
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		throw new UnsupportedOperationException();
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
