package br.com.codenation;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesafioMeuTimeTest {

    private final Map<Long, TimeFutebol> timesCadastradosBD = new HashMap<>();
    private final Map<Long, Jogador> jogadoresCadastradosBD = new HashMap<>();


	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		throw new UnsupportedOperationException();
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		throw new UnsupportedOperationException();
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
