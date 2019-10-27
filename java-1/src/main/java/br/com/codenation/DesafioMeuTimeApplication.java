package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private static final Map<Long, TimeFutebol> timesCadastradosBD = new LinkedHashMap<>();
	private static final Map<Long, Jogador> jogadoresCadastradosBD = new LinkedHashMap<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {

		Boolean validaTimeBanco = timesFindById(id).isPresent();

		if (validaTimeBanco.equals(Boolean.TRUE)){
			throw new IdentificadorUtilizadoException();
		}
		TimeFutebol timeFutebol = new TimeFutebol(id,nome, dataCriacao, corUniformePrincipal, corUniformeSecundario, null);
		cadastraNovoTimeBD(timeFutebol);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

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

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		Boolean validaJogador = jogadorFindById(idJogador).isPresent();

		if (validaJogador.equals(Boolean.FALSE)){
			throw new JogadorNaoEncontradoException();
		}

		Jogador jogador = jogadorFindById(idJogador).get();
		jogador.getTime().setJogadorCapitaoTime(jogador);

	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {

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

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {

		Optional<Jogador> jogador = jogadorFindById(idJogador);

		Boolean validaJogador = jogador.isPresent();
		if (validaJogador.equals(Boolean.FALSE)){
			throw new JogadorNaoEncontradoException();
		}
		return jogador.get().getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {

		Optional<TimeFutebol> timeOptional = timesFindById(idTime);

		Boolean validaTime = timeOptional.isPresent();
		if (validaTime.equals(Boolean.FALSE)){
			throw new TimeNaoEncontradoException();
		}

		return timeOptional.get().getNome();

	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {

		Optional<TimeFutebol> timeOptional = timesFindById(idTime);

		Boolean validaTime = timeOptional.isPresent();
		if (validaTime.equals(Boolean.FALSE)){
			throw new TimeNaoEncontradoException();
		}
		return jogadoresCadastradosBD.values()
				.stream()
				.filter(jogador -> jogador.getTime().getId().equals(idTime))
				.map(Jogador::getId)
				.collect(Collectors.toList());

	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		Optional<TimeFutebol> timeOptional = timesFindById(idTime);
		Boolean validaTime = timeOptional.isPresent();
		if (validaTime.equals(Boolean.FALSE)){
			throw new TimeNaoEncontradoException();
		}
		return  jogadoresCadastradosBD.values().stream()
				.max(Comparator.comparing(jogador -> jogador.getNivelHabilidade()))
				.map(jogador -> jogador.getId()).get();

	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {

		Optional<TimeFutebol> timeOptional = timesFindById(idTime);

		Boolean validaTime = timeOptional.isPresent();
		if (validaTime.equals(Boolean.FALSE)){
			throw new TimeNaoEncontradoException();
		}
		Long idJogadorMaisVelho =  jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getDataNascimento())).map(Jogador::getId).get();

		return idJogadorMaisVelho;
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		List<Long> retornaTimes = timesCadastradosBD.values().stream().map(TimeFutebol::getId).collect(Collectors.toList());

		if ( Objects.isNull(retornaTimes)){
			return new ArrayList<>();
		}

		return  retornaTimes;
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {

		Optional<TimeFutebol> timeOptional = timesFindById(idTime);
		Boolean validaTime = timeOptional.isPresent();
		if (validaTime.equals(Boolean.FALSE)){
			throw new TimeNaoEncontradoException();
		}

		Long jogadorMaisCaro =  jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getSalario())).map(Jogador::getId).get();

		return jogadorMaisCaro;
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {

		BigDecimal salarioJogadorMaisCaro = jogadoresCadastradosBD.values().stream().max(Comparator.comparing(jogador -> jogador.getSalario())).get().getSalario();

		if (Objects.isNull(salarioJogadorMaisCaro)){
			throw new JogadorNaoEncontradoException();
		}

		return salarioJogadorMaisCaro;
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {

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

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {

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

	private Optional<TimeFutebol> timesFindById(Long idTime) {
		TimeFutebol timeFutebol = timesCadastradosBD.get(idTime);
		return Optional.ofNullable(timeFutebol);
	}

	private Optional<Jogador> jogadorFindById(Long idjogador) {
		Jogador jogador = jogadoresCadastradosBD.get(idjogador);
		return Optional.ofNullable(jogador);
	}

	private void cadastraNovoTimeBD(TimeFutebol timeFutebol) {
		timesCadastradosBD.put(timeFutebol.getId(), timeFutebol);
	}

	private void salvaJogadorBD(Jogador jogador) {
		jogadoresCadastradosBD.put(jogador.getId(), jogador);
	}

}
